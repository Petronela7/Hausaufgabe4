package com.company.repository;

import com.company.model.Student;

public class StudentRepository extends InMemoryRepository<Student> {
    /**
     * @param entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g. id does not exist).
     */
    @Override
    public Student update(Student entity) {
        for(Student s: repoList) {
            if (s.equals(entity)){
                s.setStudentID(entity.getStudentID());
                s.setEnrolledCourses(entity.getEnrolledCourses());
                s.setTotalCredits(entity.getTotalCredits());
                return null;
            }
        }
        return entity;

    }

    /**
     * @param ID of the object searched
     * @return the object if it is found, null otherwise
     */
    public Student findByID(long ID)
    {
        for(Student s: repoList)
            if(s.getStudentID() == ID)
                return s;
        return null;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
