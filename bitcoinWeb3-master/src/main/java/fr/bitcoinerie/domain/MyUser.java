package fr.bitcoinerie.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="userTab")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private Double reputation;
    @Column
    private Double montant_compte;
    @Column
    private String login;
    @Column
    private Date date_inscription;
    @Column
    private String email;
    @Column
    private String userStatus;

    //@Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy="emetteur")
    private Set<MyTransaction> liste_depenses;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="recepteur")
    private Set<MyTransaction> liste_recettes;

    public MyUser(){
        setDate_inscription(new Date());
        setMontant_compte(10.);
        setReputation(1.);

        liste_depenses = new HashSet<MyTransaction>();
        liste_recettes = new HashSet<MyTransaction>();
    }

    public MyUser(String prenom, String nom, double montant){
        this.prenom = prenom;
        this.nom = nom;
        this.montant_compte = montant;

        liste_depenses = new HashSet<MyTransaction>();
        liste_recettes = new HashSet<MyTransaction>();
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Double getMontant_compte() {
        return montant_compte;
    }

    public void setMontant_compte(Double montant_compte) {
        this.montant_compte = montant_compte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Set<MyTransaction> getListe_depenses() {
        return liste_depenses;
    }

    public void setListe_depenses(Set<MyTransaction> liste_depenses) {
        this.liste_depenses = liste_depenses;
    }

    public Set<MyTransaction> getListe_recettes() {
        return liste_recettes;
    }

    public void setListe_recettes(Set<MyTransaction> liste_recettes) {
        this.liste_recettes = liste_recettes;
    }

    public void addRecette(MyTransaction trans){
        liste_recettes.add(trans);
    }

    public void addDepense(MyTransaction trans){
        liste_depenses.add(trans);
    }

    public void addMontant(Double somme){
        montant_compte = montant_compte + somme;
    }
}
