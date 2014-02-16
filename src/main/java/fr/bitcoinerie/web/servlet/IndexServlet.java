package fr.bitcoinerie.web.servlet;

import fr.bitcoinerie.domain.Transaction.MyTransaction;
import fr.bitcoinerie.domain.User.MyUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MyUser Arielle;
    private MyUser Babybel;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Arielle = new MyUser("Arielle","Sirène", 120);
        Babybel = new MyUser("BabyBel","Babel",420);
        request.setAttribute("transaction", new MyTransaction(1234, new Date(), Arielle,Babybel ) );
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
