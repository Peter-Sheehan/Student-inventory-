// StudentListApp.java
package com.example.assignment1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentListApp extends Application {
    @Override
    public void start(Stage stage) {
        StudentListView view = new StudentListView();
        StudentListController controller = new StudentListController(view);

        Scene scene = new Scene(view.getTabPane(), 400, 450);
        stage.setTitle("Student List App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
