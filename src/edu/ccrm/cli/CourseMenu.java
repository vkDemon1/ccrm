package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.CourseCode;
import edu.ccrm.domain.Semester;
import edu.ccrm.service.CourseService;
import java.util.List;
import java.util.Scanner;

public class CourseMenu {
    private static final CourseService courseService = new CourseService();

    public static void handle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add New Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Search Courses by Department");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCourse(scanner);
                case 2 -> listCourses(courseService.getAllCourses());
                case 3 -> searchByDept(scanner);
                case 0 -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter Department Code (e.g., CS): ");
        String dept = scanner.nextLine().toUpperCase();
        System.out.print("Enter Course Number (e.g., 101): ");
        int num = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Semester (SPRING, FALL, etc.): ");
        Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());

        Course course = new Course.Builder(new CourseCode(dept, num), title, credits)
                .semester(semester)
                .build();
        courseService.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private static void searchByDept(Scanner scanner) {
        System.out.print("Enter department code to search for: ");
        String dept = scanner.nextLine();
        listCourses(courseService.findCoursesByDepartment(dept));
    }

    private static void listCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n--- Course Listing ---");
        courses.forEach(c -> System.out.printf("Code: %s, Title: %s, Credits: %d, Semester: %s%n",
                c.getCode(), c.getTitle(), c.getCredits(), c.getSemester()));
    }
}