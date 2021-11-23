package com.company.view;

import com.company.controller.RegistrationSystem;
import com.company.exception.NonExistentObjectException;
import com.company.model.Course;
import com.company.repository.CourseRepository;


import java.util.Scanner;
/**
 * Class UI displays a menu on the console
 * Uses controller that communicates with the repository and a Scanner for user input;
 */
public class UI {
    private final RegistrationSystem registrationSystem;
    private final Scanner input = new Scanner(System.in);


    public UI(RegistrationSystem registrationSystem) {
        this.registrationSystem = registrationSystem;
    }
    /**
     * Method displays the menu
     * User must choose a number between 1-10 and 11 exits the app
     * @throws NonExistentObjectException if the user inputs the data of an inexistent object
     */
    public void show() throws NonExistentObjectException
    {
        while(true)
        {
            System.out.println("---------Menu----------");
            System.out.print("""
                    1.Register student to course
                    2.Courses with free places 
                    3.Students enrolled in a course 
                    4.All courses
                    5.Update credits 
                    6.Delete course
                    7.Courses sorted by name
                    8.Students sorted by number of courses taken
                    9.Students with 30 credits
                    10.Courses with no students enrolled
                    11.Quit
                    Choose option : \n
                    """
            );
            int ok = 1;
            int option = input.nextInt();
            switch (option)
            {
                case 1://register students to course
                    System.out.print("ID:\n");
                    long studentID = input.nextLong();
                    System.out.print("Name of course:\n");
                    String name = input.next();
                    registrationSystem.register(studentID, name);
                    break;

                case 2://courses with free places
                    System.out.println(registrationSystem.retrieveCoursesWithFreePlaces());
                    break;

                case 3://get students from course
                    System.out.println("Course name:");
                    name = input.next();
                    System.out.println(registrationSystem.retrieveStudentsFromCourse(name));
                    break;
                case 4://show all courses
                    System.out.println(registrationSystem.getAllCourses());
                    break;
                case 5://update number of credits
                    System.out.println("Name of the course:\n");
                    name = input.next();
                    System.out.println("Updated number of credits:\n");
                    int credits = input.nextInt();
                    registrationSystem.updateCredits(name, credits);
                    break;
                case 6://a course is being deleted by a teacher
                    System.out.println("ID of teacher who is deleting the course");
                    long id = input.nextLong();
                    System.out.print("Course you want to delete");
                    name = input.next();
                    registrationSystem.deleteCourse(id,name);
                    break;
                case 7://sort courses by name
                    registrationSystem.sortCoursesByName();
                    System.out.println(registrationSystem.getAllCourses());
                    break;
                case 8://sort students by number of courses they are enrolled in
                    registrationSystem.sortStudentsByNumberOfCourses();
                    System.out.println(registrationSystem.getAllStudents());
                    break;
                case 9://students who have 30 credits
                    System.out.println( registrationSystem.filterStudentsWithMaxCredits());
                case 10://courses with no students
                    System.out.println(registrationSystem.filterCoursesWithNoStudents());
                    break;
                case 11:
                    ok=0;
                    break;

            }
            if(ok==0)
                break;
        }
    }

}
