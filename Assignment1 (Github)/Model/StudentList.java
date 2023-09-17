/////DANIEL O'DRISCOLL/////

///////////// STUDENT LIST CLASS////////////////

package com.example.assignment1.Model;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> students;

    public StudentList(){
        students = new ArrayList<>();
    }

    public ArrayList<Student> getList () {
        return students;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void remCar(int i)
    {
        if ((i>-1) && (i < students.size()))
            students.remove(i);
    }

    public String remStudentByNumber(String num)
    {
        for (int i = 0 ; i< students.size(); i++)
            if (getStudent(i).getStudentNumber().equals(num))
                students.remove(i);
        return num;
    }


    public Student getStudent(int i)
    {
        if ((i>-1) && (i < students.size()))
            // check that the index is valid
            return students.get(i);
        return null;
    }

    public int getSize (){
        return students.size();
    }
}

