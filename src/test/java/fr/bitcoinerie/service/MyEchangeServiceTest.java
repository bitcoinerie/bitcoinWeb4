package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyEchangeServiceTest {
    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private MyEchangeService myEchangeService;




    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyEchange").executeUpdate();

        transaction.commit();

        session.close();


    }

    @Test
    public void save() {

        myEchangeService.saveEchange( myEchange());
    }

    private MyEchange myEchange() {
        MyEchange echange = new MyEchange();

        return echange;
    }
}
