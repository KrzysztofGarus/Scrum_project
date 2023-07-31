package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/app/recipe/list")
public class RecipeList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Tutaj trzeba będzie pobrać id zalogowanego użytkownika najlepiej z sesji, żeby wyświetlić jego przepisy.
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
//        Admin admin = new Admin();
//        admin = AdminDao.read(1);
        RecipeDao recipeDao = new RecipeDao();
//        List<Recipe> recipeList = new ArrayList<>();
//        int ctr = recipeDao.getNumberOfRecipes(admin);
//        for (int i = 0; i < ctr; i++){
//        recipeList.add(recipeDao.read(i+1));
//        }

        List<Recipe> list = recipeDao.readAllForAdmin(userId);


        req.setAttribute("recipes", list);
        getServletContext().getRequestDispatcher("/app-recipes.jsp")
                .forward(req, resp);
    }
}
