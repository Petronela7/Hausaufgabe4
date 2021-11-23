package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Teacher represents a teacher object
 */
public class Teacher extends Person {
    private long teacherID;
    private String firstName;
    private String lastName;
    private List<Course> taughtCourses;

    public Teacher( long teacherID,String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.teacherID = teacherID;
        this.taughtCourses = new ArrayList<>();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(long teacherID) {
        this.teacherID = teacherID;
    }

    public List<Course> getTaughtCourses() {
        return taughtCourses;
    }

    public void setTaughtCourses(List<Course> taughtCourses) {
        this.taughtCourses = taughtCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherID == teacher.teacherID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

