package fr.bitcoinerie.domain.User;

import fr.bitcoinerie.domain.Transaction.MyTransaction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="userTab")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_user;


    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private Float reputation;
    @Column
    private Float montant_compte;
    @Column
    private String login;
    @Column
    private Date date_inscription;
    @Column
    private String email;
    @Column
    private String userStatus;
    @Column
    private Set<MyTransaction> liste_dépenses;
    @Column
    private Set<MyTransaction> liste_recettes;

    public MyUser(String nom, String prenom, Float montant_compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.montant_compte = montant_compte;
    }
}
