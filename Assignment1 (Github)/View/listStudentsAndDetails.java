/////DANIEL O'DRISCOLL/////

///////////// TAB 3////////////////

package com.example.assignment1.View;

import com.example.assignment1.Model.GradeComparator;
import com.example.assignment1.Model.ModuleComparator;
import com.example.assignment1.Model.Student;
import com.example.assignment1.Controller.StudentController;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.util.*;

public class listStudentsAndDetails {
    StudentController control;
    Button viewAll = new Button("View all");
    Button Search = new Button("Search");
    Button load = new Button("Load");
    Button remove = new Button("Remove");
    TextField removeByNum = new TextField("Enter Student Number");

    Text t = new Text (10, 20, "Enter student number:");
    TextArea studentDetails = new TextArea();
    TextField searchBar = new TextField();
    Label choice = new Label("Sort by: ");
    ComboBox comboBox = new ComboBox();
    HBox h1 = new HBox(t, searchBar, Search);
    HBox h2 = new HBox(viewAll,load,comboBox, studentDetails);
    HBox h3 = new HBox(removeByNum, remove);
    VBox layout = new VBox(h1, h2, h3);

    /////// PASS CONTROLLER TO TAB 3 CLASS////////
    public listStudentsAndDetails(StudentController control, ListView studentListView){
        this.control = control;
        comboBox.setPromptText("Sort by");
        comboBox.getItems().addAll("Grade", "Module");
    }

    ///// FUNCTION RETURNS VBOX FOR TAB 3 LAYOUT ////////////
    public VBox listButtonAction(){

        Search.setOnAction(actionEvent -> {                              // CHECKS IF STUDENT Number searched matches, then displays info
            for(int i = 0; i < control.getStudentList().getSize(); i++){
                if(searchBar.getText().equals(control.getStudentList().getStudent(i).getStudentNumber())){
                    studentDetails.setText(control.getStudentList().getStudent(i).toString());
                }
            }
        });

        ////EVENT HANDLERS FOR LAYOUT//////////

        comboBox.setOnAction(actionEvent -> {
            if(comboBox.getSelectionModel().getSelectedItem() == "Grade"){
                Collections.sort(control.getStudentList().getList(), new GradeComparator());
            }
            else{
                Collections.sort(control.getStudentList().getList(), new ModuleComparator());
            }
        });

        viewAll.setOnAction(event -> {
            String myString = "\0";
            for(int i = 0; i < control.getStudentList().getSize(); i++){
                myString += control.getStudentList().getStudent(i).toString() + "\n";
            }
            studentDetails.setPrefHeight(400);  //sets height of the TextArea to 400 pixels
            studentDetails.setPrefWidth(400);
            studentDetails.setText(myString);

        });

        load.setOnAction(actionEvent -> {
            ArrayList<Student> myString;
            myString = control.loadFromFile(control.getStudentList().getList());
            studentDetails.setText(myString.toString());
        });

        removeByNum.setOnMouseClicked(mouseEvent -> {
            removeByNum.clear();
            remove.setText("Remove");
        });

        remove.setOnAction(e -> {
            control.removeStudentFromList(control.getStudentList().remStudentByNumber(removeByNum.getText()));
            remove.setText("Removed");
        });


        return layout;
    }

}
