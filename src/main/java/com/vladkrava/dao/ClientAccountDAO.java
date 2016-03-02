package com.vladkrava.dao;

import com.vladkrava.entity.ClientAccount;
import com.vladkrava.utils.CustomDAOSupport;
import org.springframework.stereotype.Repository;

/**
 * @author Vlad Krava
 */

@Repository
public class ClientAccountDAO extends CustomDAOSupport implements DAO<ClientAccount>{

    public ClientAccount select(int id) {
        try {
            return (ClientAccount) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createQuery("SELECT ca FROM ClientAccount ca INNER JOIN ca.client c WHERE c.id = :id").setParameter("id", id).list().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public ClientAccount save(ClientAccount c) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().save(c);
        return c;
    }
}
