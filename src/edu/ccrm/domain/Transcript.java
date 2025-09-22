package edu.ccrm.domain;

import java.util.List;

public class Transcript {
    private final Student student;
    private final List<Enrollment> enrollments;
    private final double gpa;

    public Transcript(Student student, List<Enrollment> enrollments, double gpa) {
        this.student = student;
        this.enrollments = enrollments;
        this.gpa = gpa;
    }

    // Demonstrates polymorphism through toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append(" ACADEMIC TRANSCRIPT\n");
        sb.append("========================================\n");
        sb.append(student.getProfile()).append("\n\n");
        sb.append("--- Enrolled Courses ---\n");

        if (enrollments.isEmpty()) {
            sb.append("No courses enrolled.\n");
        } else {
            for (Enrollment enr : enrollments) {
                sb.append(enr.toString()).append("\n");
            }
        }

        sb.append("\n----------------------------------------\n");
        sb.append(String.format("Cumulative GPA: %.2f\n", gpa));
        sb.append("========================================\n");
        return sb.toString();
    }
}