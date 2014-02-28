package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.MyTransaction;
import fr.bitcoinerie.domain.MyUser;
import fr.bitcoinerie.service.MyTransactionService;
import fr.bitcoinerie.service.MyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@Controller
public class AdminController {

    @Inject
    private MyTransactionService myTransactionService;

    @Inject
    private MyUserService myUserService;

    private MyUser myEmet;
    private MyUser myRecept;

    @RequestMapping("/add")
    public String add(Model model) {

        MyTransaction myTransaction = new MyTransaction();

        List<MyUser> emetteur = myUserService.findByQuery("Arnold");
        List<MyUser> recepteur = myUserService.findByQuery("Fabien");

        myEmet = emetteur.get(0);
        myRecept = recepteur.get(0);

        //myTransaction.setEmetteur(myEmet);
        //myTransaction.setRecepteur(myRecept);

        model.addAttribute("myTransac", myTransaction);
        model.addAttribute("myEmetteur",myEmet );
        model.addAttribute("myRecepteur", myRecept );

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute MyTransaction myTrans, BindingResult result) {

        if (result.hasErrors()) {
            return "edit";
        }

        myTrans.setDate_temps(new Date());

        List<MyUser> emetteur = myUserService.findByQueryWithRelations(myEmet.getPrenom());
        List<MyUser> recepteur = myUserService.findByQueryWithRelations(myRecept.getPrenom());

        myTrans.setEmetteur(emetteur.get(0));
        myTrans.setRecepteur(recepteur.get(0));

        emetteur.get(0).getListe_dépenses().add(myTrans);
        recepteur.get(0).getListe_recettes().add(myTrans);

        // Doit faire un update au lieu d'un save      sur  myUserService
        //myUserService.save(myEmet);
        //myUserService.save(myRecept);


        myTransactionService.saveTransaction(myTrans);

        return "redirect:/";
    }


    @RequestMapping("/edit/{id_transaction}")
    public String edit(@PathVariable Long id_transaction, Model model) {
        model.addAttribute("transaction", myTransactionService.findByIdTransaction(id_transaction));

        return "edit";
    }

    /*

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute("SpringWeb")MyTransaction myTransaction,
                       ModelMap model) {

        MyTransaction myTransaction = new MyTransaction();


        model.addAttribute("name", student.getName());

        myTransactionService.saveTransaction(myTransaction);

        return "index";
    }

    */


    /*

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }

        taskService.saveTransaction(task);

        return "redirect:/";
    }
    */
}
