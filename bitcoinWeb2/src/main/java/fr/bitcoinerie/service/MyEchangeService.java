package fr.bitcoinerie.service;

import fr.bitcoinerie.domain.Echange.MyEchange;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: philippe
 * Date: 20/01/14
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public interface MyEchangeService {
    @Transactional
    void save(MyEchange echange);

    @Transactional
    void delete(Long id);

    @Transactional(readOnly = true)
    List<MyEchange> findAll();

    @Transactional(readOnly = true)
    List<MyEchange> findByEmetteur(String query);

    @Transactional(readOnly = true)
    int count();
}
