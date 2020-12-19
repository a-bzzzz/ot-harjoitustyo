package recipes.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.Recipe;
import recipes.domain.RecipeBook;

public class RecipeBookTest {

    RecipeBook testBook;
    Recipe testRecipe;
    Recipe anotherRecipe;
    Map<String, String> testIngredients;   // ingredient's name and amount
    List<String> testInstructions;
  
    @Before
    public void setUp() {
        this.testBook = new RecipeBook();
        testRecipe = new Recipe("testilettu", 4, "jälkiruoat");
        anotherRecipe = new Recipe("isolettu", 2, "jälkiruoat");
        testIngredients = new HashMap<>();
        testInstructions = new ArrayList<>();
        testRecipe.setID(100);
        testRecipe.setIngredient("kananmuna", "3 kpl");
        testRecipe.setIngredient("maito", "6 dl");
        testRecipe.setIngredient("vehnäjauho", "3 dl");
        testRecipe.setIngredient("sokeri", "2 rkl");
        testRecipe.setIngredient("suola", "1 tl");
        testRecipe.setInstruction("Vatkaa munien rakenne rikki kulhossa, lisää taikinaan n. 2 dl maitoa ja muut aineet ja vatkaa tasaiseksi.");
        testRecipe.setInstruction("Lisää loppu maito ja sekoita. Anna turvota 30 min.");
        testRecipe.setInstruction("Paista taikinasta ohukaisia rasvassa pannulla.");
        testRecipe.setInstruction("Tarjoa lisänä esim. kermavaahtoa, marjoja, sokeria, hilloa, sokeroitua marjasurvosta tai jäätelöä.");
    }

    @Test
    public void addsRecipeToBookWhenNameisNew() {
        assertTrue(testBook.addRecipe(testRecipe.getRecipeName(), testRecipe));
    }
    
    @Test
    public void doesNOTaddRecipeToBookWhenNameisUsed() {
        assertTrue(testBook.addRecipe(testRecipe.getRecipeName(), testRecipe));
        assertFalse(testBook.addRecipe(testRecipe.getRecipeName(), testRecipe));
    }
    
    @Test
    public void getsAllRecipes() {
        assertThat("[]", is(equalTo(testBook.getAllRecipes().values().toString())));
        testBook.addRecipe(testRecipe.getRecipeName(), testRecipe);
        testBook.addRecipe(anotherRecipe.getRecipeName(), anotherRecipe);
        assertThat("[jälkiruoat: isolettu - 2 annosta, jälkiruoat: testilettu - 4 annosta]", is(equalTo(testBook.getAllRecipes().values().toString())));
    }
    
    @Test
    public void getsCateories() {
        testBook.addRecipe(anotherRecipe.getRecipeName(), anotherRecipe);
        assertThat("[AAMIAINEN_BRUNSSI, ALKURUOAT, KEITOT, SALAATIT, LIHARUOAT, KALARUOAT, LINTURUOAT, KASVISRUOAT, JÄLKIRUOAT, LEIVONNAISET, JUOMAT, MUUT]", is(equalTo(testBook.getCategories().toString())));
    }

}
