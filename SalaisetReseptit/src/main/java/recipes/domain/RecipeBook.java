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
    private Map<Integer, String> categories;
//    private Map<Integer,Category> categories;

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

// TODO: Poista Category-luokka, CategoryEnum ja ao. metodi, jos niitä ei tarvita    
//    private void setCategories() {
//        for (CategoryEnum cat : CategoryEnum.values()) {
//            this.categories.put(cat.ordinal(),  );
//        }
//    }
    public List getCategories() {
        List<String> categoryList = new ArrayList<>();
        for (String cat : this.categories.values()) {
            categoryList.add(cat);
        }
        return categoryList;
    }

}
