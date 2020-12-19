package recipes.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class for maintaining all recipe objects in this book, as well as their categories. 
 * Interacts with RecipesDB and RecipeBook classes.
 *
 * @see recipes.db.RecipesDB
 * @see recipes.domain.RecipeBook
 */
public class RecipeBook {

    private Map<String, Recipe> recipes;
    private Map<Integer, String> categories;

    /**
     * Constructor, creates a Recipe book with its categories.
     */
    public RecipeBook() {
        this.recipes = new HashMap<>();
        this.categories = new HashMap<>();
        this.setCategories();
    }

    private void setCategories() {
        this.categories.put(1, "AAMIAINEN_BRUNSSI");
        this.categories.put(2, "ALKURUOAT");
        this.categories.put(3, "KEITOT");
        this.categories.put(4, "SALAATIT");
        this.categories.put(5, "LIHARUOAT");
        this.categories.put(6, "KALARUOAT");
        this.categories.put(7, "LINTURUOAT");
        this.categories.put(8, "KASVISRUOAT");
        this.categories.put(9, "JÄLKIRUOAT");
        this.categories.put(10, "LEIVONNAISET");
        this.categories.put(11, "JUOMAT");
        this.categories.put(12, "MUUT");
    }

    /**
     * Adds a recipe to the recipe book.
     * @param name The name of the new recipe
     * @param newRecipe recipe object
     * @return true, if adding to book is successful, otherwise false
     */
    public boolean addRecipe(String name, Recipe newRecipe) {
        if (this.recipes.keySet().contains(name)) {
            return false;
        } else {
            this.recipes.put(name, newRecipe);
            return true;
        }
    }

    /**
     * Gets all the recipes added to the book as Map.
     * @return Map Recipes in the book
     */
    public Map getAllRecipes() {
        return this.recipes;
    }

    /**
     * Gets all recipe categories as List.
     * @return List Recipe categories
     */
    public List getCategories() {
        List<String> categoryList = new ArrayList<>();
        for (String cat : this.categories.values()) {
            categoryList.add(cat);
        }
        return categoryList;
    }

}
