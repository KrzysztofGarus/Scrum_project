package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));
        Admin admin = new Admin();
        admin = AdminDao.read(userId);
        req.setAttribute("adminName", admin.getFirstName());

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
