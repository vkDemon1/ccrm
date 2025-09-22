package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final List<Student> students = Collections.synchronizedList(new ArrayList<>());
    private final List<Course> courses = Collections.synchronizedList(new ArrayList<>());
    private final List<Enrollment> enrollments = Collections.synchronizedList(new ArrayList<>());

    private DataStore() {}

    public static DataStore getInstance() {
        return INSTANCE;
    }

    // Student methods
    public void addStudent(Student student) { students.add(student); }
    public List<Student> getAllStudents() { return new ArrayList<>(students); }

    // Course methods
    public void addCourse(Course course) { courses.add(course); }
    public List<Course> getAllCourses() { return new ArrayList<>(courses); }

    // Enrollment methods
    public void addEnrollment(Enrollment enrollment) { enrollments.add(enrollment); }
    public List<Enrollment> getAllEnrollments() { return new ArrayList<>(enrollments); }
}