package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.FileService;
import edu.ccrm.service.ReportService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main entry point for the CCRM console application. [cite: 48]
 * Manages the main menu and navigates to sub-menus.
 */
public class MainMenu {

    // Using a Singleton pattern for configuration [cite: 80, 115]
    private static final AppConfig config = AppConfig.getInstance();
    private static final FileService fileService = new FileService();
    private static final BackupService backupService = new BackupService();
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Campus Course & Records Manager (CCRM)");
        loadInitialData();

        // Demonstrate Java SE vs ME vs EE summary [cite: 125]
        printPlatformSummary();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            // Main application loop (while loop) [cite: 54]
            while (!exit) {
                printMainMenu();
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    // Enhanced switch for menu navigation [cite: 36, 52]
                    switch (choice) {
                        case 1 -> StudentMenu.handle(scanner);
                        case 2 -> CourseMenu.handle(scanner);
                        case 3 -> EnrollmentMenu.handle(scanner);
                        case 4 -> handleFileOperations(scanner);
                        case 5 -> handleReports(scanner);
                        case 0 -> {
                            System.out.println("Exiting application...");
                            exit = true;
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // clear buffer
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment & Grades");
        System.out.println("4. Data Utilities (Import/Export/Backup)");
        System.out.println("5. View Reports");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void handleFileOperations(Scanner scanner) {
        System.out.println("\n--- Data Utilities ---");
        System.out.println("1. Import Students and Courses from CSV");
        System.out.println("2. Export All Data to CSV");
        System.out.println("3. Create a Backup");
        System.out.println("4. Check Backup Size (Recursive Demo)");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch(choice) {
                case 1:
                    fileService.importStudents(config.getStudentDataPath());
                    fileService.importCourses(config.getCourseDataPath());
                    break;
                case 2:
                    fileService.exportAllData();
                    break;
                case 3:
                    // Using NIO.2 Path and Files API [cite: 32, 91]
                    Path backupPath = backupService.createBackup();
                    System.out.println("Backup created at: " + backupPath); // [cite: 124]
                    break;
                case 4:
                    // Recursive utility demonstration [cite: 33, 119]
                    backupService.displayBackupSize();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    private static void handleReports(Scanner scanner) {
        // Report generation using Streams [cite: 93, 120]
        System.out.println("\n--- Reports ---");
        System.out.println("1. GPA Distribution Report");
        System.out.print("Select a report: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            reportService.printGpaDistribution();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void loadInitialData() {
        System.out.println("Loading initial data from default paths...");
        try {
            fileService.importStudents(config.getStudentDataPath());
            fileService.importCourses(config.getCourseDataPath());
        } catch (IOException e) {
            System.err.println("Could not load initial data: " + e.getMessage());
            System.err.println("Please use the import utility in the menu.");
        }
    }

    private static void printPlatformSummary() {
        System.out.println("\n--- Platform Note ---");
        System.out.println("This application is built on Java SE (Standard Edition).");
        System.out.println(" - Java SE: For desktop/server applications. Core Java platform.");
        System.out.println(" - Java ME (Micro Edition): For small/mobile devices.");
        System.out.println(" - Java EE (Enterprise Edition): For large-scale, web-based enterprise applications. Builds on SE.");
    }
}