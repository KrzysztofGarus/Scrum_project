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
import java.nio.charset.StandardCharsets;


@WebServlet("/app/recipe/add")
public class AddRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-add-recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        HttpSession sess = req.getSession();

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = new Recipe();
        recipe.setName(req.getParameter("name"));
        recipe.setDescription(req.getParameter("description"));
        recipe.setPreparation_time(Integer.parseInt(req.getParameter("preparationTime")));
        recipe.setPreparation(req.getParameter("preparation"));
        recipe.setIngredients(req.getParameter("ingredients"));
        recipe.setAdminId((int) sess.getAttribute("userId"));
        recipeDao.create(recipe);

        resp.sendRedirect("/app/recipe/list/");

    }
}
