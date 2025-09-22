package edu.ccrm.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;
    private final LocalDateTime enrollmentDate;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String gradeStr = (grade != null) ? grade.name() : "Not Graded";
        return String.format("Course: %-40s | Grade: %s", course.getTitle(), gradeStr);
    }
}