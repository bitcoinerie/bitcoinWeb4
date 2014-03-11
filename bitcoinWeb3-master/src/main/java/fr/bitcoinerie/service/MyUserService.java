package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;

import java.util.List;

public interface MyUserService {

    void save(MyUser myUser);

    void delete(Long id);

    List<MyUser> findAll();

    List<MyUser> findByQuery(String query);

    List<MyUser> findByQueryWithRelations(String query);

    List<MyUser> findUser(String login);

    int count();

    void update(MyUser myUser);

    void doTransaction(MyTransaction trans);


}
