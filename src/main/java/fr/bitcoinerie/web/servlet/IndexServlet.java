package fr.bitcoinerie.web.servlet;

import fr.bitcoinerie.domain.Transaction.MyTransaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bphan-luong
 * Date: 02/12/13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("transaction", new MyTransaction(1234, new Date(), "Arielle","Babybel" ) );
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
