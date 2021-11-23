package com.company.repository;

import com.company.model.Teacher;

public class TeacherRepository extends InMemoryRepository<Teacher> {
    /**
     * @param entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g. id does not exist).
     */
    @Override
    public Teacher update(Teacher entity) {
        for(Teacher t : repoList)
        {
            if(t.equals(entity))
            {
                t.setTaughtCourses(entity.getTaughtCourses());
                return null;
            }
        }
        return entity;
    }

    public Teacher findById(long id)
    {
        for(Teacher t: repoList)
            if(t.getTeacherID() == id)
                return t;
        return null;
    }
}
