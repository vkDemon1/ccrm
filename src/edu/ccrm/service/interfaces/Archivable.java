package edu.ccrm.service.interfaces;

/**
 * Another interface with a default method to create a diamond problem. [cite: 70]
 */
public interface Archivable {
    default void log(String message) {
        System.out.println("ARCHIVE LOG: " + message);
    }
}