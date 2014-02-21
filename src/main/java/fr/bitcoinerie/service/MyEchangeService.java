package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
import fr.bitcoinerie.domain.MyUser;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: philippe
 * Date: 20/01/14
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public interface MyEchangeService {
    //@Transactional
    void saveEchange(MyEchange echange);

   // @Transactional
    void deleteEchange(Long id);

   // @Transactional(readOnly = true)
    List<MyEchange> findAllEchange();

   // @Transactional(readOnly = true)
    List<MyEchange> findByEmetteurEchange(String query);

    MyEchange findOneEchange(MyUser emetteur, MyUser recepteur) ;
    //@Transactional(readOnly = true)
    int countEchange();
    void majEchange(Float montant, Date date_temps, MyUser emetteur, MyUser recepteur) ;
    public void nouvuser(Date date_temps, MyUser nouveau) ;

    public void majproba(MyUser emetteur, MyUser recepteur);

}
