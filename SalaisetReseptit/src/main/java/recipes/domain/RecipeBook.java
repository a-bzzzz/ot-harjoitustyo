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

    private Map<String, Recipe> allRecipes; // Tänne haetaan kaikki tietokannasta löytyvät reseptit
//    private Map<String, Recipe> selectedRecipes; // Tänne valitaan reseptejä hakutuloksen perusteella, EI välttämättä kaikkia reseptejä
//    private List<String> allRecipeNames;
//    private List<String> selectedRecipeNames;
    private Map<Integer, String> categories;
//    private Map<Integer,Category> categories;

    public RecipeBook() {
        this.allRecipes = new HashMap<>();
//        this.allRecipeNames = new ArrayList<>();
//        this.selectedRecipes = new HashMap<>();
//        this.selectedRecipeNames = new ArrayList<>();
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

// TODO: Poista Category-luokka, CategoryEnum ja ao. metodi, jos niitä ei tarvita    
//    private void setCategories() {
//        for (CategoryEnum cat : CategoryEnum.values()) {
//            this.categories.put(cat.ordinal(),  );
//        }
//    }
    public boolean addRecipe(String name, Recipe newRecipe) {
        if (this.allRecipes.keySet().contains(name)) {
            return false;
        } else {
            this.allRecipes.put(name, newRecipe);
//            this.allRecipeNames.add(name);
            return true;
        }
    }

//    public boolean addSelectedRecipe(String name, Recipe recipe) {
//        if (this.selectedRecipes.keySet().contains(name)) {
//            return false;
//        } else {
//            this.selectedRecipes.put(name, recipe);
//            this.selectedRecipeNames.add(name);
//            return true;
//        }
//    }

    public Map getAllRecipes() {
        return this.allRecipes;
    }

//    public Map getSelectedRecipes() {
//        return this.selectedRecipes;
//    } 
    
    // TODO: tämä ei toimi! ei mitään virkaa!
    public List listAllNames() {
        List<String> nameList = new ArrayList<>();
//        for (String name : this.allRecipes.keySet()) {
//            System.out.println("Tulostettavien listalle lisätty reseptinimi: " + name);
//            this.recipeNames.add(name);
//        }
//        if (!this.allRecipeNames.isEmpty()) {
//            for (int i = 0; i < this.allRecipeNames.size(); i++) {
//                nameList.add((i + 1) + " - " + this.allRecipeNames.get(i));
//            }
////            return nameList;
//        }
        if (!this.allRecipes.isEmpty()) {
            for (Recipe recipe : this.allRecipes.values()) {
                String row = (recipe.getId() + " - " + recipe.getRecipeName());
                System.out.println(row);
                nameList.add(row);
            }
//            return nameList;
        }
        return nameList;
    }

    // TODO: Tarvitaanko erikseen metodit kaikille ja valituille resepteille? Toisteisuutta!
//    public List listSelectedNames() {
//        List<String> nameList = new ArrayList<>();
////        for (String name : this.selectedRecipes.keySet()) {
////            this.selectedRecipeNames.add(name);
////        }
////        if (!this.selectedRecipeNames.isEmpty()) {
////            for (int i = 0; i < this.selectedRecipeNames.size(); i++) {
////                String row = ((i + 1) + " - " + this.selectedRecipeNames.get(i));
////                System.out.println(row);
////                nameList.add(row);
////            }
//////            return nameList;
////        }
//        if (!this.selectedRecipes.isEmpty()) {
//            for (Recipe recipe : this.selectedRecipes.values()) {
//                String row = (recipe.getId() + " - " + recipe.getRecipeName());
//                System.out.println(row);
//                nameList.add(row);
//            }
////            return nameList;
//        }
//        return nameList;
//    }

    public List getCategories() {
        List<String> categoryList = new ArrayList<>();
        for (String cat : this.categories.values()) {
            categoryList.add(cat);
        }
        return categoryList;
    }

}
