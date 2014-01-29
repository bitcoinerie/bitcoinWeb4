package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: marjolaine
 * Date: 06/12/13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyTransactionServiceTestError {

    //@Inject
    private SessionFactory sessionFactory;

    //@Inject
    //private MyTransactionService myTransactionService;

    @Test
    public void createSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenFiveDialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:derby:target/testdb;create=true");
        configuration.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        configuration.addAnnotatedClass(MyTransaction.class);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /*
    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from MyTransaction").executeUpdate();

        transaction.commit();

        session.close();
        sessionFactory.close();
    }


    @Test
    public void testSave(){
        MyTransactionService myTransactionService = new MyTransactionService();

        myTransactionService.setSessionFactory(sessionFactory);

        myTransactionService.save(myTransaction());
        //myTransactionService.save(myTransaction());

    }


    private MyTransaction myTransaction() {
        MyTransaction myTransaction = new MyTransaction();
        myTransaction.setMontant(24);
        myTransaction.setDate_temps(new Date());
        myTransaction.setEmetteur("Paul");
        myTransaction.setRecepteur("Jean");
        return myTransaction;
    }
    */

    /*

    @Test
    public void testDelete() throws Exception {
        MyTransaction myTransaction = new MyTransaction();

        myTransactionService.save(myTransaction);

        myTransactionService.delete(myTransaction.getId_transaction());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from MyTransaction").list().size());

        session.close();

    }

    @Test
    public void testFindAll() throws Exception {

    }



    @Test
    public void testFindByQuery() throws Exception {

    }

    @Test
    public void testCount() throws Exception {

    }

    */

    /*

    @Test
    public void delete() {


        Task task = task();

        taskService.save(task);

        taskService.delete(task.getId());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from Task").list().size());

        session.close();
    }

    @Test
    public void findAll() {


        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.findAll().size());
    }

    @Test
    public void findByQuery() {


        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.findByQuery("read").size());
        Assert.assertEquals(2, taskService.findByQuery("java").size());
        Assert.assertEquals(0, taskService.findByQuery("driven").size());
    }

    @Test
    public void count() {


        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.count());
    }
    */
}
