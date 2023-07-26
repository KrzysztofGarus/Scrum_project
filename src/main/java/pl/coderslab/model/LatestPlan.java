package pl.coderslab.model;

public class LatestPlan {
    private String day_name;
    private String meal_name;
    private String recipe_name;
    private String recipe_description;
    private String plan_name;

    public LatestPlan(String day_name, String meal_name, String recipe_name, String recipe_description, String plan_name){
        this.day_name = day_name;
        this.meal_name = meal_name;
        this.recipe_name = recipe_name;
        this.recipe_description = recipe_description;
        this.plan_name = plan_name;

    }
    public LatestPlan(){

    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_description() {
        return recipe_description;
    }

    public void setRecipe_description(String recipe_description) {
        this.recipe_description = recipe_description;
    }
}
