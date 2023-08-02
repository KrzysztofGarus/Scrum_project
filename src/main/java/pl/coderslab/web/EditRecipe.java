package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipeToEdit = recipeDao.getRecipeById(recipeId);
        req.setAttribute("recipeToEdit", recipeToEdit);
        getServletContext().getRequestDispatcher("/app-edit-recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int adminId = (int) session.getAttribute("userId");

        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
        String recipeName = req.getParameter("name");
        String ingredients = req.getParameter("ingredients");
        String description = req.getParameter("description");
        int preparationTime = Integer.parseInt(req.getParameter("preparationTime"));

        String preparation = req.getParameter("preparation");
        Recipe recipe = new Recipe();
        recipe.setAdminId(adminId);
        recipe.setId(recipeId);
        recipe.setName(recipeName);
        recipe.setIngredients(ingredients);
        recipe.setDescription(description);
        recipe.setPreparationTime(preparationTime);
        recipe.setPreparation(preparation);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update(recipe);
        req.getServletContext().getRequestDispatcher("/app/recipe/list").forward(req,resp);
    }
}
