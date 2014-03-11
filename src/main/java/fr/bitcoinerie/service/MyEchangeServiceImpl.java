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



@Service
public class MyEchangeServiceImpl implements MyEchangeService {
    @Inject
    private SessionFactory sessionFactory;
    @Inject
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
    @Transactional
    public MyEchange findByIdEchange(Long id) {
        Session session = sessionFactory.getCurrentSession();



        Query query = session.createQuery("from MyEchange where id_echange =:id");
        query.setLong("id", id);



        MyEchange myEchange =  (MyEchange) query.list().get(0);

        return myEchange;


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
    @Override
    public MyEchange findOneEchange (Long emet,Long recept){


        Session session = sessionFactory.getCurrentSession();
       /* Criteria criteria = session.createCriteria(MyEchange.class);
        criteria.add(Restrictions.and(Restrictions.eq("id_user_emetteur", emet),
                Restrictions.eq("id_user_recepteur", recept)));

        List<MyEchange> myEchanges = criteria.list();
        MyEchange echange = myEchanges.get(0) ;   */
        Query query = session.createQuery("from MyEchange where (id_user_recepteur =:recept)and(id_user_emetteur =:emet)");
        query.setLong("recept", recept);
        query.setLong("emet", emet);
        List<MyEchange> myEchanges =   query.list();
        MyEchange echange = myEchanges.get(0);
        return echange;
    }
    @Transactional
    @Override
    public void majEchange (Float montant, Date date_temps, Long emet, Long recept) {

        MyEchange echange=findOneEchange(emet,recept );
        echange.setDate_derniere_modification(date_temps);
        echange.setMontant(echange.getMontant()+montant) ;
        updateEchange(echange);
        MyEchange echangeemet=findOneEchange(emet,emet );
        echangeemet.setDate_derniere_modification(date_temps);
        echangeemet.setMontant(echangeemet.getMontant()-montant) ;
        updateEchange(echangeemet);
        MyEchange echangerecep=findOneEchange(recept,recept );
        echangerecep.setDate_derniere_modification(date_temps);
        echangerecep.setMontant(echangerecep.getMontant()+montant) ;
        updateEchange(echangerecep);



    }
    @Transactional
    @Override
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
    @Transactional
    @Override
    public void majproba(Long emet, Long recept){


       calculproba( emet, recept);

       calculproba(emet , emet);
        List<MyEchange> echanges= findByEmetteurEchange(recept);
        int i;
        for (i=0; i< echanges.size();i++){
            Long id = echanges.get(i).getRecepteur().getId_user();
            calculproba(recept,id);
        }

    }
    @Transactional
    @Override
    public void calculproba(Long emet, Long recept){
        int i;
        float s=0.F;
        MyEchange ech= findOneEchange(emet,recept);
     List<MyEchange> echanges =  findByEmetteurEchange( emet);
        for (i=0; i< echanges.size();i++){
            s=s+((echanges.get(i)).getMontant());
         }
        System.out.println(ech.getMontant());
        System.out.println(s);
        float proba =ech.getMontant()/s;
        System.out.println(proba);
        ech.setProbabilite(proba);
        updateEchange(ech);
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

                    session.saveOrUpdate(myEchange);




        }

}