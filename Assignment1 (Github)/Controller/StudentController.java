/////DANIEL O'DRISCOLL/////

///////////// STUDENT CONTROLLER////////////////

package com.example.assignment1.Controller;

import com.example.assignment1.Model.Student;
import com.example.assignment1.Model.StudentList;

import java.io.*;
import java.util.ArrayList;

public class StudentController {
    private StudentList studentList;

    public StudentController(){
        // constructor
        studentList = new StudentList();
    }

    public StudentList getStudentList() {

        return studentList;
    }

    public void addStudentToList(String sName,String sNum, String sDOB, ArrayList<String> sModules, ArrayList<String> sGrades)
    {
        Student s = new Student(sName, sNum, sDOB, sModules, sGrades);
        studentList.addStudent(s);
    }

    public String getListStudent()
    {
        String allStudent="\0";
        if (studentList.getSize() == 0) {
            return "The list is currently empty";
        }
        else {
            for (int i = 0;i<studentList.getSize();i++)
            {
                //allStudent = allStudent+ "I am a car of type " + studentList.getStudent(i).getStudentName() + studentList.getStudent(i).getStudentNumber() + studentList.getStudent(i).getDateOfBirth() + studentList.getStudent(i).getStudentModules() + studentList.getStudent(i).getStudentGrades() + "\n";
                allStudent = allStudent + studentList.getStudent(i).toString();
            }
            return allStudent;
        }
    }

    public void removeStudentFromList(String sNum) {

        studentList.remStudentByNumber(sNum);
    }

    public void saveToFile(){
        try {
            System.out.println(studentList);
            FileOutputStream fileOut = new FileOutputStream("students.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(studentList);
            out.close();
            fileOut.close();
            System.out.println("Saved the file!");
        } catch (IOException e) {
            System.out.println("File not present!");
        }
    }

    public ArrayList<Student> loadFromFile(ArrayList<Student> studentList){
        try {
            FileInputStream fileIn = new FileInputStream("students.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            studentList = (ArrayList<Student>) in.readObject();
            in.close();
            fileIn.close();
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println("File not Present!");
        }
        return studentList;
    }

    public void Update(String student, ArrayList<String> module, ArrayList<String> Grade){
        String id[] = student.split(":");
        for(int i = 0; i < studentList.getSize(); i++){
            if(id[0].equals(studentList.getStudent(i).getStudentNumber())){
                studentList.getStudent(i).setStudentModules(module);
                studentList.getStudent(i).setStudentGrades(Grade);
            }
        }
    }

    public void calculateGradesModules(String student, ArrayList<String> grades, ArrayList<String> modules){
        String id[] = student.split(":");
        int[] gradesInt = new int[100];
        int overallGrade = 0;
        int i = 0;
        for(String g : grades){
            gradesInt[i] = Integer.valueOf(g);
            overallGrade += gradesInt[i];
            i++;

        }
        for( i = 0; i < studentList.getSize(); i++){
            if(id[0].equals(studentList.getStudent(i).getStudentNumber())){
                studentList.getStudent(i).setStudentOverallGrade(overallGrade);
            }
        }
    }
}