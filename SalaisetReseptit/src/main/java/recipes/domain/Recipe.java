package recipes.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class for creating and maintaining recipe objects, the main items of this
 * recipe book. Interacts with RecipesDB class.
 *
 * @see recipes.db.RecipesDB
 */
public class Recipe {

    private int id;
    private String recipeName;
    private int portionAmount;
    private String category;
    /**
     * Ingredients' names and amounts:
     */
    private Map<String, String> ingredients;
    /**
     * List of instruction rows
     */
    private List<String> instructions;

    /**
     * Constructor, creates a Recipe object with its name, amount of portions
     * and recipe category. Note, that the ingredients and the guidelines will
     * be added to the recipe separately in the relevant methods.
     *
     * @param recipeName name of the recipe
     * @param portionAmount how many portions you will make by using the
     * ingredient amounts mentioned in this recipe
     * @param recipeCategory the type of the food or drink
     */
    public Recipe(String recipeName, int portionAmount, String recipeCategory) {
        this.recipeName = recipeName;
        this.portionAmount = portionAmount;
        this.category = recipeCategory;         // TODO: Mahdollisesti muutettava jonkinlaiseksi listaksi..
        this.ingredients = new HashMap<>();
        this.instructions = new ArrayList<>();
    }

    /**
     * Getter, returns the name of the recipe.
     *
     * @return recipe's name
     */
    public String getRecipeName() {
        return this.recipeName;
    }

    /**
     * Getter, returns how many portions you will make by following this recipe.
     *
     * @return portion amount
     */
    public int getPortionAmount() {
        return this.portionAmount;
    }

    /**
     * Getter, returns the recipe category this recipe belongs to.
     *
     * @return the recipe's category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Getter, returns the recipe's identifying id number.
     *
     * @return the recipe's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter, returns recipe's ingredients with amounts.
     *
     * @return list of recipe's ingredients and amounts as Map object, or null
     */
    public Map getIngredientsAndAmounts() {
        return this.ingredients;
    }

    /**
     * Getter, returns recipe's ingredients.
     *
     * @return list of recipe's ingredients as List object, or null
     */
    public List getIngredients() {
        List<String> ingredientList = new ArrayList<>();
        for (String i : this.ingredients.keySet()) {
            ingredientList.add(i);
        }
        return ingredientList;
    }

    /**
     * Getter, returns recipe's ingredient amounts.
     *
     * @return list of recipe's ingredient amounts as List object, or null
     */
    public List getAmounts() {
        List<String> amountList = new ArrayList<>();
        for (String i : this.ingredients.values()) {
            amountList.add(i);
        }
        return amountList;
    }

    /**
     * Getter, returns recipe's instructions.
     *
     * @return list of recipe's guidelines as List object, or null
     */
    public List getInstructions() {
        return this.instructions;
    }

    /**
     * Setter, sets the recipes id received from the recipe database
     *
     * @param recipeID recipe's id number
     */
    public void setID(int recipeID) {
        this.id = recipeID;
    }

    /**
     * Setter, sets a pair of ingredient name and amount for the recipe
     *
     * @param stuff ingredient name
     * @param amount ingredient amount
     */
    public void setIngredient(String stuff, String amount) {
        this.ingredients.put(stuff, amount);
    }
    
    /**
     * Setter, sets a collection of ingredients for the recipe.
     *
     * @param newIngredients ingredients of the recipe
     */
    public void setIngredient(Map newIngredients) {
        this.ingredients = newIngredients;
    }

    /**
     * Setter, sets a row of instruction to the recipe adding it on the list.
     *
     * @param text one line of the recipe instructions, how to make the portion
     */
    public void setInstruction(String text) {
        this.instructions.add(text);
    }
    
    /**
     * Setter, sets a collection of instructions for the recipe.
     *
     * @param instructions instructions of the recipe
     */
    public void setInstruction(List instructions) {
        this.instructions = instructions;
    }

    /**
     * The textual formatting of the Recipe object including its category, name
     * and portion amount.
     *
     * @return short recipe information as String
     */
    @Override
    public String toString() {
        return this.category + ": " + this.recipeName + " - " + this.portionAmount + " annosta";
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
//    public String listInstructions() {
//        String output = "";
//        for (int i = 0; i < this.instructions.size(); i++) {
//            output += ("\n   " + (i + 1) + "  " + this.instructions.get(i));
//        }
//        return output;
//    }    
}
