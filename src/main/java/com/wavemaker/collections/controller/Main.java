package com.wavemaker.collections.controller;

import com.wavemaker.collections.CustomList;
import com.wavemaker.collections.pojo.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setStudentId("1");
        student1.setFirstName("Roopa");
        student1.setLastName("Aenugu");

        Student student2 = new Student();
        student2.setStudentId("2");
        student2.setFirstName("kushul Reddy");
        student2.setLastName("Aenugu");

        Student student3 = new Student();
        student3.setStudentId("3");
        student3.setFirstName("Ashwini");
        student3.setLastName("Donthulapalli");

        // Test equality and hashCode
        System.out.println(student1.equals(student2)); // true
        System.out.println("student1 hashcode: " + student1.hashCode());
        System.out.println("student2 hashcode: " + student2.hashCode());
        System.out.println(student1.equals(student3)); // false
        System.out.println("student3 hashcode: " + student3.hashCode());
        List<Student> customStudentList = new CustomList<>();
        customStudentList.add(student1);
        customStudentList.add(student2);
        customStudentList.add(student3);
        for (int i = 0; i < customStudentList.size(); i++) {
            System.out.println(customStudentList.get(i));
        }
        System.out.println(customStudentList.contains(student1));
        System.out.println("check the student lis is empty or not " + customStudentList.isEmpty());
        System.out.println("total count of the students " + customStudentList.size());
        System.out.println("The index of the studnt2 " + customStudentList.indexOf(student2));
        System.out.println("remove successfully studen2 " + customStudentList.remove(student2));
        System.out.println("total count of the students " + customStudentList.size());
        try {
            System.out.println("Get student1: " + customStudentList.get(12));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error getting student: Index out of bounds - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error getting student: " + e.getMessage());
        }
        try {
            Student student4 = new Student("4", "nandu", "errolla");
            System.out.println("Replace student1 with student3: " + customStudentList.set(0, student4));
            System.out.println("Get updated student at index 0: " + customStudentList.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error setting student: Index out of bounds - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error setting student: " + e.getMessage());
        }
        try {
            System.out.println("remove student by index " + customStudentList.remove(0));
            System.out.println("get student at index 0 " + customStudentList.get(0));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error setting student: Index out of bounds - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error setting student: " + e.getMessage());
        }
        System.out.println("total count of the students " + customStudentList.size());
        try {
            System.out.println("add student by index ");
            //customStudentList.add(1,student1);
            customStudentList.add(1, student3);
            System.out.println("get student at index 1 " + customStudentList.get(1));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error setting student: Index out of bounds - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error setting student: " + e.getMessage());
        }
        printStudentList(customStudentList);
        List<Student> customStudentList2 = new CustomList<>();
        Student student5 = new Student("5", "harshini", "mamulla");
        customStudentList2.add(student5);
        Student student6 = new Student("6", "lavanya", "gurijala");
        customStudentList2.add(student6);

        System.out.println("check customList2 present in customList " + customStudentList.containsAll(customStudentList2));
        System.out.println(customStudentList.addAll(customStudentList2));
        printStudentList(customStudentList);
        printStudentList(customStudentList2);
        System.out.println(customStudentList.addAll(1, customStudentList2));

        printStudentList(customStudentList);

        System.out.println(customStudentList.removeAll(customStudentList2));
        printStudentList(customStudentList);
        System.out.println(customStudentList.retainAll(customStudentList2));
        printStudentList(customStudentList);
        System.out.println(customStudentList.toArray());
        customStudentList.clear();


    }

    private static void printStudentList(List<Student> list) {
        System.out.println("Current students in the list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("Total count: " + list.size());
    }

}