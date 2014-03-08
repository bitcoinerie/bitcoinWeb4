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
    private MyEchangeService myEchangeService;
    @Inject
    private MyUserService myUserService;

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


   @Test
    public void testSave() throws Exception {
       Jean =  new MyUser("Jean", "Kevin", 100);
       Paul =  new MyUser("Paul", "Hidalgo", 200);

       myUserService.save(Jean);
       myUserService.save(Paul);
        myEchangeService.saveEchange( new MyEchange( 100.F, Paul , Jean));

       Long id_jean =Jean.getId_user();
       Long id_paul =Paul.getId_user();
       MyEchange echange = myEchangeService.findOneEchange(id_paul, id_jean);
       echange.setMontant(200.F);
       myEchangeService.saveEchange(echange);
       MyEchange echange2 =myEchangeService.findOneEchange(id_jean, id_jean );

       Assert.assertEquals(2, myEchangeService.findByEmetteurEchange(id_paul ).size());



    }



    @Test
    public void testDelete() throws Exception {

        MyEchange myEchange = new MyEchange( 200.F, Paul , Jean);

        myEchangeService.saveEchange(myEchange);

        myEchangeService.deleteEchange(myEchange.getId_echange());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyEchange").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {


        myEchangeService.saveEchange( new MyEchange( 230.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 400.F, Paul , Paul));

        Assert.assertEquals(2, myEchangeService.findAllEchange().size());

    }

    @Test
    public void testFindByEmetteur() throws Exception {
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);

        myEchangeService.saveEchange( new MyEchange( 300.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 450.F, Paul , Paul));
        Long id_jean =Jean.getId_user();
        Long id_paul =Paul.getId_user();

        Assert.assertEquals(2, myEchangeService.findByEmetteurEchange(id_paul ).size());
        Assert.assertEquals(0, myEchangeService.findByEmetteurEchange(id_jean ).size());

    }
    @Test
    public void testfindByIdEchange(){
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);
        MyEchange echange = new MyEchange( 300.F, Paul , Jean);
        myEchangeService.saveEchange( echange);
        myEchangeService.saveEchange( new MyEchange( 450.F, Paul , Paul));
        Long id_ech =echange.getId_echange();

        Assert.assertEquals(300.F, myEchangeService.findByIdEchange(id_ech).getMontant(),0.F);
        myEchangeService.deleteEchange(id_ech);
        MyEchange echange2= myEchangeService.findByIdEchange(id_ech);

    }
    @Test
    public void testfindOneEchange(){
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);

        myEchangeService.saveEchange( new MyEchange( 300.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 450.F, Paul , Paul));
        Long id_jean =Jean.getId_user();
        Long id_paul =Paul.getId_user();
        Assert.assertEquals(300.F, myEchangeService.findOneEchange(id_paul, id_jean).getMontant(),0.F);
       MyEchange echange= myEchangeService.findOneEchange(id_jean, id_jean);

    }
    @Test
    public void testmajEchange(){
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);
        myEchangeService.saveEchange( new MyEchange( 0.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 200.F, Paul , Paul));
        myEchangeService.saveEchange( new MyEchange( 100.F, Jean , Jean));
        myEchangeService.saveEchange( new MyEchange( 0.F, Jean , Paul));

        Long id_jean =Jean.getId_user();
        Long id_paul =Paul.getId_user();
        myEchangeService.majEchange(100.F, dateTest,id_paul, id_jean) ;

        Assert.assertEquals(100.F, myEchangeService.findOneEchange(id_paul, id_jean ).getMontant(),0.F);
        Assert.assertEquals(100.F, myEchangeService.findOneEchange(id_paul, id_paul ).getMontant(),0.F);
        Assert.assertEquals(200.F, myEchangeService.findOneEchange(id_jean, id_jean ).getMontant(),0.F);

    }
    @Test
     public void testmajproba(){
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);
        myEchangeService.saveEchange( new MyEchange( 0.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 200.F, Paul , Paul));
        myEchangeService.saveEchange( new MyEchange( 100.F, Jean , Jean));
        myEchangeService.saveEchange( new MyEchange( 0.F, Jean , Paul));

        Long id_jean =Jean.getId_user();
        Long id_paul =Paul.getId_user();
        myEchangeService.majEchange(100.F, dateTest,id_paul, id_jean) ;
        myEchangeService.majproba(id_paul, id_jean) ;

        Assert.assertEquals(0.5, myEchangeService.findOneEchange(id_paul, id_jean ).getProbabilite(),0.F);
        Assert.assertEquals(0.5, myEchangeService.findOneEchange(id_paul, id_paul ).getProbabilite(),0.F);
        Assert.assertEquals(1.F, myEchangeService.findOneEchange(id_jean, id_jean ).getProbabilite(),0.F);

    }
    @Test
    public void testcalculproba(){
        Jean =  new MyUser("Jean", "Kevin", 100);
        Paul =  new MyUser("Paul", "Hidalgo", 200);

        myUserService.save(Jean);
        myUserService.save(Paul);
        myEchangeService.saveEchange( new MyEchange( 0.F, Paul , Jean));
        myEchangeService.saveEchange( new MyEchange( 200.F, Paul , Paul));
        myEchangeService.saveEchange( new MyEchange( 100.F, Jean , Jean));
        myEchangeService.saveEchange( new MyEchange( 0.F, Jean , Paul));

        Long id_jean =Jean.getId_user();
        Long id_paul =Paul.getId_user();

        myEchangeService.majEchange(100.F, dateTest,id_paul, id_jean) ;


        myEchangeService.calculproba(id_paul, id_jean) ;

        Assert.assertEquals(0.5, myEchangeService.findOneEchange(id_paul, id_jean ).getProbabilite(),0.F);


    }
}
