package com.company.controller;

import com.company.exception.NonExistentObjectException;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    Student s1 = new Student(100, "Petronela", "Halip");
    Student s2 = new Student(101, "Ana", "Pop");
    Student s3 = new Student(102, "Cristi", "Popescu");

    Teacher t1 = new Teacher(1000, "Anca", "Bobu");
    Teacher t2 = new Teacher(1001, "Dan", "Irimescu");
    Teacher t3 = new Teacher(1002, "Elsa", "Irin");

    Course c1 = new Course("Databases", t1, 1, 12);
    Course c2 = new Course("Algebra", t1, 28, 6);
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

    @BeforeEach
    void setUp() {
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
        registrationSystem = new RegistrationSystem(courses, students, teachers);
    }

    @Test
    void register() throws NonExistentObjectException {
        try {
            assertTrue(registrationSystem.register(100, "Databases"));
            assertFalse(registrationSystem.register(100, "German"));//the number of credits exceeds the limit
            assertTrue(registrationSystem.register(101, "Algebra"));
            assertFalse(registrationSystem.register(102, "German2"));//testing with a course that doesn't exist in the repository
        } catch (NonExistentObjectException ex) {
            System.out.println(ex);

        }
    }

    @Test
    void retrieveCoursesWithFreePlaces() throws NonExistentObjectException {
        try {
            registrationSystem.register(100, "Databases");
            registrationSystem.register(102, "German");
            assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(0).getCourseName(), "Algebra");
            assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(1).getCourseName(), "Logic");
            assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(2).getCourseName(), "English");
            assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(3).getCourseName(), "German");
            assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().size(), 4);

        } catch (NonExistentObjectException ex) {
            System.out.println(ex);
        }
    }

    @Test
    void retrieveStudentsFromCourse() {
        try{
            registrationSystem.register(100, "Algebra");
            registrationSystem.register(102, "German");
            registrationSystem.register(102, "Algebra");
            registrationSystem.register(101, "English");
            assertEquals(registrationSystem.retrieveStudentsFromCourse("Databases").size(), 0);
            assertEquals(registrationSystem.retrieveStudentsFromCourse("Algebra").size(), 2);
            assertEquals(registrationSystem.retrieveStudentsFromCourse("Logic").size(), 0);
            assertEquals(registrationSystem.retrieveStudentsFromCourse("English").size(), 1);
            assertEquals(registrationSystem.retrieveStudentsFromCourse("German").size(), 1);

        }
        catch(NonExistentObjectException ex)
        {
            System.out.println(ex);
        }
    }

    @Test
    void updateCredits() {
        try{
            registrationSystem.register(100, "Algebra");
            registrationSystem.register(102, "German");
            registrationSystem.register(102, "Algebra");
            registrationSystem.register(101, "English");
            registrationSystem.updateCredits("English",6);
            registrationSystem.updateCredits("Algebra", 4);
            assertEquals(s2.getTotalCredits(),6);
            assertEquals(s3.getTotalCredits(),24);
            assertEquals(s1.getTotalCredits(),4);
        }
        catch(NonExistentObjectException ex)
        {
            System.out.println(ex);
        }
    }

    @Test
    void deleteCourse() {
        try{
            registrationSystem.register(100, "Algebra");
            registrationSystem.register(102, "German");
            registrationSystem.register(102, "Algebra");
            registrationSystem.register(101, "English");
            registrationSystem.register(101, "Algebra");

            registrationSystem.deleteCourse(1000,"Algebra");
            assertEquals(s3.getEnrolledCourses().size(), 1);
            assertEquals(s3.getEnrolledCourses().get(0).getCourseName(),"German");
            assertEquals(s1.getEnrolledCourses().size(),0);
            assertEquals(s2.getEnrolledCourses().size(),1);

            registrationSystem.deleteCourse(1002,"German");
            assertEquals(s2.getEnrolledCourses().size(),1);

        }
        catch(NonExistentObjectException ex)
        {
            System.out.println(ex);
        }
    }
}