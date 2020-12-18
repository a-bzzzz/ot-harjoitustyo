package recipes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import recipes.domain.Recipe;
import recipes.domain.RecipeBook;

/**
 * This class/data access object offers an interface to the actual recipe
 * database. GUI calls methods of this class, and this class is in connection to
 * Recipe class.
 *
 * @see recipes.domain.Recipe
 */
public class RecipesDB {

    private String dbase;
    private String path;

    private Connection db;
    private Statement st;
    private PreparedStatement p;
    private ResultSet r;

    private int recipeID;
    private Recipe recipe;
    private Map<String, Recipe> recipes;
    private RecipeBook book;

    /**
     * Constructor, creates a RecipeDB object, which is responsible for
     * connection to the actual recipe database
     *
     * @param dbase name of the recipe database
     */
    public RecipesDB(String dbase) {
        this.dbase = dbase;
        this.path = "jdbc:sqlite:" + this.dbase + ".db";
        this.recipeID = 0;
        this.book = new RecipeBook();
    }

    /**
     * Returns the name of the path of the database in use
     *
     * @return path name of the database as String
     */
    public String getDBPath() {
        return this.path;
    }

    /**
     * Creates the database for recipes with the following tables: Recipes,
     * Stuff, Guidance Recipes: id, name, portions, category, idx_recipe_id
     * Stuff: id, stuff_name, amount Guidance: id, row, text
     *
     * @return true, if the recipe database is created successfully; otherwise
     * false
     */
    public boolean createRecipeDB() {
        boolean success = this.getConnected();
        try {
            st.execute("BEGIN TRANSACTION");
            st.execute("PRAGMA foreign_keys = ON");
            st.execute("CREATE TABLE Recipes(id INTEGER PRIMARY KEY NOT NULL UNIQUE, name TEXT NOT NULL, portions INTEGER, category TEXT)");
            st.execute("CREATE INDEX idx_recipe_id ON Recipes (id)");
            st.execute("CREATE TABLE Stuff(id INTEGER NOT NULL, stuff_name TEXT, amount TEXT)");
            st.execute("CREATE TABLE Guidance(id INTEGER NOT NULL, row INTEGER NOT NULL, text TEXT)");
            st.execute("COMMIT");
            db.close();
            System.out.println("Database " + this.dbase + ".db has been created.");
        } catch (SQLException s) {
            success = this.handleError(s);
            return success;
        }
        return success;
    }

    // TODO: tee, testaa ja kommentoi
    public Map getAllRecipes() {
        boolean success = this.getConnected();
        List<String> recipeNames = new ArrayList<>();
        try {
            st.execute("BEGIN TRANSACTION");
            p = db.prepareStatement("SELECT * FROM Recipes");
            r = p.executeQuery();
            while (r.next()) {
                String recipeName = r.getString("name");
                System.out.println("Reseptikirjaan lisättävän reseptin nimi: " + recipeName);
                recipeNames.add(recipeName);
            }
            st.execute("COMMIT");
            // TODO: Pitäisikö luoda uusi reseptikirja, jottei jää vanhat haut?
//            this.book = new RecipeBook(); // ?????
            for (String name : recipeNames) {
                Recipe pickedRecipe = this.getRecipe(name);
                System.out.println("Listaa varten haettu resepti: " + pickedRecipe);
                this.book.addRecipe(name, pickedRecipe);
            }
            db.close();
//            success = true;
        } catch (SQLException s) {
            this.handleError(s);
//            return success;
        }
//        return success;
        return this.book.getAllRecipes();
    }

