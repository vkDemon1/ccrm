package edu.ccrm.domain;

/**
 * Abstract base class representing a person. [cite: 60, 61, 100]
 * Demonstrates Abstraction.
 */
public abstract class Person {
    // Encapsulation: private fields [cite: 59]
    protected Name name; // Using an immutable value object for Name
    private String email;

    public Person(Name name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters [cite: 59]
    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method to be implemented by subclasses [cite: 61]
    public abstract String getProfile();

    @Override
    public String toString() {
        return "Name=" + name + ", Email='" + email + '\'';
    }
}