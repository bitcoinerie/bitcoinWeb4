package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;



@Service
public class MyUserServiceImpl implements MyUserService {

    @Inject
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void save(MyUser myUser) {

        Session session = sessionFactory.getCurrentSession();

        session.save(myUser);

    }


    @Transactional
    @Override
    public void delete(Long id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from MyUser where id = :id");
        query.setLong("id", id);

        query.executeUpdate();

    }


    @Transactional
    @Override
    public List<MyUser> findAll() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyUser");

        List<MyUser> myUsers = query.list();

        return myUsers;

    }


    @Transactional
    @Override
    public List<MyUser> findByQuery(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyUser.class);

        criteria.add(Restrictions.ilike("prenom", query, MatchMode.ANYWHERE));

        List<MyUser> myUsers = criteria.list();

        return myUsers;

    }

    @Transactional
    @Override
    public List<MyUser> findByQueryWithRelations(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyUser.class);

        criteria.add(Restrictions.ilike("prenom", query, MatchMode.ANYWHERE));

        List<MyUser> myUsers = criteria.list();

        for (int i=0; i < myUsers.size(); i++){
            Hibernate.initialize(myUsers.get(i).getListe_depenses());
            Hibernate.initialize(myUsers.get(i).getListe_recettes());
        }

        return myUsers;

    }

    @Transactional
    @Override
    public List<MyUser> findUser(String login) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyUser where login = :login");

        query.setString("login", login);

        List<MyUser> users = query.list();

        return users;

    }


    @Transactional
    @Override
    public int count() {
        // TODO
        return findAll().size();
    }

    @Override
    @Transactional
    public void update(MyUser myUser) {

        Session session = sessionFactory.getCurrentSession();

      /*  Long id = myUser.getId_user();

        Query query = session.createQuery("from MyUser where id =:id");
        query.setLong("id", id);


        if ((MyUser) query.list().get(0) == null){
            session.save(myUser);
        }
        else{
            session.update(myUser);
        }   */
        session.saveOrUpdate(myUser);


    }

    @Transactional
    public void doTransaction(MyTransaction trans){

        MyUser emetteur = trans.getEmetteur();
        MyUser recepteur = trans.getRecepteur();
        Double somme = trans.getMontant();

        Session session = sessionFactory.openSession();
        session.lock(emetteur, LockMode.NONE);
        Hibernate.initialize(emetteur.getListe_depenses());
        session.close();

        session = sessionFactory.openSession();
        session.lock(recepteur, LockMode.NONE);
        Hibernate.initialize(recepteur.getListe_recettes());
        session.close();

        emetteur.addDepense(trans);
        emetteur.addMontant(- somme);
        recepteur.addRecette(trans);
        recepteur.addMontant(somme);

        update(emetteur);
        update(recepteur);

    }
}
