package com.example.assignment1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StudentListView {
        private TabPane tabPane;

        // Tab 1 UI components
        private GridPane addStudentsLayout;
        private TextField nameField;
        private TextField sIdField;
        private TextField dobField;
        private Button addButton;
        private Button removeButton;
        private Button listButton;
        private TextArea outputArea;

        // Tab 2 UI components
        private GridPane recordModulesLayout;
        private ComboBox<Student> studentComboBox;
        private TextField moduleNameField;
        private TextField moduleGradeField;
        private Button addModuleButton;

        // Tab 3 UI components
        private GridPane viewStudentRecordsLayout;
        private ComboBox<Student> viewStudentComboBox;
        private Button viewStudentButton;
        private TextArea studentInfoArea;

        public StudentListView() {
            initComponents();
        }

        private void initComponents() {
            tabPane = new TabPane();

            // Initialize and configure Tab 1 UI components
            addStudentsLayout = new GridPane();
            addStudentsLayout.setHgap(10);
            addStudentsLayout.setVgap(10);
            addStudentsLayout.setPadding(new Insets(20, 20, 20, 20));

            nameField = new TextField();
            sIdField = new TextField();
            dobField = new TextField();
            addButton = new Button("Add");
            removeButton = new Button("Remove");
            listButton = new Button("List");

            outputArea = new TextArea("Students in the application.");
            outputArea.setPrefHeight(200);
            outputArea.setEditable(false);

            // Initialize and configure Tab 2 UI components
            recordModulesLayout = new GridPane();
            recordModulesLayout.setHgap(10);
            recordModulesLayout.setVgap(10);
            recordModulesLayout.setPadding(new Insets(20, 20, 20, 20));

            studentComboBox = new ComboBox<>();
            moduleNameField = new TextField();
            moduleGradeField = new TextField();
            addModuleButton = new Button("Add Module");

            // Initialize and configure Tab 3 UI components
            viewStudentRecordsLayout = new GridPane();
            viewStudentRecordsLayout.setHgap(10);
            viewStudentRecordsLayout.setVgap(10);
            viewStudentRecordsLayout.setPadding(new Insets(20, 20, 20, 20));

            viewStudentComboBox = new ComboBox<>();
            viewStudentButton = new Button("View Student");
            studentInfoArea = new TextArea();
            studentInfoArea.setEditable(false);

            // Add UI components to their respective layouts
            addStudentsLayout.add(new Label("Enter Name "), 0, 0);
            addStudentsLayout.add(nameField, 1, 0);
            addStudentsLayout.add(new Label("Enter Student ID "), 0, 1);
            addStudentsLayout.add(sIdField, 1, 1);
            addStudentsLayout.add(new Label("Enter Date of Birth "), 0, 2);
            addStudentsLayout.add(dobField, 1, 2);

            HBox inControlBox = new HBox(); //created to hold the add, remove, and list buttons for the Add Students tab
            inControlBox.getChildren().addAll(addButton, removeButton, listButton);
            inControlBox.setAlignment(Pos.CENTER_LEFT);  //hbox works only in horzontal rows
            inControlBox.setSpacing(10);
            addStudentsLayout.add(inControlBox, 0, 3, 2, 1);
            addStudentsLayout.add(outputArea, 0, 4, 2, 1);

            Tab tab1 = new Tab("Add/Remove Students", addStudentsLayout);
            tab1.setClosable(false);

            recordModulesLayout.add(new Label("Select Student"), 0, 0);
            recordModulesLayout.add(studentComboBox, 1, 0);
            recordModulesLayout.add(new Label("Module Name"), 0, 1);
            recordModulesLayout.add(moduleNameField, 1, 1);
            recordModulesLayout.add(new Label("Module Grade"), 0, 2);
            recordModulesLayout.add(moduleGradeField, 1, 2);
            recordModulesLayout.add(addModuleButton, 1, 3);

            Tab tab2 = new Tab("Record Modules", recordModulesLayout);
            tab2.setClosable(false);

            viewStudentRecordsLayout.add(new Label("Select Student"), 0, 0);
            viewStudentRecordsLayout.add(viewStudentComboBox, 1, 0);
            viewStudentRecordsLayout.add(viewStudentButton, 2, 0);
            viewStudentRecordsLayout.add(studentInfoArea, 0, 1, 3, 1);

            Tab tab3 = new Tab("View Student Records", viewStudentRecordsLayout);
            tab3.setClosable(false);

            tabPane.getTabs().addAll(tab1, tab2, tab3);
        }

        public TabPane getTabPane() {
            return tabPane;
        }

        public TextField getNameField() {
            return nameField;
        }

        public TextField getSIdField() {
            return sIdField;
        }

        public TextField getDobField() {
            return dobField;
        }

        public Button getAddButton() {
            return addButton;
        }

        public Button getRemoveButton() {
            return removeButton;
        }

        public Button getListButton() {
            return listButton;
        }

        public TextArea getOutputArea() {
            return outputArea;
        }

        public ComboBox<Student> getStudentComboBox() {
            return studentComboBox;
        }

        public TextField getModuleNameField() {
            return moduleNameField;
        }

        public TextField getModuleGradeField() {
            return moduleGradeField;
        }

        public Button getAddModuleButton() {
            return addModuleButton;
        }

        public ComboBox<Student> getViewStudentComboBox() {
            return viewStudentComboBox;
        }

        public Button getViewStudentButton() {
            return viewStudentButton;
        }

        public TextArea getStudentInfoArea() {
            return studentInfoArea;
        }
    }

