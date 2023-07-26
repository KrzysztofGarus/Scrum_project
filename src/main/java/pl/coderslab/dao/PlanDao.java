package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Book;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,NOW(),?);";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ? WHERE	id = ?;";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ADMIN_PLANS_QTY_QUERY = "SELECT COUNT(*) as 'qty' FROM plan WHERE admin_id = ?;";
    private static final String LATEST_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, plan.name\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN plan on plan.id = plan_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";


    public int getNumerOfPlans(Admin admin) {
        int qty =0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_PLANS_QTY_QUERY);
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            qty = resultSet.getInt("qty");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }

    public Plan create (Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PLAN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)){
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getAdminId());
            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                    plan.setCreated(resultSet.getString("created"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(3, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planToAdd.setCreated(resultSet.getString("created"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }
    public static List<LatestPlan> latestPlan(int userId){
        List<LatestPlan> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LATEST_PLAN_QUERY)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                LatestPlan plan = new LatestPlan();
                plan.setDay_name(resultSet.getString("day_name"));
                plan.setMeal_name(resultSet.getString("meal_name"));
                plan.setRecipe_name(resultSet.getString("recipe_name"));
                plan.setRecipe_description(resultSet.getString("recipe_description"));
                plan.setPlan_name(resultSet.getString("name"));
                list.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}