package pl.coderslab.web;


import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/schedule/edit")
public class EditPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan planToEdit = planDao.getPlan(planId);
        req.setAttribute("planToEdit", planToEdit);
        req.getServletContext().getRequestDispatcher("/app-edit-schedules.jsp").forward(req, resp);
    }
}
