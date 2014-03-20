package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
import fr.bitcoinerie.service.MyTransactionService;
import fr.bitcoinerie.service.MyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@Controller
public class IndexController {
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

        myUserService.save(Anabelle);
        myUserService.save(Bernard);

        MyTransaction myTransaction =  new MyTransaction(45, new Date(),Anabelle,Bernard);

        myUserService.doTransaction(myTransaction);

        myTransactionService.saveTransaction(myTransaction);

        model.addAttribute("transaction", new MyTransaction(45, new Date(),Anabelle,Bernard) );
        model.addAttribute("transactions", myTransactionService.findAllTransaction());

        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world Transaction BitCoin!";
    }


    @RequestMapping("/historique/searchByDate")
    public String searchByDate(String queryStart, String queryEnd, Model model) {
        //System.out.println("Date : "+query);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {

            Date dateStart = formatter.parse(queryStart);
            Date dateEnd = formatter.parse(queryEnd);
            model.addAttribute("transactionsByQuery", myTransactionService.findByDateTransaction(dateStart, dateEnd));
            //System.out.println(date);
            //System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "index";
    }

    @RequestMapping("/historique/searchByAmountLarger")
    public String searchByAmountLarger(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountLargerTransaction(query));
        return "index";
    }

    @RequestMapping("/historique/searchByAmountSmaller")
    public String searchByAmountSmaller(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountSmallerTransaction(query));
        return "index";
    }

    @RequestMapping("/historique/searchByAmountEquals")
    public String searchByAmountEquals(double query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByAmountEqualsTransaction(query));
        return "index";
    }

    @RequestMapping("/historique/searchByRecepterTransaction")
    public String searchByRecepterTransaction(String query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByRecepterTransaction(query));
        return "index";
    }


    @RequestMapping("/historique/searchByEmetterTransaction")
    public String searchByEmetterTransaction(String query, Model model) {
        model.addAttribute("transactionsByQuery", myTransactionService.findByEmetterTransaction(query));
        return "index";
    }

    @RequestMapping("/transaction/{id_transaction}")
    public String viewTransaction(@PathVariable Long id_transaction, Model model) {
        model.addAttribute("transactionById", myTransactionService.findByIdTransaction(id_transaction));

        System.out.println("transaction by id : "+myTransactionService.findByIdTransaction(id_transaction));

        return "index";
    }


    @PostConstruct
    public void bootstrap() {
        if (myTransactionService.countTransaction()== 0) {

            MyTransaction myTransaction = new MyTransaction();
            Arnold = new MyUser("Arnold","Schwarz",120);
            Fabien = new MyUser("Fabien","Bart", 250);
            myUserService.save(Arnold);
            myUserService.save(Fabien);

            myTransaction.setDate_temps(new Date());

            myTransaction.setEmetteur(Arnold);
            myTransaction.setRecepteur(Fabien);
            myTransaction.setMontant(24);

//            Arnold.getListe_depenses().add(myTransaction);
//            Fabien.getListe_depenses().add(myTransaction);
//
//            myUserService.save(Arnold);
//            myUserService.save(Fabien);

            myUserService.doTransaction(myTransaction);

            myTransaction.setEmetteur(myUserService.findByQuery("Arnold").get(0));
            myTransaction.setRecepteur(myUserService.findByQuery("Fabien").get(0));

            System.out.println(" >>>>>>>>>>> emetteur "+myTransaction.getEmetteur().getMontant_compte());
            System.out.println(" >>>>>>>>>>> recepteur "+myTransaction.getRecepteur().getMontant_compte());

            myTransactionService.saveTransaction(myTransaction);

            MyTransaction myTransaction2 = new MyTransaction();
            Henri = new MyUser("Henri","Fayol", 300);
            Julie = new MyUser("Julie","Poitou",220);

            myTransaction2.setDate_temps(new Date());
            myTransaction2.setEmetteur(Henri);
            myTransaction2.setRecepteur(Julie);
            myTransaction2.setMontant(241);

//            Henri.getListe_depenses().add(myTransaction2);
//            Julie.getListe_depenses().add(myTransaction2);

            myUserService.save(Henri);
            myUserService.save(Julie);
            myTransactionService.saveTransaction(myTransaction2);
            myUserService.doTransaction(myTransaction2);

        }

    }
}