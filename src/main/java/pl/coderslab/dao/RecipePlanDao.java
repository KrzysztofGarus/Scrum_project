package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecipePlanDao {
    private static final String INSERT_RECIPE_INTO_A_PLAN_BY_PLAN_ID_QUERY = "INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";

    public void insert(int recipeId, String mealName, int displayOrder, String dayName, int planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RECIPE_INTO_A_PLAN_BY_PLAN_ID_QUERY)) {
            statement.setInt(1, recipeId);
            statement.setString(2, mealName);
            statement.setInt(3, displayOrder);
            statement.setString(4, dayName);
            statement.setInt(5, planId);
            int i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
