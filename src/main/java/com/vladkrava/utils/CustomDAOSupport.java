package com.vladkrava.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * @author Vlad Krava
 */
public abstract class CustomDAOSupport extends HibernateDaoSupport {

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
}
