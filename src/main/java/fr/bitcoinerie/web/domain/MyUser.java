package fr.bitcoinerie.web.domain;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
public class MyUser {
    private int id_user;
    private String nom;
    private String prenom;
    private Float reputation;
    private Float montant_compte;
    private String login;
    private Date date_inscription;
    private String email;
    private String userStatus;
    private List<MyTransaction> liste_dépenses;
    private List<MyTransaction> liste_recettes;

}
