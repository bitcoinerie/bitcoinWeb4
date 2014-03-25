package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
import org.hibernate.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyUserServiceTest {

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private MyUserService myUserService;

    @Inject
    MyTransactionService myTransactionService;

    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyTransaction").executeUpdate();
        session.createQuery("delete from MyUser").executeUpdate();

        transaction.commit();

        session.close();

    }

    private MyUser myUser() {
        MyUser myUser = new MyUser();

        myUser.setPrenom("Thierry");
        myUser.setNom("Beccaro");
        myUser.setEmail("tbeccaro@francetv.fr");
        myUser.setLogin("tbeccaro");
        myUser.setMot_de_passe("francetvrules");
        myUser.setUserStatus("normal_user");
        myUser.setMontant_compte(340.);

        return myUser;
    }

    private MyUser myUser2() {
        MyUser myUser2 = new MyUser();

        myUser2.setPrenom("Christophe");
        myUser2.setNom("Dechavanne");
        myUser2.setEmail("cdechavanne@tf1.fr");
        myUser2.setLogin("cdechavanne");
        myUser2.setMot_de_passe("tf1rules");
        myUser2.setUserStatus("normal_user");
        myUser2.setMontant_compte(280.);

        return myUser2;
    }

    private MyUser myUser1 = myUser();
    private MyUser myUser2 = myUser2();


    @Test
    public void saveUser() {

        myUserService.save(myUser1);


    }


    @Test
    public void testDelete() {

        myUserService.save(myUser1);

        myUserService.delete(myUser1.getId_user());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyUser").list().size());

        session.close();

    }


    @Test
    public void findUser() {
        myUserService.save(myUser1);

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

        myUserService.save(myUser1);
        myUserService.save(myUser1);

        Assert.assertEquals(2, myUserService.findByQuery("Thierry").size());
        Assert.assertEquals(0, myUserService.findByQuery("Beccaro").size());

    }

    @Test
    public void testFindById() {
        myUserService.save(myUser1);

        Assert.assertEquals(myUser1.getPrenom(), myUserService.findUserById(myUser1.getId_user()).getPrenom());
    }

    @Test
    public void testFindByNomAndPrenom() {
        myUserService.save(myUser1);

        Assert.assertEquals(myUser1.getId_user(),myUserService.findUserByPrenomAndNom("Thierry", "Beccaro").getId_user());
    }
    @Test
    public void testSignIn() {
        myUserService.save(myUser1);

        Assert.assertEquals(myUser1.getId_user(), myUserService.signIn("tbeccaro", "francetvrules").getId_user());
    }

    @Test
    public void testCount() {

        myUserService.save(myUser1);
        myUserService.save(myUser1);

        Assert.assertEquals(2, myUserService.count());
    }

    @Test
    public void update() {
        myUserService.save(myUser1);
        List<MyUser> users = myUserService.findUser("tbeccaro");
        users.get(0).setPrenom("Jordan");

        myUserService.updateUser(users.get(0));

        Assert.assertEquals(1, myUserService.findByQuery("Jordan").size());
    }


    @Test
    public void testDoTransaction() {
        myUserService.save(myUser1);
        myUserService.save(myUser2);

        MyTransaction trans = new MyTransaction(100., new Date(), myUser1, myUser2);

        myTransactionService.saveTransaction(trans);

        myUserService.doTransaction(trans);

        MyUser myUser1bis = myUserService.findUser("tbeccaro").get(0);
        MyUser myUser2bis = myUserService.findUser("cdechavanne").get(0);

        Session session = sessionFactory.openSession();
        session.lock(myUser1bis, LockMode.NONE);
        Hibernate.initialize(myUser1bis.getListe_depenses());
        Hibernate.initialize(myUser1bis.getListe_recettes());
        session.close();

        Assert.assertEquals(1, myUser1bis.getListe_depenses().size());
        Assert.assertEquals(0, myUser1bis.getListe_recettes().size());


        System.out.println("myUser1bis.getMontant_compte() :"+myUser1bis.getMontant_compte());
        System.out.println("myUser1bis.getMontant_compte() :"+myUser1bis.getListe_recettes());
        System.out.println("myUser1bis.getMontant_compte() :"+myUser1bis.getListe_depenses());
        Assert.assertEquals((Double) 240., myUser1bis.getMontant_compte());

        session = sessionFactory.openSession();
        session.lock(myUser2bis, LockMode.NONE);
        Hibernate.initialize(myUser2bis.getListe_depenses());
        Hibernate.initialize(myUser2bis.getListe_recettes());
        session.close();


        System.out.println("myUser2bis.getMontant_compte() : "+myUser2bis.getMontant_compte());
        System.out.println("myUser2bis.getMontant_compte() :"+myUser2bis.getListe_depenses());
        System.out.println("myUser1bis.getMontant_compte() :"+myUser2bis.getListe_recettes());

        Assert.assertEquals(0, myUser2bis.getListe_depenses().size());
        Assert.assertEquals(1, myUser2bis.getListe_recettes().size());


        Assert.assertEquals(0, myUser2bis.getListe_depenses().size());
        Assert.assertEquals(1, myUser2bis.getListe_recettes().size());
        Assert.assertEquals((Double) 380., myUser2bis.getMontant_compte());
    }

    @Test
    public void setreput() {

        myUserService.save(myUser1);
        List<MyUser> users = myUserService.findAll();
        users.get(0).setReputation(0.15);

        myUserService.updateUser(users.get(0));
        List<MyUser> users2 = myUserService.findAll();

        Assert.assertEquals((Double) 0.15, users2.get(0).getReputation());
    }

}
