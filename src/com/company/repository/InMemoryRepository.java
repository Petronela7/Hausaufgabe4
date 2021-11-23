package com.company.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepository<E> implements ICrudRepository<E> {
    protected List<E> repoList;

    public InMemoryRepository() {
        this.repoList = new ArrayList<>();
    }

    /**
     * @param entity-the entity to be returned
     * @return the entity , null - if there is no entity
     */
    @Override
    public E findOne(E entity) {
        for (E e : repoList) {
            if (e.equals(entity))
                return entity;
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<E> findAll() {
        return repoList;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity(in case the entity is already saved)
     */
    @Override
    public E save(E entity) {
        for (E e : repoList) {
            if (e.equals(entity))
                return entity;
        }
        repoList.add(entity);
        return null;
    }

    /**
     * removes the entity
     * @param entity must be not null
     * @return the removed entity or null if there is no entity
     */
    @Override
    public E delete(E entity) {
        for (E e : repoList) {
            if (e.equals(entity)) {
                repoList.remove(entity);
                return entity;
            }
        }
        return null;

    }


}
