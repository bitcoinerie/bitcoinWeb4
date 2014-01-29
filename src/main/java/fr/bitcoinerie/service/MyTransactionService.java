package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Transaction.MyTransaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marjolaine
 * Date: 23/12/13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */

public interface MyTransactionService {
    //@Override
    //@Transactional
    void save(MyTransaction myTransaction);

    //@Override
    //@Transactional
    void delete(Long id);

    //@Override
    //@Transactional
    List<MyTransaction> findAll();

    //@Override
    //@Transactional
    List<MyTransaction> findByQuery(String query);

    MyTransaction findById(Long id);

    //@Override
    //@Transactional
    int count();

    void update(MyTransaction myTransaction);
}
