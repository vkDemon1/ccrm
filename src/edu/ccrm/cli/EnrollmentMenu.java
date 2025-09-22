package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;

import java.util.Optional;
import java.util.Scanner;

public class EnrollmentMenu {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void handle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment & Grades ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Record Grade for Student");
            System.out.println("3. View Student Transcript");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> enrollStudent(scanner);
                case 2 -> recordGrade(scanner);
                case 3 -> viewTranscript(scanner);
                case 0 -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Enter Student ID to enroll: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Optional<Student> studentOpt = studentService.findStudentById(studentId);
        if (studentOpt.isEmpty()) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code (e.g., CS-101): ");
        String[] codeParts = scanner.nextLine().split("-");
        Optional<Course> courseOpt = courseService.getAllCourses().stream()
                .filter(c -> c.getCode().getDepartment().equalsIgnoreCase(codeParts[0]) &&
                        c.getCode().getNumber() == Integer.parseInt(codeParts[1]))
                .findFirst();

        if (courseOpt.isEmpty()) {
            System.out.println("Course not found.");
            return;
        }

        // This demonstrates handling custom checked exceptions.
        try {
            enrollmentService.enrollStudent(studentOpt.get(), courseOpt.get());
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
            System.err.println("Enrollment Failed: " + e.getMessage());
        }
    }

    private static void recordGrade(Scanner scanner) {
        // Similar logic to enrollStudent to find the student and course
        // Then, prompt for a grade (S, A, B, etc.)
        // And call enrollmentService.recordGrade()
        System.out.println("Record grade functionality not fully implemented in this demo.");
    }

    private static void viewTranscript(Scanner scanner) {
        System.out.print("Enter Student ID to view transcript: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Optional<Student> studentOpt = studentService.findStudentById(studentId);
        if (studentOpt.isEmpty()) {
            System.out.println("Student not found.");
            return;
        }

        Transcript transcript = enrollmentService.generateTranscript(studentOpt.get());
        System.out.println(transcript); // Relies on the overridden toString() method
    }
}