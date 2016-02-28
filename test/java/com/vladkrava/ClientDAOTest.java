package com.vladkrava;

import com.vladkrava.dao.ClientDAO;
import com.vladkrava.entity.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * @author Vlad Krava
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-spring-config.xml")
public class ClientDAOTest {

    @Autowired private ClientDAO clientDAO;

    @Test
    @Transactional
    public void saveAndSelect() throws Exception {

//        create test client with name 'Test Client'
        Client savedClient = new Client();
        savedClient.setName("Test Client");

//        save client in test DB and getting record id
        int savedClientId = clientDAO.save(savedClient);

//        retrieving saved client from DB using id
        Client selectedClient = clientDAO.select(savedClientId);

//        compering clients names
        Assert.assertEquals(selectedClient.getName(), savedClient.getName());
    }
}
