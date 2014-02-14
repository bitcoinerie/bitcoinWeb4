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

    MyTransaction findByIdTransaction(Long id);

    //@Override
    //@Transactional
    int countTransaction();

    void updateTransaction(MyTransaction myTransaction);
}
