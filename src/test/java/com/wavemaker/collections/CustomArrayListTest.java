package com.wavemaker.collections;
import com.wavemaker.collections.customarraylist.CustomArrayList;
import  com.wavemaker.collections.pojo.Student;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class CustomArrayListTest {
    private static List<Student> customStudentList = null;
    private static Student student1, student2, student3 = null;

    @BeforeClass
    public static void setUp() {
        customStudentList = new CustomArrayList<>();
        student1 = new Student();
        student1.setStudentId("1");
        student1.setFirstName("Roopa");
        student1.setLastName("Aenugu");

        student2 = new Student();
        student2.setStudentId("2");
        student2.setFirstName("kushul Reddy");
        student2.setLastName("Aenugu");

        student3 = new Student();
        student3.setStudentId("3");
        student3.setFirstName("Ashwini");
        student3.setLastName("Donthulapalli");

        // Test equality and hashCode
        System.out.println(student1.equals(student2)); // true
        System.out.println("student1 hashcode: " + student1.hashCode());
        System.out.println("student2 hashcode: " + student2.hashCode());
        System.out.println(student1.equals(student3)); // false
        System.out.println("student3 hashcode: " + student3.hashCode());
        customStudentList.add(student1);
        customStudentList.add(student2);
        customStudentList.add(student3);
        assertEquals("size qulas to  3 after adding", 3, customStudentList.size());
        printStudentList(customStudentList);
    }


    @Test
    public void addIndexTest() {
        Student student4 = new Student();
        student4.setStudentId("4");
        student4.setFirstName("pranathi");
        student4.setLastName("reddy");
        customStudentList.add(0, student4);
        System.out.println("after adding print the studentList");
        printStudentList(customStudentList);
        assertEquals("check the size is equal to 4 after adding at index", 4, customStudentList.size());
        assertTrue("check student4 object is present in customStudentList", customStudentList.contains(student4));


    }

    @Test
    public void removeTest() {
        assertTrue("check student1 object is present in customStudentList", customStudentList.contains(student1));
        customStudentList.remove(student1);
        System.out.println("print the students list after removing particuar object");
        printStudentList(customStudentList);
    }

    @Test
    public void removeIndexTest() {
        assertTrue("check index 1  object is present in customStudentList", customStudentList.contains(customStudentList.get(1)));

        customStudentList.remove(1);
        System.out.println("print the student list after removing obejct at particular index");
        printStudentList(customStudentList);
        // customStudentList.
    }

    @Test
    public void findIndexOfObjectTest() {
        System.out.println("index of the student1 object is " + customStudentList.indexOf(student2));
    }

    @Test
    public void lastIndexOfObjectTest() {
        customStudentList.add(student1);
        printStudentList(customStudentList);
        System.out.println("find the last index of the object: " + customStudentList.lastIndexOf(student1));

    }

    @Test
    public void getObjectTest() {
        System.out.println("get the index 1 object is  " + customStudentList.get(1));
        assertTrue("check the index 1 object is present in customStudentList", customStudentList.contains(customStudentList.get(1)));
    }

    @Test
    public void setObjectTest() {
        Student student5 = new Student("5", "swetha", "siliveru");
        customStudentList.set(1, student5);
        printStudentList(customStudentList);

    }

    @Test
    public void isEmptyTest() {
        System.out.println("check studentList is  empty or not\nfalse for empty\ntrue for not empty\n " + customStudentList.isEmpty());
    }

    @Test
    public void containsAllTest() {
        List<Student> customStudentList2 = new CustomArrayList<>();
        customStudentList2.add(student1);
        customStudentList2.add(student2);
        System.out.println(customStudentList.containsAll(customStudentList2));


    }

    @Test
    public void addAllTest() {
        List<Student> customStudentList2 = new CustomArrayList<>();
        Student student6 = new Student("6", "seetha", "mamidi");
        Student student7 = new Student("7", "latha", "aenugu");
        customStudentList2.add(student6);
        customStudentList2.add(student7);
        System.out.println(customStudentList.addAll(customStudentList2));
        printStudentList(customStudentList);
    }

    @Test
    public void addAllAtIndexTest() {
        List<Student> customStudentList2 = new CustomArrayList<>();
        Student student6 = new Student("6", "seetha", "mamidi");
        Student student7 = new Student("7", "latha", "aenugu");
        customStudentList2.add(student6);
        customStudentList2.add(student7);
        System.out.println(customStudentList.addAll(1, customStudentList2));
        printStudentList(customStudentList);

    }

    @Test
    public void removeAllTest() {
        List<Student> customStudentList2 = new CustomArrayList<>();
        customStudentList2.add(student1);
        customStudentList2.add(student2);
        System.out.println(customStudentList.removeAll(customStudentList2));
        printStudentList(customStudentList);

    }

    @Test
    public void retainAllTest() {
        List<Student> customStudentList2 = new CustomArrayList<>();
        customStudentList2.add(student1);
        customStudentList2.add(student2);
        System.out.println(customStudentList.retainAll(customStudentList2));
        printStudentList(customStudentList);
    }

    @Test
    public void sublistTest() {
        List<Student> studentList = customStudentList.subList(1, 2);
        System.out.println("check the student sublist present in custom student list " + customStudentList.containsAll(studentList));
    }

    @Test
    public void clearTest() {
        System.out.println(customStudentList.size());
        customStudentList.clear();
        for (int i = 0; i < customStudentList.size(); i++) {
            assertEquals("student list is null after performing clear method ", null, customStudentList.get(i));


        }


    }
    @Test
    public void ListIteratorCheck(){
        ListIterator<Student> listIterator = customStudentList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }



    }


    private static void printStudentList(List<Student> list) {
        System.out.println("Current students in the list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("Total count: " + list.size());

    }


}