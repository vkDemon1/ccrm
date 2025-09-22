# Campus Course & Records Manager (CCRM)

## Project Overview

The **Campus Course & Records Manager (CCRM)** is a console-based Java application designed to manage academic data for a college or university. This project demonstrates core Java principles, including Object-Oriented Programming (OOP), robust exception handling, and modern I/O with NIO.2 and the Date/Time API.

CCRM provides a menu-driven command-line interface (CLI) to handle the management of students, courses, enrollments, and grades. The application also includes file utilities for importing and exporting data, as well as backing up course information.

## Features

### Functional
* **Student Management:** Add, list, update, and deactivate student records, each with an ID, registration number, full name, email, and enrollment status.
* **Course Management:** Create, update, and deactivate courses, with fields for code, title, credits, instructor, semester, and department.
* **Enrollment & Grading:** Enroll and unenroll students from courses, record marks, and compute GPA. The system uses enums for `Semester` and `Grade`.
* **File Operations:** Import student and course data from simple CSV-like text files, and export current data to files. A backup command copies exported files to a timestamped folder and includes a recursive utility to compute the total size of the backup directory.

### Technical
* **OOP Principles:** The project demonstrates Encapsulation, Inheritance, Abstraction, and Polymorphism.
* **Advanced Java Concepts:**
    * **I/O:** Utilizes NIO.2 `Path` and `Files` APIs for file operations and Java Streams for reading, writing, and processing lines.
    * **Data Structures & Collections:** Uses arrays, `Arrays` class utilities, and streams for data manipulation like sorting and filtering.
    * **Exceptions:** Includes robust exception handling with `try-catch-finally`, multi-catch blocks, and custom exceptions like `DuplicateEnrollmentException`.
    * **Design Patterns:** Implements the **Singleton** pattern for application configuration (`AppConfig`) and the **Builder** pattern for complex objects like `Course`.
    * **Date/Time API:** Uses the modern API for handling timestamps and dates.

## How to Run

1.  **Prerequisites:** Ensure you have the **Java Development Kit (JDK) for Java SE** installed on your system.
2.  **Clone the repository:**
    ```bash
    git clone [your-repository-url]
    cd CampusCourseRecordsManager
    ```
3.  **Compile and Run:**
    * You can compile and run the project from your IDE (e.g., Eclipse) by running the `main` class.
    * Alternatively, you can use the command line.


### Java ME vs. Java SE vs. Java EE
| Feature | Java SE (Standard Edition) | Java EE (Enterprise Edition) | Java ME (Micro Edition) |
| :--- | :--- | :--- | :--- |
| **Purpose** | Desktop, console, and applets  | Large-scale, multi-tier, enterprise applications  | Mobile and embedded devices (e.g., phones, set-top boxes)  |
| **Key APIs** | Core libraries (Java Language, util, I/O)  | Adds APIs for servlets, EJB, JPA, etc.  | Small-footprint APIs (e.g., Swing, AWT) |
| **Platform** | Standalone applications  | Server-side runtimes like WildFly or GlassFish  | Varies by device; relies on JVM with limited features |

### JDK, JRE, and JVM
* **JDK (Java Development Kit):** A software package for developing Java applications. It includes the JRE and a set of development tools like the compiler (`javac`) and debugger[cite: 44].
* **JRE (Java Runtime Environment):** A bundle that contains the JVM, class libraries, and other components required to run a Java application[cite: 44].
* **JVM (Java Virtual Machine):** The core component responsible for executing Java bytecode. It acts as an interpreter, enabling Java's "write once, run anywhere" capability[cite: 44].

## Syllabus Topic Mapping

| Syllabus Topic | File(s) / Class(es) / Method(s) |
| :--- | :--- |
| **Object-Oriented Programming (OOP)** | `Person.java` (Abstraction, Inheritance), `Student.java`, `Instructor.java`, `Course.java` (Encapsulation), `CourseService.java` (Polymorphism) |
| **File I/O (NIO.2)** | `ImportExportService.java`, `BackupService.java` |
| **Exceptions** | `DuplicateEnrollmentException.java`, `MaxCreditLimitExceededException.java` |
| **Design Patterns** | `AppConfig.java` (Singleton), `Course.Builder.java` (Builder) |
| **Java Streams API** | `CourseService.java` (for searching/filtering), `ReportService.java` (for GPA distribution) |

## Notes on Assertions
Assertions are used for internal sanity checks and should not be used for validating user input. To enable assertions when running the program from the command line, use the `-ea` flag.
Example command:
```bash
java -ea -jar CCRM.jar