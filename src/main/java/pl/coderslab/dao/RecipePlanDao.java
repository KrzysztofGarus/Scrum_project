package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
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
    private static final String DELETE_RECIPE_PLAN_BY_PLAN_ID = "DELETE FROM recipe_plan WHERE plan_id = ?";

    private static final  String DELETE_RECIPE_FROM_PLAN ="delete from recipe_plan  where id in (select id from (\n" +
            "                                      select rp.id from recipe_plan rp JOIN day_name dn on rp.day_name_id = dn.id\n" +
            "                                      where dn.name = ?\n" +
            "                                        AND rp.plan_id=?\n" +
            "                                        AND rp.meal_name=?\n" +
            "                                        AND rp.recipe_id =?) as recipe_id);";
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
    public void delete(int planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_BY_PLAN_ID)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipeFromPlan(String dayName, int planId, String mealName, int recipeId){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_FROM_PLAN)) {

            statement.setString(1, dayName);
            statement.setInt(2, planId);
            statement.setString(3, mealName);
            statement.setInt(4, recipeId);

            int deleted = statement.executeUpdate();
            if (deleted == 1) {
                System.out.println("record has been deleted");

            } else{
                System.out.println("smth went wrong ;(");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
