package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;





@Service
public class MyTransactionServiceImpl implements MyTransactionService {
    @Inject
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void saveTransaction(MyTransaction myTransaction) {

        Session session = sessionFactory.getCurrentSession();

        session.save(myTransaction);

    }

    //@Override
    @Transactional
    @Override
    public void deleteTransaction(Long id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from MyTransaction where id = :id");
        query.setLong("id", id);

        query.executeUpdate();

    }

    //@Override
    @Transactional
    @Override
    public List<MyTransaction> findAllTransaction() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyTransaction");

        List<MyTransaction> myTransactions = query.list();

        return myTransactions;

    }

    //@Override
    @Transactional
    @Override
    public List<MyTransaction> findByDateTransaction(Date queryStart, Date queryEnd) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);


        criteria.add(Restrictions.between("date_temps", queryStart, queryEnd));

        List<MyTransaction> myTransactions = criteria.list();

        return myTransactions;

    }

    @Transactional
    @Override
    public List<MyTransaction> findByAmountLargerTransaction(double query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);

        criteria.add(Restrictions.gt("montant", query));

        List<MyTransaction> myTransactions = criteria.list();

        return myTransactions;

    }

    @Transactional
    @Override
    public List<MyTransaction> findByAmountSmallerTransaction(double query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);

        criteria.add(Restrictions.lt("montant", query));

        List<MyTransaction> myTransactions = criteria.list();

        return myTransactions;

    }

    @Transactional
    @Override
    public List<MyTransaction> findByAmountEqualsTransaction(double query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);

        criteria.add(Restrictions.eq("montant", query));

        List<MyTransaction> myTransactions = criteria.list();

        return myTransactions;


    }


    @Transactional
    @Override
    public List<MyTransaction> findByEmetterTransaction(String query) {
        Session session = sessionFactory.getCurrentSession();

        Query queryTr =  session.createSQLQuery("select * from transactionTab T, userTab U  " +
                "where T.id_user_emetteur = U.id_user " +
                "and U.prenom = ?").addEntity(MyTransaction.class);
        queryTr.setParameter(0, query);

        System.out.println(queryTr.list().size());

        List<MyTransaction> myTransactions = queryTr.list();

        for (int i=0; i < myTransactions.size(); i++){

            System.out.println(">>>>>>>>>>   myTr Emetteur "+myTransactions.get(i)+" class : "+myTransactions.getClass());
        }


        return myTransactions;

    }

    @Transactional
    @Override
    public List<MyTransaction> findByRecepterTransaction(String query) {
        Session session = sessionFactory.getCurrentSession();

        Query queryTr =  session.createSQLQuery("select * from transactionTab T, userTab U  " +
                "where T.id_user_recepteur = U.id_user " +
                "and U.prenom = ?").addEntity(MyTransaction.class);
        queryTr.setParameter(0, query);

        System.out.println(queryTr.list().size());

        List<MyTransaction> myTransactions = queryTr.list();

        for (int i=0; i < myTransactions.size(); i++){
            System.out.println(">>>>>>>>>>   myTr "+myTransactions.get(i));
        }


        return myTransactions;

    }


    @Transactional
    @Override
    public MyTransaction findByIdTransaction(Long id) {
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
    public int countTransaction() {
        // TODO
        return findAllTransaction().size();
    }


    @Override
    @Transactional
    public void updateTransaction(MyTransaction myTransaction) {

        Session session = sessionFactory.getCurrentSession();

        Long id = myTransaction.getId_transaction();

        Query query = session.createQuery("from MyTransaction where id =:id");
        query.setLong("id", id);


        if ((MyTransaction) query.list().get(0) == null){
            session.save(myTransaction);
        }


    }
}
