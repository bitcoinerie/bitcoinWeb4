package fr.bitcoinerie.web.controller;

import fr.bitcoinerie.web.domain.MyTransaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("transaction", new MyTransaction(1, 12.1, new Date()) );

        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }
}
