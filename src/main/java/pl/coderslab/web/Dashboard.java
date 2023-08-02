package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));
        AdminDao adminDao = new AdminDao();
        RecipeDao recipeDao = new RecipeDao();
        PlanDao planDao = new PlanDao();
        Admin admin = AdminDao.read(userId);
        session.setAttribute("adminName",admin.getFirstName());
        req.setAttribute("recipeCount", recipeDao.getNumberOfRecipes(admin));
        req.setAttribute("scheduleCount", planDao.getNumberOfPlans(admin));

        List<PlanDetails> latestPlans = planDao.latestPlan(userId);
        if (latestPlans.size() != 0) {
            req.setAttribute("lastAddedPlan", latestPlans.get(latestPlans.size()-1).getPlanName());
            req.setAttribute("latestPlan",latestPlans);

        } else {
            req.setAttribute("lastAddedPlan", "Brak");
        }
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
