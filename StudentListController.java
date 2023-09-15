package com.example.assignment1;

import javafx.collections.FXCollections;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class StudentListController {
    private ArrayList<Student> students;
    private StudentListView view;

    public StudentListController(StudentListView view) {
        this.view = view;
        students = new ArrayList<>();
        initEventHandlers();
    }

    private void initEventHandlers() {
        view.getAddButton().setOnAction(e -> {  //when the add button is clicked the code inside will run
            String name = view.getNameField().getText(); // get the name entered in the "Name" field of the view
            String sId = view.getSIdField().getText();
            String dob = view.getDobField().getText();

            Student newStudent = new Student(name, sId, dob);
            students.add(newStudent);
            updateStudentComboBox(view.getStudentComboBox(), students);
            updateStudentComboBox(view.getViewStudentComboBox(), students);
        });

        view.getRemoveButton().setOnAction(e -> {
            Student selectedStudent = view.getStudentComboBox().getValue();
            if (selectedStudent != null) {
                students.remove(selectedStudent);
                updateStudentComboBox(view.getStudentComboBox(), students); //update the combo box so in the next tab the user can select a student.
                updateStudentComboBox(view.getViewStudentComboBox(), students);
            }
        });

        view.getAddModuleButton().setOnAction(e -> {
            Student selectedStudent = view.getStudentComboBox().getValue();
            if (selectedStudent != null) {
                String moduleName = view.getModuleNameField().getText();
                String moduleGrade = view.getModuleGradeField().getText().trim();

                selectedStudent.addModule(moduleName, moduleGrade);
            }
        });

        view.getViewStudentButton().setOnAction(e -> {
            Student selectedStudent = view.getViewStudentComboBox().getValue();
            if (selectedStudent != null) {
                String studentInfo = selectedStudent.toString() + "\n";
                studentInfo += selectedStudent.getCompletedModules();

                view.getStudentInfoArea().setText(studentInfo);
            }
        });

        view.getListButton().setOnAction(e -> {
            StringBuilder sb = new StringBuilder("Students:\n");
            for (Student student : students) {
                sb.append(student.toString()).append("\n");
            }

            view.getOutputArea().setText(sb.toString());
        });
    }

    private void updateStudentComboBox(ComboBox<Student> comboBox, ArrayList<Student> students) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(students);
    }
}

