package edu.ccrm.domain;

/**
 * Represents a Course. This class uses the Builder design pattern. [cite: 81]
 */
public class Course {
    private final CourseCode code; // Using immutable value class [cite: 66]
    private final String title;
    private final int credits;
    private Instructor instructor;
    private final Semester semester;

    // Private constructor to be used by the Builder
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
    }

    public CourseCode getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Semester getSemester() {
        return semester;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" + "code=" + code + ", title='" + title + '\'' + ", credits=" + credits + '}';
    }

    // Static nested class for the Builder pattern [cite: 67, 81]
    public static class Builder {
        private CourseCode code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;

        public Builder(CourseCode code, String title, int credits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Course build() {
            // Using an assertion to check for invariants [cite: 89]
            assert credits > 0 && credits < 6 : "Invalid credit value";
            return new Course(this);
        }
    }
}