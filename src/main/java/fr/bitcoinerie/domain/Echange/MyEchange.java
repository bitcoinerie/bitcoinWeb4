package fr.bitcoinerie.domain.Echange;

import javax.persistence.*;
import java.util.Date;
import fr.bitcoinerie.domain.User.MyUser;
@Entity
@Table(name = "myEchange")
public class MyEchange {
    @Column
    private MyUser emetteur;
    @Column
    private MyUser recepteur;
    @Column
    private Float montant;
    @Column
    private double probabilite;
    @Column
    private Date date_derniere_modification;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_echange;
    public Long getId() {
        return id_echange;
    }

    public void setId(Long id) {
        this.id_echange = id;
    }



    public  MyEchange (Float montant, MyUser emetteur, MyUser recepteur) {
        this.emetteur= emetteur;
        this.recepteur=recepteur;
        this.montant= montant;
        /**this.date_derniere_modification=; */
        this.probabilite= 0.F;
    }

    public  MyEchange(){
        MyUser banque = new MyUser( "banque","banque", 1000000.f );
        this.emetteur=banque;
        this.recepteur=banque;
        this.montant=1000000.f;

    }
    public void majEchange (Float montant, Date date_temps, MyUser emetteur, MyUser recepteur) {
        /** requete SQL pourtrouver l'échange en question*/
        date_derniere_modification= date_temps;
        this.montant+= montant;
        majproba( emetteur, recepteur);
    }

    public void majproba(MyUser emetteur, MyUser recepteur){
        /** doit moidifier la proba émetteur-recepteur */

    }

    public void nouvuser( Date date_temps, MyUser nouveau){


    }
    public MyUser getEmetteur() {
        return emetteur;
    }


    public MyUser getRecepteur() {
        return recepteur;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public double getProbabilite() {
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
