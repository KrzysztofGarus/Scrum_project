package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
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
        List<Plan> adminPlans = planDao.readAllAdminPlans(userId);
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> adminRecipes = recipeDao.readAllForAdmin(userId);
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayList = dayNameDao.findAll();
        req.setAttribute("dayList", dayList);
        req.setAttribute("adminPlans", adminPlans);
        req.setAttribute("adminRecipes", adminRecipes);
        getServletContext().getRequestDispatcher("/app-add-recipe-to-plan.jsp").forward(req, resp);
    }
}
