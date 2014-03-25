package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.MyTransaction;

import java.util.Date;
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
    List<MyTransaction> findByDateTransaction(Date queryS, Date queryE);

    List<MyTransaction> findByAmountLargerTransaction(double query);
    List<MyTransaction> findByAmountSmallerTransaction(double query);
    List<MyTransaction> findByAmountEqualsTransaction(double query);

    List<MyTransaction> findByEmetterTransaction(String query);
    List<MyTransaction> findByRecepterTransaction(String query);

    List<MyTransaction> findByEmetterId(Long id_user);
    List<MyTransaction> findByRecepterId(Long id_user);
    List<MyTransaction> findByUser(Long id_user);

    MyTransaction findByIdTransaction(Long id);

    //@Override
    //@Transactional
    int countTransaction();

    void updateTransaction(MyTransaction myTransaction);
}
