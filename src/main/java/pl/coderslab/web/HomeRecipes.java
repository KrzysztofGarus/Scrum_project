package pl.coderslab.web;


import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/recipes")
public class HomeRecipes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> newestRecipes = recipeDao.readNewestRecipes();
        req.setAttribute("newestRecipes", newestRecipes);
        req.getServletContext().getRequestDispatcher("/unregistered-recipes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchedRecipe = req.getParameter("searchedRecipe");
        Pattern compiledPattern = Pattern.compile(searchedRecipe, Pattern.CASE_INSENSITIVE);

        RecipeDao recipeDao = new RecipeDao();

        List<Recipe> foundRecipes = new ArrayList<>();
        List<Recipe> allRecipesList = recipeDao.readAllRecipes();

        for (Recipe recipe : allRecipesList) {
           Matcher matcher = compiledPattern.matcher(recipe.getName());
            if(matcher.find()){
                foundRecipes.add(recipe);
            }
        }
        req.setAttribute("newestRecipes", foundRecipes);
        getServletContext().getRequestDispatcher("/unregistered-recipes.jsp").forward(req,resp);

    }
}

