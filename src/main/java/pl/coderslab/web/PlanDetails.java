package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/details/")
public class PlanDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //int id =  Integer.parseInt(req.getParameter("id"));
        // co potrzebuję: nazwe planu, opis planu, dni w dobrej kolejności i w nich dania z przypisaną porą oraz id przepisu
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(6);
        List<LatestPlan> list = planDao.latestPlan(1);
        req.setAttribute("plan", plan);
        req.setAttribute("details", list);
        getServletContext().getRequestDispatcher("/app-details-schedules.jsp")
                .forward(req,resp);
    }
}
