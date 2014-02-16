package fr.bitcoinerie.domain.Transaction;

import fr.bitcoinerie.domain.User.MyUser;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="transactionTab")
public class MyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_transaction;


    @ManyToOne
    @JoinColumn(name="id_user")
    private MyUser emetteur;

    @ManyToOne
    @JoinColumn(name="id_user")
    private MyUser recepteur;

    @Column
    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date date_temps;

    public MyTransaction(double montant, Date date_temps, MyUser emetteur, MyUser recepteur) {

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
                ", emetteur='" + emetteur.getPrenom() + '\'' +
                ", recepteur='" + recepteur.getPrenom() + '\'' +
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

    public MyUser getRecepteur() {
        return recepteur;
    }

    public MyUser getEmetteur() {
        return emetteur;
    }

    public void setRecepteur(MyUser recepteur) {
        this.recepteur = recepteur;
    }

    public void setEmetteur(MyUser emetteur) {
        this.emetteur = emetteur;
    }

}
