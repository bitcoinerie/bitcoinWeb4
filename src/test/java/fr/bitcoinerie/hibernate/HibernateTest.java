package fr.bitcoinerie.hibernate;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 04/12/13
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
public class HibernateTest {
    private SessionFactory sessionFactory;

    @Before
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
    public void saveMyTransaction() {
        MyTransaction myTransaction = new MyTransaction(20, new Date(),"A","B");

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(myTransaction);



        transaction.commit();

        session.close();
    }
}


