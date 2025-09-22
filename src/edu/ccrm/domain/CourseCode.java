package edu.ccrm.domain;

/**
 * An immutable value class for CourseCode. [cite: 66]
 * All fields are final and there are no setters.
 */
public final class CourseCode {
    private final String department;
    private final int number;

    public CourseCode(String department, int number) {
        this.department = department;
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return department + "-" + number;
    }
}