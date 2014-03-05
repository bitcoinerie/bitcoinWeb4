package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
import fr.bitcoinerie.domain.MyEchange;
import fr.bitcoinerie.service.MyEchangeService;
import fr.bitcoinerie.service.MyTransactionService;
import fr.bitcoinerie.service.MyUserService;
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
    private MyEchangeService myEchangeService;
    @Inject
    private MyTransactionService myTransactionService;

    @Inject
    private MyUserService myUserService;

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
        List<MyTransaction> myTransactionList = myTransactionService.findByDateTransaction(query);
        List<String> transactionEmetteur = null;
        for (MyTransaction t: myTransactionList){
            transactionEmetteur.add(t.getEmetteur());
        }
        return transactionEmetteur;
    }
    */

    @RequestMapping("/searchByDate")
    public String searchByDate(Date query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByDateTransaction(query));
        return "index";
    }

    @RequestMapping("/searchByAmountLarger")
    public String searchByAmountLarger(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountLargerTransaction(query));
        return "index";
    }

    @RequestMapping("/searchByAmountSmaller")
    public String searchByAmountSmaller(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountSmallerTransaction(query));
        return "index";
    }

    @RequestMapping("/searchByAmountEquals")
    public String searchByAmountEquals(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountEqualsTransaction(query));
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

            Arnold.getListe_dépenses().add(myTransaction);
            Fabien.getListe_dépenses().add(myTransaction);

            myUserService.save(Arnold);
            myUserService.save(Fabien);
            myTransactionService.saveTransaction(myTransaction);

            MyTransaction myTransaction2 = new MyTransaction();
            Henri = new MyUser("Henri","Fayol", 300);
            Julie = new MyUser("Julie","Poitou",220);

            myTransaction2.setDate_temps(new Date());
            myTransaction2.setEmetteur(Henri);
            myTransaction2.setRecepteur(Julie);
            myTransaction2.setMontant(241);

            Henri.getListe_dépenses().add(myTransaction2);
            Julie.getListe_dépenses().add(myTransaction2);

            myUserService.save(Henri);
            myUserService.save(Julie);
            myTransactionService.saveTransaction(myTransaction2);

        }

    }
}