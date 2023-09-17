package com.example.assignment1.Model;

import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s2.getStudentOverallGrade(), s1.getStudentOverallGrade());
    }
}
