/////DANIEL O'DRISCOLL/////

///////////// MAIN JAVA FILE////////////////

package com.example.assignment1;

import com.example.assignment1.Controller.StudentController;
import com.example.assignment1.View.AddModuleGrade;
import com.example.assignment1.View.listStudentsAndDetails;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {

    private Label studentName, studentNo, studentDOB;
    private TextField nameText, numberText, dobText, tab2Text, removeByNum;
    private Button add, remove, list, save, exit;
    private TextArea studentDetails;
    private ListView<String> studentListView;
    protected StudentController control;




    @Override
    public void start(Stage stage){
        try{
/////////////INITIALIZING VARIABLES////////////
            control = new StudentController();
            studentName = new Label("Name: ");
            nameText = new TextField();
            studentNo = new Label("Student No: ");
            numberText = new TextField();
            studentDOB = new Label("D.O.B: ");
            dobText = new TextField();
            add = new Button("Add");
            list = new Button("List");
            studentDetails = new TextArea();
            save = new Button("Save");
            exit = new Button("Exit");
            tab2Text = new TextField("Update students Modules/Grades: ");
            tab2Text.setPrefWidth(200);
            studentListView = new ListView<>();

            Group root = new Group();
            HBox h1 = new HBox(10);
            h1.getChildren().addAll(studentName, nameText);
            HBox h2 = new HBox(10);
            h2.getChildren().addAll(studentNo, numberText);
            HBox h3 = new HBox(10);
            h3.getChildren().addAll(studentDOB, dobText);
            HBox h4 = new HBox(10);
            h4.getChildren().addAll(add);
            HBox h5 = new HBox(10);
            h5.getChildren().addAll(list, studentDetails);
            HBox h6 = new HBox(10);
            h6.getChildren().addAll(save, exit);
            VBox layout = new VBox(10);
            layout.getChildren().addAll(h1, h2, h3, h4, h6);

            Tab tab1 = new Tab("Tab 1", layout);
            HBox h7 = new HBox(tab2Text);
            HBox h8 = new HBox(studentListView);
            VBox tab2Layout = new VBox(h7, h8);
            Tab tab2 = new Tab("Tab 2", tab2Layout);
            Tab tab3 = new Tab("Tab 3", h5);


/////////// EVENT HANDLERS ////////////////////////////////

            add.setOnAction(actionEvent -> {
                control.addStudentToList(nameText.getText(), numberText.getText(), dobText.getText(), null, null);
                nameText.clear();
                numberText.clear();
                dobText.clear();
                add.setText("Added");
            });

            nameText.setOnMouseClicked(e -> add.setText("Add"));

            save.setOnAction(e -> control.saveToFile());

            exit.setOnAction(e-> System.exit(0));



            tab2.setOnSelectionChanged(event -> {
                for(int i = 0; i < control.getStudentList().getSize(); i++){
                    String studentID = control.getStudentList().getStudent(i).getStudentNumber() + ": " + control.getStudentList().getStudent(i).getStudentName();
                    studentListView.getItems().add(studentID);
                }
                tab2.setContent(tab2Layout);
            });

            studentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            studentListView.setOnMouseClicked(mouseEvent -> {
                AddModuleGrade studentPage = new AddModuleGrade(control, studentListView);
                VBox v1 = new VBox(studentPage.display(studentListView.getSelectionModel().getSelectedItem()));
                tab2.setContent(v1);

                studentPage.updateButton.setOnMouseClicked(mouseEvent2 -> {
                    studentPage.updateButton.setText("Updated");
                });

                studentPage.backButton.setOnMouseClicked(mouseEvent3 -> {
                    tab2.setContent(studentListView);
                });
            });



            tab1.setOnSelectionChanged(event -> studentListView.getItems().clear());

            tab3.setOnSelectionChanged(event -> {
                studentListView.getItems().clear();
                studentListView.setStyle("#81c483");
              listStudentsAndDetails listPlusDetails = new listStudentsAndDetails(control, studentListView);
              VBox v2 = new VBox(listPlusDetails.listButtonAction());
              tab3.setContent(v2);
            });

//////////ADDING TABS AND ROOT TO SCENE////////////////////////
            TabPane tabList = new TabPane(tab1, tab2, tab3);
            BorderPane mainPane = new BorderPane();
            mainPane.setCenter(tabList);
            root.getChildren().add(mainPane);

            Scene scene = new Scene(root, 600, 300);
            scene.setFill(Color.web("#81c483"));
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}