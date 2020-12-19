package recipes.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.Recipe;

public class RecipeTest {
    
    Recipe testRecipe;
    Recipe anotherRecipe;
    Map<String, String> testIngredients;   // ingredient's name and amount
    List<String> testInstructions;
    
    @Before
    public void setUp() {
        testRecipe = new Recipe("testilettu", 4, "jälkiruoat");
        anotherRecipe = new Recipe("isolettu", 2, "jälkiruoat");
        testIngredients = new HashMap<>();
        testInstructions = new ArrayList<>();
        testRecipe.setID(100);
        anotherRecipe.setID(200);
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
    public void getsRightRecipeName() {
        assertThat("testilettu", is(equalTo(testRecipe.getRecipeName())));
        assertThat("testilettu", is(not(equalTo(anotherRecipe.getRecipeName()))));
    }
    
    @Test
    public void getsRightPortionAmount() {
        assertThat(4, is(equalTo(testRecipe.getPortionAmount())));
        assertThat(4, is(not(equalTo(anotherRecipe.getPortionAmount()))));
    }
    
    @Test
    public void getsRightID() {
        assertThat(100, is(equalTo(testRecipe.getId())));
        assertThat(100, is(not(equalTo(anotherRecipe.getId()))));
    }
    
    @Test
    public void getsRightCategory() {
        assertThat("jälkiruoat", is(equalTo(testRecipe.getCategory())));
        assertThat("juomat", is(not(equalTo(testRecipe.getCategory()))));
    }
    
    @Test
    public void toStringMakesRightOutput() {
        assertThat("jälkiruoat: testilettu - 4 annosta", is(equalTo(testRecipe.toString())));
        assertThat("jälkiruoat: testilettu - 4 annosta", is(not(equalTo(anotherRecipe.toString()))));
    }
    
    @Test
    public void getsRightIngredientsAndAmounts() {
        assertThat("[vehnäjauho, kananmuna, sokeri, suola, maito]", is(equalTo(testRecipe.getIngredientsAndAmounts().keySet().toString())));
        assertThat("[3 dl, 3 kpl, 2 rkl, 1 tl, 6 dl]", is(equalTo(testRecipe.getIngredientsAndAmounts().values().toString())));
    }
    
    @Test
    public void returnsRightIngredientList() {
        assertThat("[vehnäjauho, kananmuna, sokeri, suola, maito]", is(equalTo(testRecipe.getIngredients().toString())));
    }
    
    @Test
    public void returnsRightIAmountList() {
        assertThat("[3 dl, 3 kpl, 2 rkl, 1 tl, 6 dl]", is(equalTo(testRecipe.getAmounts().toString())));
    }
    
    @Test
    public void getsRightInstructions() {
        assertThat("[Vatkaa munien rakenne rikki kulhossa, lisää taikinaan n. 2 dl maitoa ja muut aineet ja vatkaa tasaiseksi., Lisää loppu maito ja sekoita. Anna turvota 30 min., Paista taikinasta ohukaisia rasvassa pannulla., Tarjoa lisänä esim. kermavaahtoa, marjoja, sokeria, hilloa, sokeroitua marjasurvosta tai jäätelöä.]", is(equalTo(testRecipe.getInstructions().toString())));
        assertThat("[vehnäjauho, kananmuna, sokeri, suola, maito]", is(not(equalTo(testRecipe.getInstructions().toString()))));
    }
    
    @Test
    public void createsRecipeDetailsCorrectly() {
        Recipe tikka = new Recipe("tikka masala", 6, "pääruoat");
        assertThat("pääruoat: tikka masala - 6 annosta", is(equalTo(tikka.toString())));
    }
    
    @Test
    public void setsIngredientsCorrectly() {
        Recipe tikka = new Recipe("tikka masala", 6, "pääruoat");
        Map<String, String> stuff = new HashMap<>();
        stuff.put("broilerin leikkeleitä", "350 g");
        stuff.put("turkkilaista jogurttia", "4 dl");
        tikka.setIngredient(stuff);
        assertThat("[turkkilaista jogurttia, broilerin leikkeleitä]", is(equalTo(tikka.getIngredients().toString())));
        assertThat("[4 dl, 350 g]", is(equalTo(tikka.getAmounts().toString())));
    }
    
    @Test
    public void setsIstructionsCorrectly() {
        Recipe tikka = new Recipe("tikka masala", 6, "pääruoat");
        List<String> guide = new ArrayList<>();
        guide.add("Sekoita mausteseos ja puolet siitä jogurtin joukkoon.");
        guide.add("Kääntele broilerit jogurtissa ja anna maustua noin puoli tuntia huoneenlämmössä.");
        tikka.setInstruction(guide);
        assertThat("[Sekoita mausteseos ja puolet siitä jogurtin joukkoon., Kääntele broilerit jogurtissa ja anna maustua noin puoli tuntia huoneenlämmössä.]", is(equalTo(tikka.getInstructions().toString())));
    }
    
}
