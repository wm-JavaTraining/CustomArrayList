package com.wavemaker.collections;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;

    public Student(){}

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(studentId, firstName, lastName);
//    }
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;

    Student student = (Student) obj;
    return (this.studentId != null  && this.studentId.equals(student.studentId))&&
            (this.firstName != null && this.firstName.equals(student.firstName))&&
            (this.lastName != null && this.lastName.equals(student.lastName));
}
    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Student ID: " + studentId + ", First Name: " + firstName + ", Last Name: " + lastName;
    }






}
