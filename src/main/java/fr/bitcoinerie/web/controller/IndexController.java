package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import fr.bitcoinerie.service.MyTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 04/12/13
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class IndexController {
    @Inject
    private MyTransactionService myTransactionService;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("transaction", new MyTransaction(45, new Date(),"Anabelle","Bernard") );
        model.addAttribute("transactions", myTransactionService.findAll());

        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world Transaction BitCoin!";
    }

    /*
    @RequestMapping("/search")
    public List<String> search(String query, Model model) {
        List<MyTransaction> myTransactionList = myTransactionService.findByQuery(query);
        List<String> transactionEmetteur = null;
        for (MyTransaction t: myTransactionList){
            transactionEmetteur.add(t.getEmetteur());
        }
        return transactionEmetteur;
    }
    */

    @RequestMapping("/search")
    public String search(String query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByQuery(query));
        return "index";
    }

    @PostConstruct
    public void bootstrap() {
        if (myTransactionService.count()== 0) {

            MyTransaction myTransaction = new MyTransaction();

            myTransaction.setDate_temps(new Date());
            myTransaction.setEmetteur("Arnold");
            myTransaction.setRecepteur("Fabien");
            myTransaction.setMontant(24);
            myTransactionService.save(myTransaction);

            MyTransaction myTransaction2 = new MyTransaction();

            myTransaction2.setDate_temps(new Date());
            myTransaction2.setEmetteur("Henri");
            myTransaction2.setRecepteur("Jo");
            myTransaction2.setMontant(241);
            myTransactionService.save(myTransaction2);

            MyTransaction myTransaction3 = new MyTransaction();

            myTransaction3.setDate_temps(new Date());
            myTransaction3.setEmetteur("Clement");
            myTransaction3.setRecepteur("Pascal");
            myTransaction3.setMontant(58);
            myTransactionService.save(myTransaction3);
        }

    }
}