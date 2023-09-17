/////DANIEL O'DRISCOLL/////

///////////// STUDENT CLASS////////////////

package com.example.assignment1.Model;


import java.util.ArrayList;
import java.util.Collections;

public class Student {

    private String studentName;
    private String studentNumber;
    private String dateOfBirth;
    private ArrayList<String> studentModules;
    private ArrayList<String> studentGrades;
    private int overallGrade;

    public Student(String studentName, String studentNumber, String dateOfBirth, ArrayList<String> studentModules, ArrayList<String> studentGrades){
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.dateOfBirth = dateOfBirth;
        this.studentModules = studentModules;
        this.studentGrades = studentGrades;
        this.overallGrade = 0;

    }

    public String getStudentName(){
        return this.studentName;
    }

    public String getStudentNumber(){
        return this.studentNumber;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public ArrayList<String> getStudentModules(){
        return this.studentModules;

    }
    public ArrayList<String> getStudentGrades(){
        return this.studentGrades;
    }

    public int getStudentOverallGrade(){return this.overallGrade;}


    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public void setStudentNumber(String studentNumber){
        this.studentNumber = studentNumber;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setStudentModules(ArrayList<String> studentModules){
        ArrayList<String> concatenatedList = new ArrayList<>();

        for (String list : studentModules) {
            concatenatedList.addAll(Collections.singleton(list));
        }
        this.studentModules = concatenatedList;
    }

    public void setStudentGrades(ArrayList<String> studentGrades){
        ArrayList<String> concatenatedList = new ArrayList<>();
        for (String list : studentGrades) {
            concatenatedList.addAll(Collections.singleton(list));
        }
        this.studentGrades = concatenatedList;
    }

    public void setStudentOverallGrade(int overallGrade){this.overallGrade = overallGrade;}


    public String toString(){
        return String.format("------------\nName: %s\nStudent No: %s\nD.O.B: %s\n Modules: %s\nGrades: %s\nOverall Grade: %d\n-----------", this.studentName,
                                                                                                                         this.studentNumber,
                                                                                                                         this.dateOfBirth,
                                                                                                                         getStudentModules(),
                                                                                                                         getStudentGrades(),
                                                                                                                         getStudentOverallGrade());
    }
}

