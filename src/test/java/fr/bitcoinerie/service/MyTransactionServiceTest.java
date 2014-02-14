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

        myTransactionService.saveTransaction(myTransaction());

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

        myTransactionService.saveTransaction(myTransaction);

        myTransactionService.deleteTransaction(myTransaction.getId_transaction());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyTransaction").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {

        myTransactionService.saveTransaction(myTransaction());
        myTransactionService.saveTransaction(myTransaction());

        Assert.assertEquals(2, myTransactionService.findAllTransaction().size());

    }

    @Test
    public void testFindByQuery() throws Exception {

        myTransactionService.saveTransaction(myTransaction());
        myTransactionService.saveTransaction(myTransaction());

        Assert.assertEquals(2, myTransactionService.findByQueryTransaction("Paul").size());
        Assert.assertEquals(0, myTransactionService.findByQueryTransaction("Pierre").size());

    }


    @Test
    public void testFindById() throws Exception {

        MyTransaction myTransaction = myTransaction();

        myTransactionService.saveTransaction(myTransaction);

        Assert.assertEquals("Paul", myTransactionService.findByIdTransaction(myTransaction.getId_transaction()).getEmetteur());

    }

    @Test
    public void testCount() throws Exception {

        myTransactionService.saveTransaction(myTransaction());
        myTransactionService.saveTransaction(myTransaction());

        Assert.assertEquals(2, myTransactionService.countTransaction());
    }

    /*
    @Test
    public void udpate() {
<<<<<<< HEAD
        MyTransaction myTransaction = new MyTransaction();
=======
        MyTransaction myTransaction = myTransaction();
>>>>>>> 0d22225a29cb189f664d2c3da323390d1071d0c7

        myTransactionService.save(myTransaction);
        myTransactionService.updateTransaction(myTransaction);

        Assert.assertEquals(1, myTransactionService.countTransaction());
    }
    */

}
