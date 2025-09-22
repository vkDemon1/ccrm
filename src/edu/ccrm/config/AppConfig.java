package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Singleton class for application-wide configurations. [cite: 80, 111, 115]
 * Provides centralized access to paths and settings.
 */
public class AppConfig {
    // 1. Eager initialization of the singleton instance
    private static final AppConfig INSTANCE = new AppConfig();

    private final Path dataFolderPath;
    private final Path backupRootPath;

    // 2. Private constructor to prevent instantiation
    private AppConfig() {
        this.dataFolderPath = Paths.get("data");
        this.backupRootPath = Paths.get("backups");
    }

    // 3. Public static method to get the single instance
    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public Path getStudentDataPath() {
        return dataFolderPath.resolve("students.csv");
    }

    public Path getCourseDataPath() {
        return dataFolderPath.resolve("courses.csv");
    }

    public Path getEnrollmentDataPath() {
        return dataFolderPath.resolve("enrollments.csv");
    }

    public Path getBackupRootPath() {
        return backupRootPath;
    }
}