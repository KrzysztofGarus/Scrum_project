package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
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

        resp.sendRedirect("/app-add-schedules.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));

        Plan plan = new Plan();
        plan.setName(planName);
        plan.setDescription(planDescription);
        plan.setAdminId(userId);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);

        resp.sendRedirect("/app/dashboard");
    }
}
