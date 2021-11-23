package com.company.repository;
import com.company.model.Course;

import java.util.List;

public class CourseRepository extends InMemoryRepository<Course> {

    public CourseRepository() {
        super();
    }

    /**
     * @param entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity
     */
    @Override
    public Course update(Course entity) {
        for (Course c : repoList) {
            if (c.equals(entity)) {
                c.setCredits(entity.getCredits());
                c.setNumberStudentsMax(entity.getNumberStudentsMax());
                c.setTeacher(entity.getTeacher());
                c.setStudentList(entity.getStudentList());
                return null;
            }
        }
        return entity;
    }

    public Course findByName(String name) {
        for (Course c : repoList) {
            if (c.getCourseName().equals(name))
                return c;
        }
        return null;
    }

}