package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import fr.bitcoinerie.service.MyTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Date;


@Controller
public class AdminController {

    @Inject
    private MyTransactionService myTransactionService;

    @RequestMapping("/add")
    public String add(Model model) {

        MyTransaction myTransaction = new MyTransaction();


        model.addAttribute("myTransac", myTransaction);

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute MyTransaction myTrans, BindingResult result) {

        if (result.hasErrors()) {
            return "edit";
        }



        myTrans.setDate_temps(new Date());

        myTransactionService.saveTransaction(myTrans);

        return "redirect:/";
    }


    @RequestMapping("/edit/{id_transaction}")
    public String edit(@PathVariable Long id_transaction, Model model) {
        model.addAttribute("transaction", myTransactionService.findById(id_transaction));


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
