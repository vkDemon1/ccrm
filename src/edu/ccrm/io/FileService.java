package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.DataStore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service for handling file import/export operations using NIO.2. [cite: 10, 29, 90, 109]
 */
public class FileService {
    private final DataStore dataStore = DataStore.getInstance();

    // Imports students from a CSV-like file [cite: 30]
    public void importStudents(Path path) throws IOException {
        // Using Files and Stream API to read lines [cite: 92]
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1) // Skip header
                    .map(line -> line.split(","))
                    .forEach(parts -> {
                        Student student = new Student(
                                Integer.parseInt(parts[0]),
                                parts[1],
                                new Name(parts[2], parts[3]),
                                parts[4],
                                "Active"
                        );
                        dataStore.addStudent(student);
                    });
            System.out.println("Successfully imported " + dataStore.getAllStudents().size() + " students.");
        }
    }

    // Imports courses from a CSV-like file [cite: 30]
    public void importCourses(Path path) throws IOException {
        // try-with-resources and multi-catch block demonstration [cite: 85]
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1)
                    .forEach(line -> {
                        String[] parts = line.split(",");
                        CourseCode code = new CourseCode(parts[0], Integer.parseInt(parts[1]));
                        Course course = new Course.Builder(code, parts[2], Integer.parseInt(parts[3]))
                                .semester(Semester.valueOf(parts[4].toUpperCase()))
                                .build();
                        dataStore.addCourse(course);
                    });
            System.out.println("Successfully imported " + dataStore.getAllCourses().size() + " courses.");
        } catch (IOException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            // Rethrowing as a custom unchecked exception
            throw new RuntimeException("Failed to parse course file: " + e.getMessage(), e);
        }
    }

    // Exports all current data to files [cite: 31]
    public void exportAllData() throws IOException {
        exportStudents();
        exportCourses();
        System.out.println("All data exported successfully.");
    }

    private void exportStudents() throws IOException {
        List<String> lines = dataStore.getAllStudents().stream()
                .map(s -> String.join(",",
                        String.valueOf(s.getId()), s.getRegNo(), s.getName().getFirstName(), s.getName().getLastName(), s.getEmail()))
                .collect(Collectors.toList());

        lines.add(0, "ID,RegNo,FirstName,LastName,Email"); // Header
        Files.write(AppConfig.getInstance().getStudentDataPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void exportCourses() throws IOException {
        List<String> lines = dataStore.getAllCourses().stream()
                .map(c -> String.join(",",
                        c.getCode().getDepartment(), String.valueOf(c.getCode().getNumber()), c.getTitle(), String.valueOf(c.getCredits()), c.getSemester().name()))
                .collect(Collectors.toList());

        lines.add(0, "Dept,Number,Title,Credits,Semester"); // Header
        Files.write(AppConfig.getInstance().getCourseDataPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}