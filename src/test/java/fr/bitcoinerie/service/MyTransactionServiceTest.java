package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
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

    @Inject
    private MyUserService myUserService;

    private MyUser Paul;
    private MyUser Jean;
    private Date dateTest = new Date();

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
        System.out.println("1 >>>>>>>>");
        myTransactionService.saveTransaction(myTransaction((double) 100));

    }

    private MyTransaction myTransaction(double amount) {
        System.out.println("2 >>>>>>>>");
        MyTransaction myTransaction = new MyTransaction();
        myTransaction.setMontant(amount);
        myTransaction.setDate_temps(dateTest);

        Jean =  new MyUser("Jean", "Kevin", 100);
        Jean.getListe_depenses().add(myTransaction);


        Paul =  new MyUser("Paul", "Hidalgo", 200);
        Paul.getListe_recettes().add(myTransaction);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(Jean);
        session.save(Paul);

        transaction.commit();

        session.close();

        myTransaction.setEmetteur(Paul);
        myTransaction.setRecepteur(Jean);

        System.out.println("3 >>>>>>>>");
        return myTransaction;
    }

    @Test
    public void testDelete() throws Exception {

        MyTransaction myTransaction = myTransaction((double) 200);

        myTransactionService.saveTransaction(myTransaction);

        myTransactionService.deleteTransaction(myTransaction.getId_transaction());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyTransaction").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {

        myTransactionService.saveTransaction(myTransaction((double) 230));
        myTransactionService.saveTransaction(myTransaction((double) 400));

        Assert.assertEquals(2, myTransactionService.findAllTransaction().size());

    }

    @Test
    public void testFindByQuery() throws Exception {

        myTransactionService.saveTransaction(myTransaction((double) 300));
        myTransactionService.saveTransaction(myTransaction((double) 450));

        System.out.println(dateTest.toString());

        Assert.assertEquals(2, myTransactionService.findByDateTransaction(dateTest, dateTest).size());
        Assert.assertEquals(1, myTransactionService.findByAmountLargerTransaction((double) 300).size());
        Assert.assertEquals(1, myTransactionService.findByAmountSmallerTransaction((double) 400).size());
        Assert.assertEquals(1, myTransactionService.findByAmountEqualsTransaction((double) 300).size());

        Assert.assertEquals(2, myTransactionService.findByRecepterTransaction("Jean").size());

        Assert.assertEquals(2, myTransactionService.findByEmetterTransaction("Paul").size());
        //Assert.assertEquals(0, myTransactionService.findByDateTransaction(dateTest ).size());

    }

//    @Test
//    public void updateMontant() throws Exception {
//        MyTransaction myTransaction = myTransaction((double)300);
//        myTransactionService.saveTransaction(myTransaction);
//
//        Assert.assertEquals(-200, myTransactionService.findByIdTransaction(myTransaction.getId_transaction()));
//        myTransactionService.saveTransaction(myTransaction((double) 300));
//
//    }


    @Test
    public void testFindById() throws Exception {

        MyTransaction myTransaction = myTransaction((double)340);

        myTransactionService.saveTransaction(myTransaction);

        Assert.assertEquals(myTransaction.getId_transaction(), myTransactionService.findByIdTransaction(myTransaction.getId_transaction()).getId_transaction() );
        //Assert.assertEquals(Paul, myTransactionService.findByIdTransaction(myTransaction.getId_transaction()).getEmetteur());

    }

    @Test
    public void testFindByEmetterAndRecepteerId() {
        MyTransaction myTransaction = myTransaction(50.);
        myTransactionService.saveTransaction(myTransaction);

        Assert.assertEquals(1, myTransactionService.findByEmetterId(Paul.getId_user()).size());
        Assert.assertEquals(0, myTransactionService.findByEmetterId(Jean.getId_user()).size());
        Assert.assertEquals(0, myTransactionService.findByRecepterId(Paul.getId_user()).size());
        Assert.assertEquals(1, myTransactionService.findByRecepterId(Jean.getId_user()).size());
    }

    @Test
    public void testFindByUser() {
        MyUser user1 = new MyUser("Nicolas", "Sarkozy", 50.);
        MyUser user2 = new MyUser("François", "Hollande", 20.);
        MyUser user3 = new MyUser("Dominique", "De Villepin", 30.);
        myUserService.save(user1);
        myUserService.save(user2);
        myUserService.save(user3);

        MyTransaction trans1 = new MyTransaction(50., new Date(), user1, user2);
        MyTransaction trans2 = new MyTransaction(50., new Date(), user2, user1);
        MyTransaction trans3 = new MyTransaction(50., new Date(), user1, user3);
        myTransactionService.saveTransaction(trans1);
        myUserService.doTransaction(trans1);
        myTransactionService.saveTransaction(trans2);
        myUserService.doTransaction(trans2);
        myTransactionService.saveTransaction(trans3);
        myUserService.doTransaction(trans3);

        Assert.assertEquals(3, myTransactionService.findByUser(user1.getId_user()).size());
        Assert.assertEquals(2, myTransactionService.findByUser(user2.getId_user()).size());
        Assert.assertEquals(1, myTransactionService.findByUser(user3.getId_user()).size());

    }

    @Test
    public void testCount() throws Exception {

        myTransactionService.saveTransaction(myTransaction((double) 390));
        myTransactionService.saveTransaction(myTransaction((double) 280));

        Assert.assertEquals(2, myTransactionService.countTransaction());
    }

    /*
    @Test
    public void udpate() {
        MyTransaction myTransaction = new MyTransaction();

        MyTransaction myTransaction = myTransaction();


        myTransactionService.save(myTransaction);
        myTransactionService.updateTransaction(myTransaction);

        Assert.assertEquals(1, myTransactionService.countTransaction());
    }
    */

}
