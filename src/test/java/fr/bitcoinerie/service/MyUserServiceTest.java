package fr.bitcoinerie.service;

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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyUserServiceTest {

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private MyUserService myUserService;

    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyUser").executeUpdate();

        transaction.commit();

        session.close();

    }

    private MyUser myUser(){
        MyUser myUser = new MyUser();

        myUser.setPrenom("Thierry");
        myUser.setNom("Beccaro");
        myUser.setEmail("tbeccaro@francetv.fr");
        myUser.setLogin("tbeccaro");
        myUser.setUserStatus("normal_user");

        return myUser;
    }

    @Test
    public void saveUser() {

        myUserService.save(myUser());


    }


    @Test
    public void testDelete(){

        MyUser myUser = myUser();

        myUserService.save(myUser);

        myUserService.delete(myUser.getId_user());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyUser").list().size());

        session.close();

    }



    @Test
    public void findUser() {
        myUserService.save(myUser());

        List<MyUser> users = myUserService.findUser("tbeccaro");

        Assert.assertEquals(1, users.size());
        Assert.assertEquals("tbeccaro", users.get(0).getLogin());
    }


    @Test
    public void testFindAll() {

        myUserService.save(myUser());
        myUserService.save(myUser());

        Assert.assertEquals(2, myUserService.findAll().size());

    }

    @Test
    public void testFindByQuery() {

        myUserService.save(myUser());
        myUserService.save(myUser());

        Assert.assertEquals(2, myUserService.findByQuery("Thierry").size());
        Assert.assertEquals(0, myUserService.findByQuery("Beccaro").size());

    }

    @Test
    public void testCount() {

        MyUser myUser = new MyUser();

        myUserService.save(myUser);
        myUserService.save(myUser);

        Assert.assertEquals(2, myUserService.count());
    }

    @Test
    public void update() {
        MyUser myUser = new MyUser();

        myUserService.save(myUser);
        myUserService.update(myUser);

        Assert.assertEquals(1, myUserService.count());
    }


}
