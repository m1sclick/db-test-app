package com.vladkrava.dao;

import com.vladkrava.entity.Client;
import com.vladkrava.utils.CustomDAOSupport;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * @author Vlad Krava
 */

@Repository
public class ClientDAO extends CustomDAOSupport {

    public int save(Client client) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().save(client);
        return client.getId();
    }

    public Client select(int id) throws ObjectNotFoundException {
        return (Client) getHibernateTemplate().getSessionFactory().getCurrentSession().get(Client.class, id);
    }
}
