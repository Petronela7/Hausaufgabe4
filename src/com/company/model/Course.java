package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Course represents a course object
 */
public class Course {
    private String courseName;
    private Teacher teacher;
    private int numberStudentsMax;
    private int credits;
    private List<Student> studentList;

    public Course(String courseName, Teacher teacher, int numberStudentsMax, int credits) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.numberStudentsMax = numberStudentsMax;
        this.credits = credits;
        this.studentList = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getNumberStudentsMax() {
        return numberStudentsMax;
    }

    public int getCredits() {
        return credits;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setNumberStudentsMax(int numberStudentsMax) {
        this.numberStudentsMax = numberStudentsMax;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
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
        Course course = (Course) o;
        return Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, teacher, numberStudentsMax, credits, studentList);
    }

    /**
     * @return the object in a string format
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", numberStudentsMax=" + numberStudentsMax +
                ", credits=" + credits +
                '}';
    }
}

