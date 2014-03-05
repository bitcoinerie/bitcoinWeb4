package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
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
public class MyEchangeServiceTest {

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private MyTransactionService myEchangeService;

    private MyUser Paul;
    private MyUser Jean;
    private Date dateTest = new Date();

    @After
    public void cleanDb() {


        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyEchange").executeUpdate();

        transaction.commit();

        session.close();


    }


 /*   @Test
    public void testSave() throws Exception {
        System.out.println("1 >>>>>>>>");
        myEchangeService.saveEchange(myEchange(( 100.F,Paul, Jean));

    } */

    private MyEchange myEchange (Float amount) {
        System.out.println("2 >>>>>>>>");
        MyEchange myEchange = new MyEchange();
        myEchange.setMontant(amount);
        myEchange.setDate_derniere_modification(dateTest);

        Jean =  new MyUser("Jean", "Kevin", 100);



        Paul =  new MyUser("Paul", "Hidalgo", 200);


        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(Jean);
        session.save(Paul);

        transaction.commit();

        session.close();

        myEchange.setEmetteur(Paul);
        myEchange.setRecepteur(Jean);

        System.out.println("3 >>>>>>>>");
        return myEchange;
    }

    @Test
    public void testDelete() throws Exception {

        MyEchange myEchange = myEchange( 200.F);

        myEchangeService.saveEchange(myEchange);

        myEchangeService.deleteEchange(myEchange.getId_echange());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyTransaction").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {

        myEchangeService.saveEchange(myEchange( 230.F));
        myEchangeService.saveEchange(myEchange( 400.F));

        Assert.assertEquals(2, myEchangeService.findAllTransaction().size());

    }

    @Test
    public void testFindByQuery() throws Exception {

        myEchangeService.saveEchange(myEchange( 300.F));
        myEchangeService.saveEchange(myEchange( 450.F));

        System.out.println(dateTest.toString());

        //Assert.assertEquals(0, myTransactionService.findBEmetteur(dateTest ).size());

    }
}
