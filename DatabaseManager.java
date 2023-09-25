package com.example.assignment1;
import com.example.assignment1.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:student_records.db";
    private static DatabaseManager instance;

    public DatabaseManager() {
        try {
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    private void createTables() throws SQLException {
        String studentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                "name TEXT NOT NULL," +
                "student_id TEXT NOT NULL," +
                "dob TEXT NOT NULL" +
                ");";

        String completedModulesTable = "CREATE TABLE IF NOT EXISTS completed_modules ("
                + "student_id INTEGER NOT NULL,"
                + "module_name TEXT NOT NULL,"
                + "module_grade TEXT NOT NULL,"
                + "FOREIGN KEY(student_id) REFERENCES students(id));";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(studentsTable);
            stmt.execute(completedModulesTable);
        }
    }

    public void addStudent(Student student) {
        String insertStudent = "INSERT INTO students (name, student_id, dob) VALUES (?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertStudent)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentID());
            pstmt.setString(3, student.getDateOfBirth());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  Map<String, String> getCompletedModules(Student student) {
        String query = "SELECT module_name, module_grade FROM completed_modules "
                + "WHERE student_id = ?;";

        Map<String, String> completedModules = new HashMap<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getStudentID());

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String moduleName = resultSet.getString("module_name");
                String moduleGrade = resultSet.getString("module_grade");
                completedModules.put(moduleName, moduleGrade);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return completedModules;
    }


    public void removeStudent(Student student) {
        String deleteStudent = "DELETE FROM students WHERE student_id = ?;";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(deleteStudent)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        List<Student> students = new ArrayList<>();
        String selectStudents = "SELECT * FROM students;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectStudents)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String studentID = rs.getString("student_id");
                String dob = rs.getString("dob");
                Student student = new Student(name, studentID, dob);

                String moduleQuery = "SELECT module_name, module_grade FROM completed_modules WHERE student_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(moduleQuery)) {
                    pstmt.setString(1, studentID);
                    ResultSet moduleRs = pstmt.executeQuery();

                    while (moduleRs.next()) {
                        String moduleName = moduleRs.getString("module_name");
                        String moduleGrade = moduleRs.getString("module_grade");
                        student.addModule(moduleName, moduleGrade);
                    }
                }

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public List<Student> getPassedStudents() {
        List<Student> students = new ArrayList<>();
        String query = """
            SELECT DISTINCT s.*
            FROM students s
            JOIN completed_modules cm ON s.student_id = cm.student_id
            WHERE s.student_id NOT IN (
                SELECT student_id
                FROM completed_modules
                WHERE module_grade < 40
            )
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String studentID = rs.getString("student_id");
                String dob = rs.getString("dob");
                Student student = new Student(name, studentID, dob);

                String moduleQuery = "SELECT module_name, module_grade FROM completed_modules WHERE student_id = ?";
                try (PreparedStatement pstmt2 = conn.prepareStatement(moduleQuery)) {
                    pstmt2.setString(1, studentID);
                    ResultSet moduleRs = pstmt2.executeQuery();

                    while (moduleRs.next()) {
                        String moduleName = moduleRs.getString("module_name");
                        String moduleGrade = moduleRs.getString("module_grade");
                        student.addModule(moduleName, moduleGrade);
                    }
                }

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public void addModule(Student student, String moduleName, String moduleGrade) {
        String insertModule = "INSERT INTO completed_modules (student_id, module_name, module_grade) " +
                "VALUES (?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertModule)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, moduleName);
            pstmt.setString(3, moduleGrade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeModule(String studentID, String moduleName) {
        String deleteModule = "DELETE FROM completed_modules WHERE student_id = ? AND module_name = ?;";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(deleteModule)) {
            pstmt.setString(1, studentID);
            pstmt.setString(2, moduleName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


