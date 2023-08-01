package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HomeServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String planId = req.getParameter("planId");
        String mealName = req.getParameter("mealName");
        String displayOrder = req.getParameter("displayOrder"); // czy to jest numer posilku
        String recipeId = req.getParameter("recipeId"); // przepis
        String dayNameId = req.getParameter("dayNameId"); // dayNameId
        PlanDao planDao = new PlanDao();
        Plan plan = new Plan();

        planDao.update(planId);
        req.getServletContext().getRequestDispatcher("/app/recipe/plan/add").forward(req, resp);

    }
}
