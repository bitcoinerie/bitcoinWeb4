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
    void updateEchange(MyEchange myEchange);

    void deleteEchange(Long id);

    List<MyEchange> findAllEchange();

    List<MyEchange> findByEmetteurEchange(Long id);

    List<MyEchange> findByRecepteurEchange(Long id);

    void nouvuser( Date date_temps, MyUser nouveau, Float montant);

    MyEchange findOneEchange(MyUser emetteur, MyUser recepteur) ;

    int countEchange();

    void majEchange(Float montant, Date date_temps, MyUser emetteur, MyUser recepteur) ;



    void calculproba(MyEchange echange);

    public void majproba(MyUser emetteur, MyUser recepteur);

}
