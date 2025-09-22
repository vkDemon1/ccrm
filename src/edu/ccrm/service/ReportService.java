package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for generating reports from the application data.
 * This class demonstrates Stream pipelines and different ways of using comparators.
 */
public class ReportService {

    private final DataStore dataStore = DataStore.getInstance();
    private final EnrollmentService enrollmentService = new EnrollmentService();

    /**
     * Generates and prints a report on the distribution of student GPAs.
     * This method demonstrates a Stream pipeline that aggregates data[cite: 93, 120].
     */
    public void printGpaDistribution() {
        List<Student> students = dataStore.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student data available to generate a report.");
            return;
        }

        // Using Collectors.groupingBy to create a distribution map.
        Map<String, Long> gpaDistribution = students.stream()
                .collect(Collectors.groupingBy(student -> {
                    double gpa = enrollmentService.calculateGpa(student);
                    if (gpa >= 9.0) return "A Grade (9.0+)";
                    if (gpa >= 8.0) return "B Grade (8.0-8.9)";
                    if (gpa >= 7.0) return "C Grade (7.0-7.9)";
                    if (gpa >= 6.0) return "D Grade (6.0-6.9)";
                    return "Below D (< 6.0)";
                }, Collectors.counting())); // The downstream collector counts the entries.

        System.out.println("\n--- Student GPA Distribution Report ---");
        gpaDistribution.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort the report by grade category.
                .forEach(entry -> System.out.printf("%-20s: %d student(s)%n", entry.getKey(), entry.getValue()));
        System.out.println("-------------------------------------");
    }

    /**
     * Prints a list of students sorted by name.
     * This method demonstrates the use of an ANONYMOUS INNER CLASS for comparison.
     */
    public void printStudentsSortedByName() {
        List<Student> students = dataStore.getAllStudents();

        // Using an anonymous inner class to define a one-time-use comparator.
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // First compare by last name, then by first name if last names are the same.
                int lastNameCompare = s1.getName().getLastName().compareTo(s2.getName().getLastName());
                if (lastNameCompare != 0) {
                    return lastNameCompare;
                }
                return s1.getName().getFirstName().compareTo(s2.getName().getFirstName());
            }
        });

        System.out.println("\n--- Students Sorted by Name (Anonymous Class) ---");
        students.forEach(student -> System.out.println(student.getName()));
    }

    /**
     * Prints a list of students sorted by their GPA in descending order.
     * This method demonstrates the use of a LAMBDA EXPRESSION for comparison.
     */
    public void printStudentsSortedByGpa() {
        List<Student> students = dataStore.getAllStudents();

        // Storing GPAs in a map to avoid recalculation during the sort.
        Map<Integer, Double> gpaMap = students.stream()
                .collect(Collectors.toMap(Student::getId, enrollmentService::calculateGpa));

        // Using a lambda expression for a concise comparator implementation.
        students.sort((s1, s2) -> {
            Double gpa1 = gpaMap.getOrDefault(s1.getId(), 0.0);
            Double gpa2 = gpaMap.getOrDefault(s2.getId(), 0.0);
            return gpa2.compareTo(gpa1); // Descending order
        });

        System.out.println("\n--- Students Sorted by GPA (Lambda) ---");
        students.forEach(student ->
                System.out.printf("%-25s | GPA: %.2f%n", student.getName(), gpaMap.get(student.getId()))
        );
    }
}