package com.epam.university_admissions.dao;

import java.util.List;

public interface Dao<T> {

    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T find(int entityKey);

    public List<T> findAll();
}
