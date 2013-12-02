package fr.bitcoinerie.web.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
public class MyTransaction {
    private int id_transaction;
    private MyUser emetteur;
    private MyUser recepteur;
    private Float montant;
    private Date date_temps;

}
