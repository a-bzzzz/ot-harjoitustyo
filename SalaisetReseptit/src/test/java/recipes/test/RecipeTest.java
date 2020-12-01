package recipes.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.Recipe;

/**
 *
 * @author aebjork
 */
public class RecipeTest {

    Recipe testRecipe;
    Recipe anotherRecipe;
    private Map<String, String> testIngredients;   // ingredient's name and amount
    private List<String> testInstructions;
    
    @Before
    public void setUp() {
        testRecipe = new Recipe("testilettu", 4, "jälkiruuat");
        anotherRecipe = new Recipe("isolettu", 2, "jälkiruuat");
        this.testIngredients = new HashMap<>();
        this.testInstructions = new ArrayList<>();
        this.testRecipe.setIngredient("kananmuna", "3 kpl");
        this.testRecipe.setIngredient("maito", "6 dl");
        this.testRecipe.setIngredient("vehnäjauho", "3 dl");
        this.testRecipe.setIngredient("sokeri", "2 rkl");
        this.testRecipe.setIngredient("suola", "1 tl");
        this.testRecipe.setInstruction("Vatkaa munien rakenne rikki kulhossa, lisää taikinaan n. 2 dl maitoa ja muut aineet ja vatkaa tasaiseksi.");
        this.testRecipe.setInstruction("Lisää loppu maito ja sekoita. Anna turvota 30 min.");
        this.testRecipe.setInstruction("Paista taikinasta ohukaisia rasvassa pannulla.");
        this.testRecipe.setInstruction("Tarjoa lisänä esim. kermavaahtoa, marjoja, sokeria, hilloa, sokeroitua marjasurvosta tai jäätelöä.");
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
    public void toStringMakesRightOutput() {
        assertThat("jälkiruuat: testilettu - 4 annosta", is(equalTo(testRecipe.toString())));
        assertThat("jälkiruuat: testilettu - 4 annosta", is(not(equalTo(anotherRecipe.toString()))));
    }

    @Test
    public void getsRightCategory() {
        assertThat("jälkiruuat", is(equalTo(testRecipe.getCategory())));
        assertThat("juomat", is(not(equalTo(testRecipe.getCategory()))));
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

}
