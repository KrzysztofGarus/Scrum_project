package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import java.sql.*;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;


public class RecipeDao {

    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(id, name, ingredients, description, created, preparation_time, preparation, admin_id) " +
            "VALUES (?,?,?,?,NOW(),?,?,?);";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET  name = ?, ingredients = ?, description = ?, updated = NOW(), preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";

    private static final String FIND_USER_RECIPES_QTY_QUERY = "SELECT COUNT(*) as 'qty' FROM recipe WHERE admin_id = ?;";

    public int getNumerOfRecipes(Admin admin) {
        int qty =0;
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

    public void delete(Integer recipeId) {
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
            statement.setInt(7, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setInt(4, recipe.getPreparation_time());
            statement.setString(5, recipe.getPreparation());
            statement.setInt(6, recipe.getAdminId());

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
            statement.setInt(5, recipe.getPreparation_time());
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

    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
//                    recipe.setCreated(resultSet.getDate("created"));
//                    recipe.setUpdated(resultSet.getDate("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}
