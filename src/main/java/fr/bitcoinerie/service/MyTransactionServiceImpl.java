package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;

import org.hibernate.*;
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

        Criteria criteria = session.createCriteria(MyTransaction.class);

        Criteria critUser = session.createCriteria(MyUser.class);

        critUser.add(Restrictions.eq("prenom", query));

        List<MyUser> myEmetters = critUser.list();

        for (int i=0; i < myEmetters.size(); i++){
            System.out.println(myEmetters.get(i).getPrenom());
        }


        //List<MyUser> myEmetters = myUserService.findByQuery(query);

        //criteria.add(Restrictions.eq("id_user_emetteur", myEmetters.get(0).getId_user() ));

/*        Long id_user_emetteur =  myEmetters.get(0).getId_user();
        Query queryTr = session.createQuery("from MyTransaction where id_user_emetteur = :id_user_emetteur");
        queryTr.setLong("id_user_emetteur", id_user_emetteur );
        queryTr.executeUpdate();*/


        List<MyTransaction> myTransactions = session.createCriteria(MyTransaction.class)
                                                    .setFetchMode("id_user_recepteur", FetchMode.JOIN)
                                                    .list();

        return myTransactions;

    }

    @Transactional
    @Override
    public List<MyTransaction> findByRecepterTransaction(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyTransaction.class);

        Criteria critUser = session.createCriteria(MyUser.class);

        critUser.add(Restrictions.eq("prenom", query));

        List<MyUser> myRecepters = critUser.list();

        for (int i=0; i < myRecepters.size(); i++){
            System.out.println(myRecepters.get(i).getPrenom());
        }

        //List<MyUser> myRecepters = myUserService.findByQuery(query);

        //criteria.add(Restrictions.eq("id_user_recepteur", myRecepters.get(0).getId_user() ));

/*        Long  id_user= myRecepters.get(0).getId_user();
        Query queryTr = session.createQuery("from MyTransaction where MyTransaction.id_user_recepteur = :id_user left join fetch MyTransaction.id_user_recepteur");
        queryTr.setLong("id_user_recepteur", id_user);
        queryTr.executeUpdate();
        */


        List<MyTransaction> myTransactions = session.createCriteria(MyTransaction.class)
                                                    .setFetchMode("id_user_recepteur", FetchMode.JOIN)
                                                    .list();


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
