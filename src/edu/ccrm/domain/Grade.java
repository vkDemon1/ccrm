package edu.ccrm.domain;

/**
 * Enum for letter grades and their corresponding grade points. [cite: 27, 74, 100]
 * Demonstrates enums with constructors and fields.
 */
public enum Grade {
    S(10.0), A(9.0), B(8.0), C(7.0), D(6.0), E(5.0), F(0.0);

    private final double gradePoint;

    // Enum constructor [cite: 74]
    Grade(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    public double getGradePoint() {
        return gradePoint;
    }
}