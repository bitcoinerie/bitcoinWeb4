package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
import fr.bitcoinerie.domain.MyUser;
import fr.bitcoinerie.service.MyUserService;
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



public class MyEchangeServiceImpl implements MyEchangeService {
    @Inject
    private SessionFactory sessionFactory;
   private MyUserService myUserService;


    @Override
    @Transactional
    public void saveEchange(MyEchange echange) {
        Session session = sessionFactory.getCurrentSession();

        session.save(echange);
    }

    @Override
    @Transactional
    public void deleteEchange(Long id) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from MyEchange where id_echange = :id");
        query.setLong("id", id);

        query.executeUpdate();

    }

    @Override
    @Transactional(readOnly = true)
    public List<MyEchange> findAllEchange() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(" from MyEchange ");

        List<MyEchange> myEchanges= query.list();


        return myEchanges;
    }

    @Transactional
    @Override
    public List<MyEchange> findByEmetteurEchange(Long id) {
        Session session = sessionFactory.getCurrentSession();

        //Criteria criteria = session.createCriteria(MyTransaction.class);

        //criteria.add(Restrictions.ilike("emetteur", query, MatchMode.ANYWHERE));

        Query query = session.createQuery("from MyEchange where id_user_emetteur =:id");
        query.setLong("id", id);

        //System.out.println(query.list().size());

        List<MyEchange> myEchanges =   query.list();

        return myEchanges;
    }
        @Transactional
        @Override
        public List<MyEchange> findByRecepteurEchange(Long id) {
            Session session = sessionFactory.getCurrentSession();



            Query query = session.createQuery("from MyEchange where id_user_recepteur =:id");
            query.setLong("id", id);

            //System.out.println(query.list().size());

            List<MyEchange> myEchanges =   query.list();

            return myEchanges;
        }
    @Transactional
    public MyEchange findOneEchange (MyUser emetteur,MyUser recepteur){
        Long emet =emetteur.getId_user();
        Long recept =recepteur.getId_user();

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyEchange.class);
        criteria.add(Restrictions.and(Restrictions.eq("id_user_emetteur", emet),
                Restrictions.eq("id_user_recepteur", recept)));

        List<MyEchange> myEchanges = criteria.list();
        MyEchange echange = myEchanges.get(0) ;

        return echange;
    }
    @Transactional
    @Override
    public void majEchange (Float montant, Date date_temps, MyUser emetteur, MyUser recepteur) {

        MyEchange echange=findOneEchange(emetteur,recepteur );
        echange.setDate_derniere_modification(date_temps);
        echange.setMontant(montant) ;
        saveEchange(echange);
        MyEchange echangeemet=findOneEchange(emetteur,emetteur );
        echangeemet.setDate_derniere_modification(date_temps);
        echangeemet.setMontant(montant) ;
        saveEchange(echangeemet);
        MyEchange echangerecep=findOneEchange(emetteur,recepteur );
        echangerecep.setDate_derniere_modification(date_temps);
        echangerecep.setMontant(montant) ;
        saveEchange(echangerecep);



    }


   public void nouvuser( Date date_temps, MyUser nouveau, Float montant){
     List<MyUser> users= myUserService.findAll();
        int i;
        MyEchange echange3 = new MyEchange( montant, nouveau,nouveau);
        saveEchange(echange3);
        for (i=0; i< users.size();i++){
            MyEchange echange = new MyEchange( 0.F, users.get(i),nouveau);
            saveEchange(echange);
            MyEchange echange2 = new MyEchange( 0.F, nouveau,users.get(i));
            saveEchange(echange2);

        }
    }

    @Override
    public void majproba(MyUser emetteur, MyUser recepteur){
        Long id= recepteur.getId_user();
       MyEchange echange=  findOneEchange(emetteur, recepteur);
       calculproba(echange);
        MyEchange echangeemetteur=  findOneEchange(emetteur, recepteur);
        calculproba(echangeemetteur);
        List<MyEchange> echanges= findByEmetteurEchange(id);
        int i;
        for (i=0; i< echanges.size();i++){
            calculproba(echanges.get(i));
        }

    }
    @Transactional
    @Override
    public void calculproba(MyEchange echange){
        int i;
        float s=0.F;
    Long id= (echange.getEmetteur()).getId_user();
     List<MyEchange> echanges =  findByEmetteurEchange( id);
        for (i=0; i< echanges.size();i++){
            s=s+(echanges.get(i)).getMontant();
         }
        echange.setProbabilite(echange.getMontant()/s);
        saveEchange(echange);
    }



    @Transactional
    @Override
    public int countEchange() {
        // TODO
        return findAllEchange().size();
    }

    @Override
    @Transactional
    public void updateEchange(MyEchange myEchange) {

        Session session = sessionFactory.getCurrentSession();

        Long id = myEchange.getId_echange();

        Query query = session.createQuery("from MyEchange where id =:id");
        query.setLong("id", id);


        if ( query.list().get(0) == null){
            session.save(myEchange);
        }
    }

}