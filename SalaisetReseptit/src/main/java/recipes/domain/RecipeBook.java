package recipes.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aebjork
 */
public class RecipeBook {

    private Map<String, Recipe> recipes;
    private List<String> recipeNames;
    private Map<Integer, String> categories;
//    private Map<Integer,Category> categories;

    public RecipeBook() {
        this.recipes = new HashMap<>();
        this.recipeNames = new ArrayList<>();
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
        this.categories.put(8, "KASVISRUOAT");        this.categories.put(12, "MUUT");

        this.categories.put(9, "JÄLKIRUOAT");
        this.categories.put(10, "LEIVONNAISET");
        this.categories.put(11, "JUOMAT");
    }

// TODO: Poista Category-luokka, CategoryEnum ja ao. metodi, jos niitä ei tarvita    
//    private void setCategories() {
//        for (CategoryEnum cat : CategoryEnum.values()) {
//            this.categories.put(cat.ordinal(),  );
//        }
//    }
    public void setRecipes(Map<String,Recipe> recipeSet) {
        this.recipes = recipeSet;
    }
    
    public List getNames() {
        List<String> nameList = new ArrayList<>();
        for (String name : this.recipes.keySet()) {
            this.recipeNames.add(name);
        }
        if (!this.recipeNames.isEmpty()) {
            for (int i = 0; i < this.recipeNames.size(); i++) {
                nameList.add((i+1) + " - " + this.recipeNames.get(i));
            }
            return nameList;
        }
        return nameList;
    }    

    public List getCategories() {
        List<String> categoryList = new ArrayList<>();
        for (String cat : this.categories.values()) {
            categoryList.add(cat);
        }
        return categoryList;
    }

}
