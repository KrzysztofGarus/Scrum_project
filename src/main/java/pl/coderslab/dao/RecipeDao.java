package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RecipeDao {

    private static final String GET_RECIPE_BY_ID_QUERY = "SELECT * FROM recipe WHERE id = ?;";
    private static final String READ_NEWEST_RECIPE_QUERY = "SELECT * FROM recipe ORDER BY created DESC LIMIT 5;";
    private static final String READ_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String READ_ALL_ADMIN_RECIPE_QUERY = "SELECT * from recipe where admin_id = ? ORDER BY created DESC;";
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(id, name, ingredients, description, created, preparation_time, preparation, admin_id) " +
            "VALUES (?,?,?,?,NOW(),?,?,?);";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET  name = ?, ingredients = ?, description = ?, updated = NOW(), preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe WHERE id = ?;";

    private static final String FIND_USER_RECIPES_QTY_QUERY = "SELECT COUNT(*) AS 'qty' FROM recipe WHERE admin_id = ?;";
    private static final String IS_RECIPE_IN_PLAN_QUERY = "SELECT * FROM recipe_plan WHERE recipe_id = ?";

    public int getNumberOfRecipes(Admin admin) {
        int qty = 0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_RECIPES_QTY_QUERY);
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            qty = resultSet.getInt("qty");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }

    public void delete(int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setInt(4, recipe.getPreparationTime());
            statement.setString(5, recipe.getPreparation());
            statement.setInt(6, recipe.getAdminId());
            statement.setInt(7, recipe.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, recipe.getId());
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getIngredients());
            statement.setString(4, recipe.getDescription());
            statement.setInt(5, recipe.getPreparationTime());
            statement.setString(6, recipe.getPreparation());
            statement.setInt(7, recipe.getAdminId());


            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RECIPE_BY_ID_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getTimestamp("created"));
//                    recipe.setUpdated(resultSet.getDate("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> readAllForAdmin(int userId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_ADMIN_RECIPE_QUERY)
        ) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public List<Recipe> readNewestRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_NEWEST_RECIPE_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public List<Recipe> readAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_RECIPES_QUERY)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public boolean isRecipeInPlan(int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_RECIPE_IN_PLAN_QUERY)
        ) {
            statement.setInt(1,recipeId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
