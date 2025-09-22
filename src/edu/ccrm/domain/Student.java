package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Student, inheriting from Person. [cite: 18, 60]
 * Demonstrates Inheritance.
 */
public class Student extends Person {
    private final int id;
    private final String regNo;
    private String status;
    private final LocalDate registrationDate; // Using Date/Time API [cite: 18, 94]

    public Student(int id, String regNo, Name name, String email, String status) {
        // Constructor in inheritance using 'super' [cite: 64]
        super(name, email);
        this.id = id;
        this.regNo = regNo;
        this.status = status;
        this.registrationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getRegNo() {
        return regNo;
    }

    // Overriding an abstract method [cite: 62, 77]
    @Override
    public String getProfile() {
        return String.format("Student Profile:\nID: %d\nRegNo: %s\nName: %s\nEmail: %s\nStatus: %s",
                id, regNo, super.getName(), super.getEmail(), status);
    }

    // Overriding toString() [cite: 77]
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", regNo='" + regNo + '\'' +
                ", " + super.toString() +
                '}';
    }
}