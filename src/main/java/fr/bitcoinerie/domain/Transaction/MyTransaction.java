package fr.bitcoinerie.domain.Transaction;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="transactionTab")
public class MyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_transaction;
    /*
    @Column
    private MyUser emetteur;

    @Column
    private MyUser recepteur;
*/
    @Column
    private String emetteur;

    @Column
    private String recepteur;

    @Column
    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date date_temps;

    public MyTransaction(double montant, Date date_temps, String emetteur, String recepteur) {

        this.montant = montant;
        this.date_temps = date_temps;
        this.emetteur = emetteur;
        this.recepteur = recepteur;
    }

    public MyTransaction(){

    }

    @Override
    public String toString() {
        return "MyTransaction{" +
                "id_transaction=" + id_transaction +
                ", emetteur='" + emetteur + '\'' +
                ", recepteur='" + recepteur + '\'' +
                ", montant=" + montant +
                ", date_temps=" + date_temps +
                '}';
    }



    public Long getId_transaction() {
        return id_transaction;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate_temps() {
        return date_temps;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate_temps(Date date_temps) {
        this.date_temps = date_temps;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setRecepteur(String recepteur) {
        this.recepteur = recepteur;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

}
