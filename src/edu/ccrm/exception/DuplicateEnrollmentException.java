package edu.ccrm.exception;

/**
 * Custom checked exception for duplicate enrollments. [cite: 84, 86, 87]
 */
public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}