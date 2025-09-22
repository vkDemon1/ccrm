package edu.ccrm.cli;

import edu.ccrm.domain.Name;
import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    private static final StudentService studentService = new StudentService();

    public static void handle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> listStudents();
                case 0 -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Registration No (e.g., S2025001): ");
        String regNo = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        studentService.addStudent(regNo, new Name(firstName, lastName), email);
    }

    private static void listStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- List of All Students ---");
        students.forEach(s -> System.out.printf("ID: %d, RegNo: %s, Name: %s%n",
                s.getId(), s.getRegNo(), s.getName()));
    }
}