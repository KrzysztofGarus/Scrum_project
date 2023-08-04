package pl.coderslab.web;


import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/delete")
public class DeleteRecipeFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        int userId = (int) sess.getAttribute("userId");
        Admin admin = AdminDao.read(userId);
        req.setAttribute("adminName", admin.getFirstName());

        String dayName = req.getParameter("dayName");
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        int planId = Integer.parseInt(req.getParameter("planId"));
        String mealName = req.getParameter("mealName");


        req.setAttribute("planId", planId);
        req.setAttribute("recipeId", recipeId);
        req.setAttribute("dayName", dayName);
        req.setAttribute("mealName", mealName);

        getServletContext().getRequestDispatcher("/app-delete-recipe-from-plan.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dayName = req.getParameter("dayName");
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        int planId = Integer.parseInt(req.getParameter("planId"));
        String mealName = req.getParameter("mealName");
        String agree = req.getParameter("agree");

        if ("agreement".equals(agree)) {
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            recipePlanDao.deleteRecipeFromPlan(dayName, planId, mealName, recipeId);

        }
            PlanDao planDao = new PlanDao();
            Plan plan = planDao.getPlan(planId);
            List<PlanDetails> list = planDao.planDetails(planId);
            req.setAttribute("plan", plan);
            req.setAttribute("details", list);
            req.setAttribute("id",planId);

            getServletContext().getRequestDispatcher("/app-details-schedules.jsp").forward(req,resp);

    }
}
