package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import fr.bitcoinerie.domain.User.MyUser;
import fr.bitcoinerie.service.MyTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;



@Controller
public class IndexController {
    @Inject
    private MyTransactionService myTransactionService;
    private MyUser Anabelle;
    private MyUser Bernard;
    private MyUser Arnold;
    private MyUser Fabien;
    private MyUser Henri;
    private MyUser Julie;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        Anabelle = new MyUser("Anabelle","Anabelle",100);
        Bernard =  new MyUser("Bernard","Bernard",100);
        model.addAttribute("transaction", new MyTransaction(45, new Date(),Anabelle,Bernard) );
        model.addAttribute("transactions", myTransactionService.findAllTransaction());

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
        List<MyTransaction> myTransactionList = myTransactionService.findByQueryTransaction(query);
        List<String> transactionEmetteur = null;
        for (MyTransaction t: myTransactionList){
            transactionEmetteur.add(t.getEmetteur());
        }
        return transactionEmetteur;
    }
    */

    @RequestMapping("/search")
    public String search(String query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByQueryTransaction(query));
        return "index";
    }

    @PostConstruct
    public void bootstrap() {
        if (myTransactionService.countTransaction()== 0) {

            MyTransaction myTransaction = new MyTransaction();
            Arnold = new MyUser("Arnold","Schwarz",120);
            Fabien = new MyUser("Fabien","Bart", 250);
            myTransaction.setDate_temps(new Date());
            myTransaction.setEmetteur(Arnold);
            myTransaction.setRecepteur(Fabien);
            myTransaction.setMontant(24);
            myTransactionService.saveTransaction(myTransaction);

            MyTransaction myTransaction2 = new MyTransaction();
            Henri = new MyUser("Henri","Fayol", 300);
            Julie = new MyUser("Julie","Poitou",220);

            myTransaction2.setDate_temps(new Date());
            myTransaction2.setEmetteur(Henri);
            myTransaction2.setRecepteur(Julie);
            myTransaction2.setMontant(241);
            myTransactionService.saveTransaction(myTransaction2);

        }

    }
}