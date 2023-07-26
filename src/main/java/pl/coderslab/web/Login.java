package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login") public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

       if (AdminDao.isEmailAndPasswordValid(email,password)) {

           // gdzieś tutaj powinien być kod odpowiedzialny za stworzenie w sesji informacji o tym, że użytkownik jest zalogowany

           resp.sendRedirect(req.getContextPath() + "/app");
        } else {

           // powinno być wyświetlenie informacji o ponownej próbie logowania

           resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
