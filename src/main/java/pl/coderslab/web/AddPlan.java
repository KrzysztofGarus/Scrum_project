package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/add")
public class AddPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        int userId = (int) sess.getAttribute("userId");
        Admin admin = AdminDao.read(userId);
        req.setAttribute("adminName", admin.getFirstName());
        getServletContext().getRequestDispatcher("/app-add-schedules.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute("userId");

        Plan plan = new Plan(planName, planDescription, userId);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);

        resp.sendRedirect("/app/plan/list");
    }
}
