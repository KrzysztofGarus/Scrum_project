package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class PlanDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        int userId = (int) sess.getAttribute("userId");
        Admin admin = AdminDao.read(userId);
            req.setAttribute("adminName", admin.getFirstName());
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        getServletContext().getRequestDispatcher("/app-delete-plan.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        if (id != 0){
            recipePlanDao.delete(id);
            planDao.delete(id);
            getServletContext().getRequestDispatcher("/app/plan/list")
                    .forward(req,resp);
        } else {
            getServletContext().getRequestDispatcher("/app/plan/list")
                    .forward(req,resp);
        }
    }
}
