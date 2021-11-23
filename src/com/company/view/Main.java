package com.company.view;

import com.company.controller.RegistrationSystem;
import com.company.exception.NonExistentObjectException;
import com.company.exception.NonExistentObjectException;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.*;
import com.company.view.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NonExistentObjectException {

        Student s1 = new Student(100, "Petronela", "Halip");
        Student s2 = new Student(101, "Ana", "Pop");
        Student s3 = new Student(102, "Cristi", "Popescu");

        Teacher t1 = new Teacher(1000, "Anca", "Bobu");
        Teacher t2 = new Teacher(1001, "Dan", "Irimescu");
        Teacher t3 = new Teacher(1002, "Elsa", "Irin");

        Course c1 = new Course("Databases", t1, 1, 12);
        Course c2 = new Course("Algebra", t1, 28, 5);
        Course c3 = new Course("Logic", t2, 30, 15);
        Course c4 = new Course("English", t3, 25, 5);
        Course c5 = new Course("German", t3, 28, 20);
        Course c6 = new Course("German2", t3, 28, 20);

        List<Course> coursesT1 = new ArrayList<>();
        List<Course> coursesT2 = new ArrayList<>();
        List<Course> coursesT3 = new ArrayList<>();
        CourseRepository courses = new CourseRepository();
        StudentRepository students = new StudentRepository();
        TeacherRepository teachers = new TeacherRepository();
        RegistrationSystem registrationSystem;

        coursesT1.add(c1);
        coursesT1.add(c2);
        t1.setTaughtCourses(coursesT1);


        coursesT2.add(c3);
        t2.setTaughtCourses(coursesT2);


        coursesT3.add(c4);
        coursesT3.add(c5);
        t3.setTaughtCourses(coursesT3);

        teachers.save(t1);
        teachers.save(t2);
        teachers.save(t3);

        courses.save(c1);
        courses.save(c2);
        courses.save(c3);
        courses.save(c4);
        courses.save(c5);

        students.save(s1);
        students.save(s2);
        students.save(s3);
        registrationSystem = new RegistrationSystem(courses, students,teachers);

        //System.out.println(registrationSystem.retrieveCoursesWithFreePlaces());
        UI userInterface = new UI(registrationSystem);
        userInterface.show();

    }
}

