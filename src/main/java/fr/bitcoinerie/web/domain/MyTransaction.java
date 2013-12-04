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
    private double montant;
    private Date date_temps;

    public MyTransaction(int id_transaction, double montant, Date date_temps) {
        this.id_transaction = id_transaction;
        this.montant = montant;
        this.date_temps = date_temps;
    }

    @Override
    public String toString() {
        return "MyTransaction {" +
                "id_transaction=" + id_transaction +
                ", montant=" + montant +
                ", date_temps=" + date_temps +
                '}';
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate_temps() {
        return date_temps;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate_temps(Date date_temps) {
        this.date_temps = date_temps;
    }


}
