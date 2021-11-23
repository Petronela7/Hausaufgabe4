package com.company.controller;

import com.company.exception.NonExistentObjectException;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;

import java.util.*;

public class RegistrationSystem {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public RegistrationSystem(CourseRepository courseRepository1, StudentRepository studentRepository1, TeacherRepository teacherRepository1) {
        this.courseRepository = courseRepository1;
        this.studentRepository = studentRepository1;
        this.teacherRepository = teacherRepository1;

    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    /**
     * @param ID of a student
     * @param name of a course
     * @return true if the student has been successfully registered, false otherwise
     * @throws NonExistentObjectException if either the student or the object are  not found
     */
    public boolean register(long ID, String name) throws NonExistentObjectException {
        if(studentRepository.findByID(ID) == null || courseRepository.findByName(name) == null)
            throw new NonExistentObjectException("Invalid id and/or name of course");
        Course course = courseRepository.findByName(name);
        Student student = studentRepository.findByID(ID);
        if (course.getNumberStudentsMax() > course.getStudentList().size()) {//verify if there are free places
            if (course.getCredits() + student.getTotalCredits() <= 30) {//verify if the number of credits doesn't exceed the limit
                List<Course> newCourseList = student.getEnrolledCourses();
                newCourseList.add(course);
                student.setEnrolledCourses(newCourseList);//register student into course

                student.setTotalCredits(student.getTotalCredits() + course.getCredits());//update credits

                List<Student> newStudentList = course.getStudentList();
                newStudentList.add(student);
                course.setStudentList(newStudentList);//add to course's list of students
                return true;

            } else {
                System.out.println("If you choose this course, your number of credits will exceed the limit.Choose another course.");
                return false;
            }
        } else {
            System.out.println("There are no free places.Choose from: " + retrieveCoursesWithFreePlaces());
            return false;
        }

    }

    /**
     * @return the list of courses with free places
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> coursesWithFreePlaces = new ArrayList<>();
        for (Course course : courseRepository.findAll())
            if (course.getNumberStudentsMax() > course.getStudentList().size())
                coursesWithFreePlaces.add(course);
        return coursesWithFreePlaces;
    }

    /**
     * @param name of a course
     * @return the list of students attending the specified course
     * @throws NonExistentObjectException
     */
    public List<Student> retrieveStudentsFromCourse(String name) throws NonExistentObjectException {
        if(courseRepository.findByName(name)==null)
            throw new NonExistentObjectException("Course is not found");
        Course course = courseRepository.findByName(name);
        return (ArrayList<Student>) courseRepository.findOne(course).getStudentList();

    }
    /**
     * @return a list of all students
     */
    public List<Student> getAllStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    /**
     * @return a list of all courses
     */
    public List<Course> getAllCourses() {
        return (ArrayList<Course>) courseRepository.findAll();
    }

    /**
     * updates the credits of a course
     * updates the number of credits of student enrolled in that course
     * @param name of a course
     * @param credits is the new number of credits
     */
    public void updateCredits(String name, int credits) throws NonExistentObjectException {
        if(courseRepository.findByName(name) == null)
            throw new NonExistentObjectException("Course not found");
        Course course = courseRepository.findByName(name);
        int difference = course.getCredits() - credits;
        courseRepository.findOne(course).setCredits(credits);
        for (Student s: course.getStudentList()) {
            s.setTotalCredits(s.getTotalCredits() - difference);
        }
    }

    /**
     * Deletes the course
     * @param name - the name of the course being deleted
     * @param id is the teacher deleting the course
     */
    public void deleteCourse (long id,String name) throws NonExistentObjectException {
        if(courseRepository.findByName(name) == null)
            throw new NonExistentObjectException("Course not found");
        Course course = courseRepository.findByName(name);
        teacherRepository.findById(id).getTaughtCourses().remove(course);
        for (Student s: course.getStudentList())
        {
            ArrayList<Course> newList = (ArrayList<Course>) s.getEnrolledCourses();
            newList.remove(course);
            s.setEnrolledCourses(newList);
        }
        courseRepository.delete(course);

    }

    /**
     * Method that sorts courses by name, in alphabetical order
     */
    public void sortCoursesByName() {
        Collections.sort((ArrayList<Course>) courseRepository.findAll(), new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getCourseName().compareTo(o2.getCourseName());
            }
        });

    }

    /**
     * Method that sorts students by number of enrolled courses
     */
    public void sortStudentsByNumberOfCourses()
    {
        Collections.sort((ArrayList<Student>) studentRepository.findAll(), new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getEnrolledCourses().size() - o2.getEnrolledCourses().size();
            }
        });
    }
    /**
     * Method that filters students who have the maximum number of credits(30)
     * Solving this using streams and lambdas
     */
    public List<Student> filterStudentsWithMaxCredits()
    {
        List<Student> students = new ArrayList<>();
        students.addAll((Collection<? extends Student>) studentRepository.findAll());
        students.stream().filter(x -> x.getTotalCredits() == 30);
        return students;

    }
    /**
     * Method that filters courses which have the no students enrolled
     * Solving this using streams and lambdas
     */

    public List<Course> filterCoursesWithNoStudents()
    {
        List<Course> courses = new ArrayList<>();
        courses.addAll((Collection<? extends Course>) courseRepository.findAll());
        courses.stream().filter(x -> x.getStudentList().size() == 0);
        return courses;

    }
}

