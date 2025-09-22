package edu.ccrm.service.interfaces;

/**
 * Interface with a default method. [cite: 70]
 */
public interface Loggable {
    // Default method
    default void log(String message) {
        System.out.println("LOG: " + message);
    }
}