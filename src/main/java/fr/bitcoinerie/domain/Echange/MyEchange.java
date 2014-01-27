package fr.bitcoinerie.domain.Echange;

import fr.bitcoinerie.domain.User.MyUser;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
public class MyEchange {
    private MyUser emetteur;
    private MyUser recepteur;
    private Float montant;
    private Float probabilite;
    private Date date_derniere_modification;

}
