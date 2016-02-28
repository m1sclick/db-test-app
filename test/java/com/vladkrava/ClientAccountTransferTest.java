package com.vladkrava;

import com.vladkrava.dao.ClientAccountDAO;
import com.vladkrava.dao.ClientDAO;
import com.vladkrava.entity.Client;
import com.vladkrava.entity.ClientAccount;
import org.junit.Assert;
import org.junit.Before;
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
public class ClientAccountTransferTest {

    private @Autowired ClientDAO clientDAO;
    private @Autowired ClientAccountDAO accountDAO;

    private ClientAccount senderClientAccount;
    private ClientAccount recipientClientAccount;

    @Before
    @Transactional
    public void init() throws Exception {

//        init start amount for client accounts
        float senderAmount = 10f;
        float recipientAmount = 0f;

//        init sender and recipient clients
        Client senderClient = new Client();
        Client recipientClient = new Client();

        senderClient.setName("Sender Client");
        recipientClient.setName("Recipient Client");

//        save clients
        clientDAO.save(senderClient);
        clientDAO.save(recipientClient);

//        init sender and recipient client accounts
        ClientAccount senderAccount = new ClientAccount(senderClient, senderAmount);
        ClientAccount recipientAccount = new ClientAccount(recipientClient, recipientAmount);


//        save sender and recipient client accounts
        senderClientAccount = accountDAO.save(senderAccount);
        recipientClientAccount = accountDAO.save(recipientAccount);
    }

    @Test
    @Transactional
    public void transfer() throws Exception {

//        select client account objects
        ClientAccount currentSenderClientAccount = accountDAO.select(senderClientAccount.getClient().getId());
        ClientAccount currentRecipientClientAccount = accountDAO.select(recipientClientAccount.getClient().getId());

//        make a money transfer
        currentSenderClientAccount.setBill(currentSenderClientAccount.getBill() - 5f);
        currentRecipientClientAccount.setBill(currentRecipientClientAccount.getBill() + 5f);

//        check user account balance
        Assert.assertEquals(accountDAO.select(senderClientAccount.getClient().getId()).getBill(), 5f, 0.001);
        Assert.assertEquals(accountDAO.select(recipientClientAccount.getClient().getId()).getBill(), 5f, 0.001);
    }
}
