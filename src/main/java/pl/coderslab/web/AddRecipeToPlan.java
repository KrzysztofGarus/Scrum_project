package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        int userId = (int) sess.getAttribute("userId");
        Admin admin = AdminDao.read(userId);
        req.setAttribute("adminName", admin.getFirstName());
        PlanDao planDao = new PlanDao();
        List<Plan> adminPlans = planDao.getAllPlansForUserId(userId);
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> adminRecipes = recipeDao.readAllForAdmin(userId);
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayList = dayNameDao.findAll();
        req.setAttribute("dayList", dayList);
        req.setAttribute("adminPlans", adminPlans);
        req.setAttribute("adminRecipes", adminRecipes);
        getServletContext().getRequestDispatcher("/app-add-recipe-to-plan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        String mealName = req.getParameter("mealName");
        int planId = Integer.parseInt(req.getParameter("planId"));
        int displayOrder = Integer.parseInt(req.getParameter("displayOrder"));
        String dayName = req.getParameter("dayName");
        DayNameDao dayNameDao = new DayNameDao();
        int dayNameId = dayNameDao.getIdForDayName(dayName);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.insert(recipeId, mealName, displayOrder, dayNameId, planId);
      //  resp.sendRedirect("/app/recipe/list"); TODO czy nie lepiej po dodaniu wrocic do strony wejsciowej..!?
        resp.sendRedirect("/app/recipe/plan/add");
    }
}