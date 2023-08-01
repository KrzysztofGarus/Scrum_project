package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipeToEdit = recipeDao.read(recipeId);
        req.setAttribute("recipeToEdit", recipeToEdit);
        getServletContext().getRequestDispatcher("/app-edit-recipe.jsp").forward(req, resp);
    }
}
