package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
import fr.bitcoinerie.domain.MyUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class MyEchangeServiceImpl implements MyEchangeService {
    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private MyUserServiceImpl myUserService;

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

    @Override
    @Transactional(readOnly = true)
    public List<MyEchange> findByEmetteurEchange(String emetteur) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(MyEchange.class);

        criteria.add(Restrictions.ilike("emetteur", emetteur, MatchMode.ANYWHERE));

        List<MyEchange> myEchanges = criteria.list();

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

    @Override
    public void majEchange (Float montant, Date date_temps, MyUser emetteur, MyUser recepteur) {

        MyEchange echange=findOneEchange(emetteur,recepteur );
        echange.setDate_derniere_modification(date_temps);
        echange.setMontant(montant) ;

    }

    @Override
   public void nouvuser( Date date_temps, MyUser nouveau){
     List<MyUser> users= myUserService.findAll();
        int i;
        for (i=0; i< users.size();i++){
            MyEchange echange = new MyEchange( 0.F, users.get(i),nouveau);
            saveEchange(echange);
            MyEchange echange2 = new MyEchange( 0.F, nouveau,users.get(i));
            saveEchange(echange2);

        }
    }
    @Transactional
    @Override
    public void majproba(MyUser emetteur, MyUser recepteur){
      // List<MyEchange>

    }

    @Transactional
    @Override
    public int countEchange() {
        // TODO
        return findAllEchange().size();
    }

    /*@Override
    @Transactional
    public void saveMyUser(MyUser user) {
        Session session = sessionFactory.getCurrentSession();
        List<MyEchange> existe= findByQueryTransaction( user);
        List<MyEchange>echanges=findAllTransaction();
        int n=echanges.size();

        for (int j=1;j<= n;j++){
            MyUser myuser=echanges.get(j).getEmetteur();

            MyEchange myEchange= new MyEchange((double) 0,user,myuser);
            saveTransaction(myEchange) ;
            MyEchange monEchange= new MyEchange((double) 0,user,user);
            saveTransaction(monEchange) ;
            MyEchange tonEchange= new MyEchange((double) 0,myuser,user);
            saveTransaction(tonEchange) ;
        }
    }    */

}