package com.example.assignment1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {
    private String name;
    private String studentID;
    private String dateOfBirth;
    private Map<String, String> completedModules;

    public Student(String name, String studentID, String dateOfBirth) {
        this.name = name;
        this.studentID = studentID;
        this.dateOfBirth = dateOfBirth;
        this.completedModules = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Map<String, String> getModules() {
        return completedModules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(studentID, student.studentID) && Objects.equals(dateOfBirth, student.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Student {" +
                "name='" + name + '\'' +
                ", studentID='" + studentID + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentID, dateOfBirth);
    }

    public void addModule(String moduleName, String moduleGrade) {
        completedModules.put(moduleName, moduleGrade);
    }

    // Retrieve the completed module information as a formatted String
    public String getCompletedModules() {
        StringBuilder sb = new StringBuilder("Completed Modules:\n");
        for (Map.Entry<String, String> entry : completedModules.entrySet()) {
            sb.append("Module Name: ").append(entry.getKey())
                    .append(", Grade: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
