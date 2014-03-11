package fr.bitcoinerie.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "myEchange")
public class MyEchange {
    @ManyToOne
    @JoinColumn(name="id_user_emetteur")
    private MyUser emetteur;



    @ManyToOne
    @JoinColumn(name="id_user_recepteur")
    private MyUser recepteur;

    @Column
    private Float montant;
    @Column
    private Float probabilite;
    @Column
    private Date date_derniere_modification;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_echange;

    public Long getId_echange() {
        return id_echange;
    }



    public void setId_echange(Long id_echange) {
        this.id_echange = id_echange;
    }



    public MyEchange(Float montant, MyUser emetteur, MyUser recepteur) {
        this.emetteur= emetteur;
        this.recepteur=recepteur;
        this.montant= montant;
        /**this.date_derniere_modification=; */
        this.probabilite= 0.F;
    }

    public MyEchange(){
        MyUser banque = new MyUser( "banque","banque", 1000000.f );
        this.emetteur=banque;
        this.recepteur=banque;
        this.montant=1000000.f;

    }



    public MyUser getEmetteur() {
        return emetteur;
    }


    public MyUser getRecepteur() {
        return recepteur;
    }
    public void setEmetteur(MyUser emetteur) {
        this.emetteur = emetteur;
    }

    public void setRecepteur(MyUser recepteur) {
        this.recepteur = recepteur;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Float getProbabilite() {
        return probabilite;
    }

    public void setProbabilite(Float probabilite) {
        this.probabilite = probabilite;
    }

    public Date getDate_derniere_modification() {
        return date_derniere_modification;
    }

    public void setDate_derniere_modification(Date date_derniere_modification) {
        this.date_derniere_modification = date_derniere_modification;
    }
}
