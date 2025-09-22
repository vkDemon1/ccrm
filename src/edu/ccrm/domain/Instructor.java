package edu.ccrm.domain;

public class Instructor extends Person {
    private static int nextId = 2001;
    private final int instructorId;
    private String department;

    public Instructor(Name name, String email, String department) {
        super(name, email);
        this.instructorId = nextId++;
        this.department = department;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getProfile() {
        return String.format("Instructor Profile:\nID: %d\nName: %s\nDepartment: %s",
                instructorId, super.getName(), department);
    }
}