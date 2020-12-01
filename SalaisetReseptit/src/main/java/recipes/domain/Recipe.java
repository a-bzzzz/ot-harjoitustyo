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
    private List<String> instructions;         // list of instruction rows

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

//    public void setRecipeName(String recipeName) {
//        this.recipeName = recipeName;
//    }
    
    public int getPortionAmount() {
        return this.portionAmount;
    }

    @Override
    public String toString() {
        return this.category + ": " + this.recipeName + " - " + this.portionAmount + " annosta";
    }

//    public void setPortionAmount(int portionAmount) {
//        this.portionAmount = portionAmount;
//    }
    
    public String getCategory() {
        return this.category;
    }

//    public void setCategory(String category) {
//        this.category = category;
//    }
    
    public Map getIngredientsAndAmounts() {
        return this.ingredients;
    }

    public List getIngredients() {
        List<String> ingredientList = new ArrayList<>();
        for (String i : this.ingredients.keySet()) {
            ingredientList.add(i);
        }
        return ingredientList;
    }

    public List getAmounts() {
        List<String> amountList = new ArrayList<>();
        for (String i : this.ingredients.values()) {
            amountList.add(i);
        }
        return amountList;
    }

//    public String listIngredients() {
//        String output = "";
//        for (String stuff : this.ingredients.keySet()) {
//            output += ("\n" + stuff);
//        }
//        return output;
//    }

//    public String listAmounts() {
//        String output = "";
//        for (String amount : this.ingredients.values()) {
//            output += ("\n   " + amount);
//        }
//        return output;
//    }
    
    public void setIngredient(String stuff, String amount) {
        this.ingredients.put(stuff, amount);
    }

    public List getInstructions() {
        return this.instructions;
    }

//    public String listInstructions() {
//        String output = "";
//        for (int i = 0; i < this.instructions.size(); i++) {
//            output += ("\n   " + (i + 1) + "  " + this.instructions.get(i));
//        }
//        return output;
//    }

    public void setInstruction(String text) {
        this.instructions.add(text);
    }

}
