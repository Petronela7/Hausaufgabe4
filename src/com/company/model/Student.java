package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Student represents a Student object
 */
public class Student extends Person{
    private long studentID;
    private String firstName;
    private String lastname;
    private int totalCredits;
    private List<Course> enrolledCourses;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Student(long studentID, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastname = lastName;
        this.studentID = studentID;
        this.totalCredits = 0;
        this.enrolledCourses = new ArrayList<>();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     *
     * @param o object to be compared
     * @return true or false, depending on whether objects are equal or not
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, totalCredits, enrolledCourses);
    }

    /**
     * @return the object in a string format
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalCredits=" + totalCredits +
                '}';
    }
}
