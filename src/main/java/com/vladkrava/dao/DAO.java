package com.vladkrava.dao;

/**
 * @author Vlad Krava
 */
public interface DAO<U> {
    U save(U o);
    U select(int id);
}