    /**
     * Enters the recipe details to recipe database, as well as ingredients with
     * measures and guidelines to recipe object.
     *
     * @param recipe Recipe object
     * @param ingredients stuff and amount as Map object
     * @param instructions guidelines as a list
     * @see recipes.domain.Recipe
     * @return true, if the the recipe details have been stored to database
     * successfully, otherwise false
     */
    public boolean addRecipe(Recipe newRecipe, Map ingredients, List instructions) {

        boolean success = this.getConnected();
        try {
//            String recipeName = recipe.getRecipeName().toLowerCase();
//            int portionAmount = recipe.getPortionAmount();
//            String recipeCategory = recipe.getCategory().toLowerCase();
            String recipeName = newRecipe.getRecipeName().toLowerCase();
            int portionAmount = newRecipe.getPortionAmount();
            String recipeCategory = newRecipe.getCategory().toLowerCase();

            st.execute("BEGIN TRANSACTION");

            p = db.prepareStatement("INSERT INTO Recipes(name,portions,category) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            p.setString(1, recipeName);
            p.setInt(2, portionAmount);
            p.setString(3, recipeCategory);

            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();

            p = db.prepareStatement("SELECT id FROM Recipes WHERE name=?");
            p.setString(1, recipeName);
            r = p.executeQuery();
            int recipeID = r.getInt("id");

            st.execute("COMMIT");

            // TODO: Tarkista, toimiiko ao. lisäykset reseptikirjaan (ei tietokantaan lisäys) - tai onko tarpeen? vasta haettaessa?
            newRecipe.setID(recipeID); 
            newRecipe.setIngredient(ingredients);
            newRecipe.setInstruction(instructions);
            // TODO: Tarvitseeko lisätä reseptikirjaan tässä vaiheessa - vai vasta hakutuloksia listatessa?
            this.addStuff(ingredients, recipeID);
            this.addGuidance(instructions, recipeID);

            db.close();
            success = true;
        } catch (SQLException s) {
            success = this.handleError(s);
            return success;
        }
        return success;
    }

    private void addStuff(Map stuffList, int recipeID) throws SQLException {
        Collection<String> ingredients = stuffList.values();
        for (Object s : stuffList.keySet()) {
            String stuff = (String) s;
            String amount = (String) stuffList.getOrDefault(s, "");
            st.execute("BEGIN TRANSACTION");
            p = db.prepareStatement("INSERT INTO Stuff(id, stuff_name, amount) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            p.setInt(1, recipeID);
            p.setString(2, stuff);
            p.setString(3, amount);
            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();
            st.execute("COMMIT");
        }
    }

    private void addGuidance(List guidance, int recipeID) throws SQLException {
        for (int i = 0; i < guidance.size(); i++) {
            String text = (String) guidance.get(i);
            st.execute("BEGIN TRANSACTION");
            p = db.prepareStatement("INSERT INTO Guidance(id, row, text) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            p.setInt(1, recipeID);
            p.setInt(2, i + 1);
            p.setString(3, text);
            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();
            st.execute("COMMIT");
        }
    }

    /**
     * Gets the recipe from the database, when searching by the name of the
     * recipe.
     *
     * @param recipeName name of the recipe, that is being searched
     * @see recipes.domain.Recipe
     * @return Recipe object, if it can be found from the database, otherwise
     * null
     */
    public Recipe searchRecipebyName(String recipeName) {
        this.recipe = null;
        this.getConnected();
        try {
            this.recipe = this.getRecipe(recipeName);
            db.close();
        } catch (SQLException s) {
            this.recipe = null;
            this.handleError(s);
        }
        return this.recipe;
    }

    /**
     * @param stuff name of an ingredient
     * @see recipes.domain.Recipe
     * @return recipe the searched recipe, if exists, otherwise null
     */
    public Map<String, Recipe> searchRecipebyStuff(String stuff) {
        System.out.println("Searcing a recipe from database by an ingredient..");
        this.recipes = new HashMap<>();
        this.getConnected();
        try {
            this.recipes = this.getByIngredient(stuff);
            db.close();
        } catch (SQLException s) {
            this.recipe = null;
        }
        return this.recipes;
    }

    public boolean deleteRecipe(Recipe removableRecipe) {
        boolean success = false;
        this.getConnected(); // ?????
        int recipeID = removableRecipe.getId();
        System.out.println("Poistettavan reseptin tunnus on: " + recipeID);
        try {
            st.execute("BEGIN TRANSACTION");           
            p = db.prepareStatement("DELETE FROM Stuff WHERE id=?");
            p.setInt(1, recipeID);
            p.executeUpdate();
            p = db.prepareStatement("DELETE FROM Guidance WHERE id=?");
            p.setInt(1, recipeID);
            p.executeUpdate();
            p = db.prepareStatement("DELETE FROM Recipes WHERE id=?");
            p.setInt(1, recipeID);
            p.executeUpdate();
            st.execute("COMMIT");
            this.getAllRecipes(); // ?????
            success = true;
        } catch (SQLException s) {
            success = false;
            this.handleError(s);
        }
        return success;
    }

    private Recipe getRecipe(String recipeName) throws SQLException {
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Recipes WHERE name=?");
        p.setString(1, recipeName);
        r = p.executeQuery();
        if (r.next()) {
            this.setRecipeDetails();
            this.recipe.setID(this.recipeID); // ?????
        } else {
            return null;
        }

        p = db.prepareStatement("SELECT * FROM Stuff WHERE id=?");
        p.setInt(1, this.recipeID);
        r = p.executeQuery();
        while (r.next()) {
            this.setIngredients();
        }

        p = db.prepareStatement("SELECT * FROM Guidance WHERE id=?");
        p.setInt(1, this.recipeID);
        r = p.executeQuery();
        while (r.next()) {
            this.setInstructions();
        }

        st.execute("COMMIT");
        return this.recipe;
    }

    private Map<String, Recipe> getByIngredient(String ingredient) throws SQLException {
        System.out.println("Haetaan reseptiä raaka-aineella " + ingredient);
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Stuff S WHERE S.stuff_name=?");
        p.setString(1, ingredient);
        System.out.println("Aloitetaan kysely..");
        r = p.executeQuery();
        List<Integer> ids = new ArrayList<>();
        while (r.next()) {
            this.recipeID = r.getInt("id");
            System.out.println("Raaka-aineen id: " + this.recipeID);
            ids.add(this.recipeID);
        }
        System.out.println("Reseptien id:t: " + ids);
        this.recipes = this.getSelectedRecipes(ids);
        st.execute("COMMIT");
        return this.recipes;
    }

    private Map<String, Recipe> getSelectedRecipes(List<Integer> ids) {
        for (Integer id : ids) {
            try {
                p = db.prepareStatement("SELECT * FROM Recipes R WHERE R.id=?");
                p.setInt(1, id);
                r = p.executeQuery();
                this.setRecipeDetails();
                this.recipe.setID(id);

                p = db.prepareStatement("SELECT * FROM Stuff WHERE id=?");
                p.setInt(1, id);
                r = p.executeQuery();
                while (r.next()) {
                    this.setIngredients();
                }

                p = db.prepareStatement("SELECT * FROM Guidance WHERE id=?");
                p.setInt(1, id);
                while (r.next()) {
                    this.setInstructions();
                }

                this.recipes.put(this.recipe.getRecipeName(), this.recipe);
            } catch (SQLException s) {
                this.recipes = null;
                this.handleError(s);
            }
        }
//        this.book.setRecipes(this.recipes);
        return this.recipes;
    }

    private void setRecipeDetails() {
        try {
            this.recipeID = r.getInt("id");
            String name = r.getString("name");
            int portions = r.getInt("portions");
            String category = r.getString("category");
            this.recipe = new Recipe(name, portions, category);
        } catch (SQLException s) {
            this.handleError(s);
        }
        System.out.println("Resepti on: " + this.recipe);
    }

    private void setIngredients() {
        try {
            String stuffName = r.getString("stuff_name");
            String stuffAmount = r.getString("amount");
            this.recipe.setIngredient(stuffName, stuffAmount);
        } catch (SQLException s) {
            this.handleError(s);
        }
    }

    private void setInstructions() {
        try {
            String line = r.getString("text");
            this.recipe.setInstruction(line);
        } catch (SQLException s) {
            this.handleError(s);
        }
    }

    private boolean getConnected() {
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
        } catch (SQLException s) {
            System.out.println("Database error when trying to connect with " + this.dbase + ": " + s.getMessage());
            return false;
        }
        return true;
    }

    private boolean handleError(SQLException s) {
        try {
            System.out.println("Database error: " + s.getMessage());
            db.close();
        } catch (SQLException ex) {
            System.out.println("Database error when trying to close " + this.dbase + ": " + ex.getMessage());
        }
        return false;
    }

}
