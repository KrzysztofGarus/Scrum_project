package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession sess = req.getSession();
        if (sess.getAttribute("userId") == null) {
            getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {


            getServletContext().getRequestDispatcher("/app/dashboard").forward(req, resp);
        }


    }
}
