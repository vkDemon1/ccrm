package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides business logic for course management. [cite: 108]
 */
public class CourseService {
    private final DataStore dataStore = DataStore.getInstance();

    public void addCourse(Course course) {
        dataStore.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return dataStore.getAllCourses();
    }

    /**
     * Searches courses by department using the Stream API. [cite: 23]
     * Demonstrates functional interfaces (Predicate) via filtering. [cite: 72]
     */
    public List<Course> findCoursesByDepartment(String department) {
        return dataStore.getAllCourses().stream()
                .filter(course -> course.getCode().getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    /**
     * Searches courses by semester using the Stream API. [cite: 23]
     */
    public List<Course> findCoursesBySemester(Semester semester) {
        return dataStore.getAllCourses().stream()
                .filter(course -> course.getSemester() == semester)
                .collect(Collectors.toList());
    }
}