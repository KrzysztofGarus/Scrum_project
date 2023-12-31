package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        Admin admin = new Admin();
        AdminDao adminDao = new AdminDao();
        admin.setFirstName(name);
        admin.setLastName(surname);

        if (email.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}")) {
            admin.setEmail(email);
        } else {
            email="";
        }
        admin.setPassword(password);
        if (password.equals(repassword) &&
                password.length() > 0 &&
                name.length() > 0 &&
                surname.length() > 0 &&
                email.length() > 0) {
            adminDao.create(admin);
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("name", name);
            req.setAttribute("surname", surname);
            req.setAttribute("email", email);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
        }

    }
}
