/////DANIEL O'DRISCOLL/////

///////////// TAB 2////////////////

package com.example.assignment1.View;

import com.example.assignment1.Controller.StudentController;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class AddModuleGrade{
    private Label studentLabel;
    private Label moduleLabel;
    private Label gradeLabel;
    private TextField moduleInput;
    public Button updateButton, backButton;
    private TextField gradeInput;
    private StudentController control;
    static private ArrayList<String> modules;
    static private ArrayList<String> grades;
    private ListView studentListView;


/////// PASS CONTROLLER TO TAB 2 CLASS////////
    public AddModuleGrade(StudentController control, ListView studentListView){
        this.control = control;
        modules = new ArrayList<>();
        grades = new ArrayList<>();
        this.studentListView = studentListView;

    }

/////FUNCTION DISPLAYS NEW LAYOUT FOR TAB 2////////
    public VBox display(String studentID){
        studentLabel = new Label(studentID);
        moduleLabel = new Label("Completed Module: ");
        moduleInput = new TextField("e.g. Maths");
        gradeLabel = new Label("Grade obtained: ");
        gradeInput = new TextField("e.g. 96");
        updateButton = new Button("Update");
        backButton = new Button("Go Back");
        HBox h1 = new HBox(studentLabel);
        HBox h2 = new HBox(moduleLabel, moduleInput);
        HBox h3 = new HBox(gradeLabel, gradeInput);
        HBox h4 = new HBox(updateButton, backButton);
        VBox layout = new VBox(h1, h2, h3, h4);

        gradeInput.textProperty().addListener((Observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        gradeInput.setText(newValue.replaceAll("\\D", ""));
                    }
                });
        gradeInput.setOnMouseClicked(mouseEvent -> {
            gradeInput.clear();
            updateButton.setText("Update");
        });
        moduleInput.setOnMouseClicked(mouseEvent -> {
            moduleInput.clear();
            updateButton.setText("Update");
        });

        updateButton.setOnAction(actionEvent -> {
            modules.add(moduleInput.getText());
            grades.add(gradeInput.getText());
            control.Update(studentID, modules, grades);

            if(grades.size() != 0 && modules.size() != 0){
                control.calculateGradesModules(studentID, grades, modules);
            }

        });

        return layout;
    }



}
