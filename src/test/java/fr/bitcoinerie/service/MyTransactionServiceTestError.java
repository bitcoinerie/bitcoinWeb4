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

        session.createQuery("deleteTransaction from MyTransaction").executeUpdate();

        transaction.commit();

        session.close();
        sessionFactory.close();
    }


    @Test
    public void testSave(){
        MyTransactionService myTransactionService = new MyTransactionService();

        myTransactionService.setSessionFactory(sessionFactory);

        myTransactionService.saveTransaction(myTransaction());
        //myTransactionService.saveTransaction(myTransaction());

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

        myTransactionService.saveTransaction(myTransaction);

        myTransactionService.deleteTransaction(myTransaction.getId_transaction());

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
    public void deleteTransaction() {


        Task task = task();

        taskService.saveTransaction(task);

        taskService.deleteTransaction(task.getId());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from Task").list().size());

        session.close();
    }

    @Test
    public void findAllTransaction() {


        taskService.saveTransaction(task());
        taskService.saveTransaction(task());

        Assert.assertEquals(2, taskService.findAllTransaction().size());
    }

    @Test
    public void findByQueryTransaction() {


        taskService.saveTransaction(task());
        taskService.saveTransaction(task());

        Assert.assertEquals(2, taskService.findByQueryTransaction("read").size());
        Assert.assertEquals(2, taskService.findByQueryTransaction("java").size());
        Assert.assertEquals(0, taskService.findByQueryTransaction("driven").size());
    }

    @Test
    public void countTransaction() {


        taskService.saveTransaction(task());
        taskService.saveTransaction(task());

        Assert.assertEquals(2, taskService.countTransaction());
    }
    */
}
