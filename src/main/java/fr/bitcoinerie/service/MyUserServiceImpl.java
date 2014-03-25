package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.service.MyEchangeService;
import fr.bitcoinerie.domain.MyUser;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;



@Service
public class MyUserServiceImpl implements MyUserService {

    @Inject
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void save(MyUser myUser) {

        Session session = sessionFactory.getCurrentSession();

//        myEchangeService.nouvuser(new Date(),myUser,myUser.getMontant_compte());
//        myEchangeService.majreput(0.15);

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
    public MyUser findUserById(Long id_user) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyUser where id_user = :id_user");

        query.setLong("id_user", id_user);

        return (MyUser) query.list().get(0);
    }

    @Transactional
    @Override
    public MyUser findUserByPrenomAndNom(String prenom, String nom){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyUser where prenom = :prenom and nom = :nom");

        query.setString("prenom", prenom);
        query.setString("nom", nom);

        if (query.list().size() == 0) return null;

        return (MyUser) query.list().get(0);
    }

    @Transactional
    @Override
    public MyUser signIn(String login, String mot_de_passe) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MyUser where login = :login and mot_de_passe = :mot_de_passe");

        query.setString("login", login);
        query.setString("mot_de_passe", mot_de_passe);

        if (query.list().size() == 0) return null;

        return (MyUser) query.list().get(0);
    }


    @Transactional
    @Override
    public int count() {
        // TODO
        return findAll().size();
    }

    @Override
    @Transactional
    public void updateUser(MyUser myUser) {

        Session session = sessionFactory.getCurrentSession();

      /*  Long id = myUser.getId_user();

        Query query = session.createQuery("from MyUser where id =:id");
        query.setLong("id", id);


        if ((MyUser) query.list().get(0) == null){
            session.save(myUser);
        }
        else{
            session.updateUser(myUser);
        }   */
        session.saveOrUpdate(myUser);


    }

    @Transactional
    public void doTransaction(MyTransaction trans){

        MyUser emetteur = trans.getEmetteur();
        MyUser recepteur = trans.getRecepteur();
        Double somme = trans.getMontant();


        System.out.println("transaction montant : "+somme);

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
        System.out.println("montant emetteur: "+emetteur.getMontant_compte());
        recepteur.addRecette(trans);
        recepteur.addMontant(somme);

        System.out.println("montant : "+recepteur.getMontant_compte());

//        myEchangeService.majEchange(somme,new Date(),emetteur.getId_user(),recepteur.getId_user());
//        myEchangeService.majproba(emetteur.getId_user(),recepteur.getId_user());
//        myEchangeService.majreput(0.15);


        updateUser(emetteur);
        updateUser(recepteur);
        System.out.println("do transaction user : "+findAll());





    }
}
