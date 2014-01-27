package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;



/**
 * Created with IntelliJ IDEA.
 * User: marjolaine
 * Date: 06/12/13
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MyTransactionServiceImpl implements MyTransactionService {
    @Inject
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void save(MyTransaction myTransaction) {

        Session session = sessionFactory.getCurrentSession();

        /*

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(myTransaction);

        transaction.commit();

        session.close();

        */
        session.save(myTransaction);

    }

    //@Override
    @Transactional
    @Override
    public void delete(Long id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from MyTransaction where id = :id");
        query.setLong("id", id);

        query.executeUpdate();

    }

    //@Override
    @Transactional
    @Override
    public List<MyTransaction> findAll() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyTransaction");

        List<MyTransaction> myTransactions = query.list();

        return myTransactions;

    }

    //@Override
    @Transactional
    @Override
    public List<MyTransaction> findByQuery(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);

        criteria.add(Restrictions.ilike("emetteur", query, MatchMode.ANYWHERE));

        List<MyTransaction> myTransactions = criteria.list();

        return myTransactions;

    }

    @Transactional
    @Override
    public MyTransaction findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        //Criteria criteria = session.createCriteria(MyTransaction.class);

        //criteria.add(Restrictions.ilike("emetteur", query, MatchMode.ANYWHERE));

        Query query = session.createQuery("from MyTransaction where id =:id");
        query.setLong("id", id);

        //System.out.println(query.list().size());

        MyTransaction myTransaction =  (MyTransaction) query.list().get(0);

        return myTransaction;

        //return (MyTransaction) session.get(MyTransaction.class, id);

    }

    //@Override
    @Transactional
    @Override
    public int count() {
        // TODO
        return findAll().size();
    }

    /*
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    */

    @Override
    @Transactional
    public void update(MyTransaction myTransaction) {

        Session session = sessionFactory.getCurrentSession();

        Long id = myTransaction.getId_transaction();

        Query query = session.createQuery("from MyTransaction where id =:id");
        query.setLong("id", id);


        if ((MyTransaction) query.list().get(0) == null){
            session.save(myTransaction);
        }


    }
}
