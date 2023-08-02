package pl.coderslab.web;


import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class EditPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan planToEdit = planDao.getPlan(planId);
        req.setAttribute("planToEdit", planToEdit);
        req.getServletContext().getRequestDispatcher("/app-edit-schedules.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("planId"));
        String description = req.getParameter("planDescription");
        String planName = req.getParameter("planName");
        Plan plan = new Plan();
        plan.setId(planId);
        plan.setName(planName);
        plan.setDescription(description);
        PlanDao planDao = new PlanDao();
        planDao.update(plan);
        req.getServletContext().getRequestDispatcher("/app/plan/list").forward(req, resp);
    }
}
