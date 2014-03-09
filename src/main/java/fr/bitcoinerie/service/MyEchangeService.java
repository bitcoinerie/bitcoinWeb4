package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyEchange;
import fr.bitcoinerie.domain.MyUser;


import java.util.Date;
import java.util.List;


public interface MyEchangeService {
    //@Transactional
    void saveEchange(MyEchange echange);
    void updateEchange(MyEchange myEchange);

    void deleteEchange(Long id);

    List<MyEchange> findAllEchange();

    List<MyEchange> findByEmetteurEchange(Long id);

    List<MyEchange> findByRecepteurEchange(Long id);

    void nouvuser( Date date_temps, MyUser nouveau, Float montant);

    MyEchange findOneEchange(Long emet, Long recept) ;

    int countEchange();

    void majEchange(Float montant, Date date_temps, Long emet, Long recept) ;



    void calculproba(Long emet, Long recept);

    public void majproba(Long emetteur, Long recepteur);

}
