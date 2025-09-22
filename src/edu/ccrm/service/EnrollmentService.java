package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {
    private final DataStore dataStore = DataStore.getInstance();
    private static final int MAX_CREDITS_PER_SEMESTER = 18;

    public void enrollStudent(Student student, Course course)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {

        List<Enrollment> studentEnrollments = getEnrollmentsForStudent(student);

        // Rule 1: Check for duplicate enrollment
        boolean alreadyEnrolled = studentEnrollments.stream()
                .anyMatch(e -> e.getCourse().equals(course));
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
        }

        // Rule 2: Check for credit limit
        int currentCredits = studentEnrollments.stream()
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();
        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Cannot enroll. Maximum credit limit of "
                    + MAX_CREDITS_PER_SEMESTER + " would be exceeded.");
        }

        Enrollment newEnrollment = new Enrollment(student, course);
        dataStore.addEnrollment(newEnrollment);
        System.out.println("Enrollment successful!");
    }

    public void recordGrade(Student student, Course course, Grade grade) {
        getEnrollmentsForStudent(student).stream()
                .filter(e -> e.getCourse().equals(course))
                .findFirst()
                .ifPresent(enrollment -> {
                    enrollment.setGrade(grade);
                    System.out.println("Grade recorded successfully.");
                });
    }

    public double calculateGpa(Student student) {
        List<Enrollment> gradedEnrollments = getEnrollmentsForStudent(student).stream()
                .filter(e -> e.getGrade() != null)
                .collect(Collectors.toList());

        if (gradedEnrollments.isEmpty()) {
            return 0.0;
        }

        double totalPoints = gradedEnrollments.stream()
                .mapToDouble(e -> e.getGrade().getGradePoint() * e.getCourse().getCredits())
                .sum();

        int totalCredits = gradedEnrollments.stream()
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        return totalPoints / totalCredits;
    }

    public Transcript generateTranscript(Student student) {
        List<Enrollment> enrollments = getEnrollmentsForStudent(student);
        double gpa = calculateGpa(student);
        return new Transcript(student, enrollments, gpa);
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return dataStore.getAllEnrollments().stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }
}