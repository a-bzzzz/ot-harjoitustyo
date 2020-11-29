/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aebjork
 */
public class Recipe {

    private String recipeName;
    private int portionAmount;
    private String category;
    private Map<String, String> ingredients;   // ingredient's name and amount
    private List<String> instructions;                  // list of instruction rows
    
    /**
     * Creates tables: Recipes, Stuff, Guidance Recipes: id, name, portions,
     * category, stuff_id, stuff_amount, guidance_id
     */

    public Recipe(String recipeName, int portionAmount, String recipeCategory) {
        this.recipeName = recipeName;
        this.portionAmount = portionAmount;
        this.category = recipeCategory;
        this.ingredients = new HashMap<>();
        this.instructions = new ArrayList<>();
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getPortionAmount() {
        return this.portionAmount;
    }

    @Override
    public String toString() {
        return this.category + ": " + this.recipeName + " - " + this.portionAmount + " annosta";
    }

    public void setPortionAmount(int portionAmount) {
        this.portionAmount = portionAmount;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Map ingredients) {
        this.ingredients = ingredients;
    }

    public List getInstructions() {
        return this.instructions;
    }

    public void setInstructions(List instructions) {
        this.instructions = instructions;
    }
    
}
