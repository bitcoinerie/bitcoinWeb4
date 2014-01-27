package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import fr.bitcoinerie.service.MyTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by marjolaine on 07/01/14.
 */

@Controller
public class AdminController2 {

    @Inject
    private MyTransactionService myTransactionService;

    @RequestMapping("/add")
    public String add(Model model) {

        MyTransaction myTransaction = new MyTransaction();


        model.addAttribute("transaction", myTransaction);

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute("myTransaction") MyTransaction myTransaction, BindingResult result) {

        if (result.hasErrors()) {
            return "edit";
        }


        //myTransaction.setEmetteur("emetteur");
        System.out.println(myTransaction.getEmetteur());
        System.out.println(result);

        myTransaction.setDate_temps(new Date());

        myTransactionService.save(myTransaction);

        return "redirect:/";
    }

    /*

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute("SpringWeb")MyTransaction myTransaction,
                       ModelMap model) {

        MyTransaction myTransaction = new MyTransaction();


        model.addAttribute("name", student.getName());

        myTransactionService.save(myTransaction);

        return "index";
    }

    */


    /*

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }

        taskService.save(task);

        return "redirect:/";
    }
    */
}
