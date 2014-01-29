package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Transaction.MyTransaction;

import java.util.List;



public interface MyTransactionService {
    //@Override
    //@Transactional
    void saveTransaction(MyTransaction myTransaction);

    //@Override
    //@Transactional
    void deleteTransaction(Long id);

    //@Override
    //@Transactional
    List<MyTransaction> findAllTransaction();

    //@Override
    //@Transactional
    List<MyTransaction> findByQueryTransaction(String query);

    MyTransaction findById(Long id);

    //@Override
    //@Transactional
    int count();

    void update(MyTransaction myTransaction);
}
