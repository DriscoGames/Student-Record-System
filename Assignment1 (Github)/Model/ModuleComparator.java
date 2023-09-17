package com.example.assignment1.Model;

import java.util.Comparator;
import java.util.List;

public class ModuleComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        List<String> modules1 = s1.getStudentModules();
        List<String> modules2 = s2.getStudentModules();
        int minLength = Math.min(modules1.size(), modules2.size());
        for (int i = 0; i < minLength; i++) {
            int compareResult = modules1.get(i).compareTo(modules2.get(i));
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return Integer.compare(modules1.size(), modules2.size());
    }
}