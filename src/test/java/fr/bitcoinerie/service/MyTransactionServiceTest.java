package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: marjolaine
 * Date: 17/12/13
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyTransactionServiceTest {

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private MyTransactionService myTransactionService;

    @After
    public void cleanDb() {


        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyTransaction").executeUpdate();

        transaction.commit();

        session.close();


    }


    @Test
    public void testSave() throws Exception {

        myTransactionService.save(myTransaction());

    }

    private MyTransaction myTransaction() {
        MyTransaction myTransaction = new MyTransaction();
        myTransaction.setMontant(24);
        myTransaction.setDate_temps(new Date());
        myTransaction.setEmetteur("Paul");
        myTransaction.setRecepteur("Jean");
        return myTransaction;
    }

    @Test
    public void testDelete() throws Exception {

        MyTransaction myTransaction = myTransaction();

        myTransactionService.save(myTransaction);

        myTransactionService.delete(myTransaction.getId_transaction());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyTransaction").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {

        myTransactionService.save(myTransaction());
        myTransactionService.save(myTransaction());

        Assert.assertEquals(2, myTransactionService.findAll().size());

    }

    @Test
    public void testFindByQuery() throws Exception {

        myTransactionService.save(myTransaction());
        myTransactionService.save(myTransaction());

        Assert.assertEquals(2, myTransactionService.findByQuery("Paul").size());
        Assert.assertEquals(0, myTransactionService.findByQuery("Pierre").size());

    }


    @Test
    public void testFindById() throws Exception {

        MyTransaction myTransaction = myTransaction();

        myTransactionService.save(myTransaction);

        Assert.assertEquals("Paul", myTransactionService.findById(myTransaction.getId_transaction()).getEmetteur());

    }

    @Test
    public void testCount() throws Exception {

        myTransactionService.save(myTransaction());
        myTransactionService.save(myTransaction());

        Assert.assertEquals(2, myTransactionService.count());
    }

    @Test
    public void udpate() {
        MyTransaction myTransaction = MyTransaction();

        myTransactionService.update(myTransaction);
        myTransactionService.update(myTransaction);

        Assert.assertEquals(1, myTransactionService.count());
    }

}
