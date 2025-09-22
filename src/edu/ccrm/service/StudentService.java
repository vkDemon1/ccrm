package edu.ccrm.service;

import edu.ccrm.domain.Name;
import edu.ccrm.domain.Student;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final DataStore dataStore = DataStore.getInstance();
    private static int nextStudentId = 101;

    public void addStudent(String regNo, Name name, String email) {
        Student student = new Student(nextStudentId++, regNo, name, email, "Active");
        dataStore.addStudent(student);
        System.out.println("Student added successfully: " + name);
    }

    public Optional<Student> findStudentById(int id) {
        return dataStore.getAllStudents().stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public List<Student> getAllStudents() {
        return dataStore.getAllStudents();
    }
}