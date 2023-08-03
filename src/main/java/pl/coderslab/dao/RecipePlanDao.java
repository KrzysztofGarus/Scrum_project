package pl.coderslab.dao;

import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {
    private static final String INSERT_RECIPE_INTO_A_PLAN_BY_PLAN_ID_QUERY = "INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String LIST_PLANS_TO_RECIPE_QUERY = "SELECT DISTINCT p.name AS name,plan_id AS planId FROM recipe_plan rp  JOIN plan p ON p.id = rp.plan_id WHERE recipe_id =?;";

    public void insert(int recipeId, String mealName, int displayOrder, int dayNameId, int planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RECIPE_INTO_A_PLAN_BY_PLAN_ID_QUERY)) {
            statement.setInt(1, recipeId);
            statement.setString(2, mealName);
            statement.setInt(3, displayOrder);
            statement.setInt(4, dayNameId);
            statement.setInt(5, planId);
            int i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Plan> readRecipePlans(int recipeId) {
        List<Plan> plansList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_PLANS_TO_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setName(resultSet.getString("name"));
                plan.setId(resultSet.getInt("planId"));
                plansList.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plansList;
    }

}
