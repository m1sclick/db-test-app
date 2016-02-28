package com.vladkrava;

import com.vladkrava.dao.ClientAccountDAO;
import com.vladkrava.dao.ClientDAO;
import com.vladkrava.entity.Client;
import com.vladkrava.entity.ClientAccount;
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
public class ClientAccountDAOTest {

    private @Autowired ClientDAO clientDAO;
    private @Autowired ClientAccountDAO accountDAO;

    @Test
    @Transactional
    public void saveAndSelectTest() throws Exception {
//        create test client object
        Client testClient = new Client();
        testClient.setName("Test Client");

//        save test client object
        int testClientId = clientDAO.save(testClient);

//        create test account object
        ClientAccount testClientAccount = new ClientAccount(testClient, 10f);
        accountDAO.save(testClientAccount);

//        save test account object
        ClientAccount selectedClientAccount = accountDAO.select(testClientId);

//        check result
        Assert.assertEquals(testClientAccount.getClient().getName(), selectedClientAccount.getClient().getName());
        Assert.assertEquals(testClientAccount.getBill(), selectedClientAccount.getBill(), 0.001);
    }

}
