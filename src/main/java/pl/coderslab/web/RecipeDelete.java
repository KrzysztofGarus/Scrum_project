package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/delete")
public class RecipeDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        int userId = (int) sess.getAttribute("userId");
        Admin admin = AdminDao.read(userId);
        req.setAttribute("adminName", admin.getFirstName());
        int id = Integer.parseInt(req.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        if (id != 0) {
            if (!recipeDao.isRecipeInPlan(id)) {
                req.setAttribute("id", id);
                getServletContext().getRequestDispatcher("/app-delete-recipe.jsp")
                        .forward(req, resp);
            } else if (recipeDao.isRecipeInPlan(id)) {
                req.setAttribute("id", id);
                RecipePlanDao recipePlanDao = new RecipePlanDao();
                req.setAttribute("recipePlans", recipePlanDao.readRecipePlans(id));
                getServletContext().getRequestDispatcher("/app-show-plans-for-recipe.jsp")
                        .forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/app/recipe/list")
                    .forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        if (id != 0) {
            recipeDao.delete(id);
            getServletContext().getRequestDispatcher("/app/recipe/list")
                    .forward(req, resp);

        } else {
            getServletContext().getRequestDispatcher("/app/recipe/list")
                    .forward(req, resp);
        }
    }
}

