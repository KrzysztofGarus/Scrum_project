package pl.coderslab.web;

import pl.coderslab.model.Recipe;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/details")
public class RecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(id);
        String[] ingredients = recipe.getIngredients().split(",");
        req.setAttribute("recipe", recipe);
        req.setAttribute("ingredients", ingredients);
        getServletContext().getRequestDispatcher("/app-recipe-details.jsp")
                .forward(req,resp);
    }
}
