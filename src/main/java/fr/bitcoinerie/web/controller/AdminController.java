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
import javax.validation.Valid;
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

    @RequestMapping("/addTransaction/add")
    public String addTransactionToViews(Model model) {

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

        myUserService.doTransaction(myTrans);

        // Doit faire un update au lieu d'un save      sur  myUserService
        //myUserService.update(myEmet);
        //myUserService.update(myRecept);


        myTransactionService.saveTransaction(myTrans);

        return "redirect:/";
    }


    @RequestMapping("/edit/{id_transaction}")
    public String edit(@PathVariable Long id_transaction, Model model) {
        model.addAttribute("myTransac", myTransactionService.findByIdTransaction(id_transaction));

        return "edit";
    }

    @RequestMapping("/new_user")
    public String add(Model model) {
        // on injecte un utilisateur vierge dans le modèle
        model.addAttribute("user", new MyUser());

        return "new_user";
    }

    @RequestMapping(value = "/new_user", method = RequestMethod.POST)
    public String post(@ModelAttribute @Valid MyUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "new_user";
        }

        myUserService.save(user);

        return "redirect:/";
    }

}
