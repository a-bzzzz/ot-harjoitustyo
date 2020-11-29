/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import recipes.domain.Recipe;
// import recipes.domain.User;

/**
 *
 * @author aebjork
 */
public class RecipesDB {

    private String dbase;
    private String path;

    private Connection db;
    private Statement st;
    private PreparedStatement p;
    private ResultSet r;

    public RecipesDB(String dbase) {
        this.dbase = dbase;
        this.path = "jdbc:sqlite:" + this.dbase + ".db";
    }

    public String getDBPath() {
        return this.path;
    }

    public boolean createRecipeDB() {

        try {
            /**
             * Creates tables: Recipes, Stuff, Guidance
             */
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            st.execute("BEGIN TRANSACTION");
            st.execute("PRAGMA foreign_keys = ON");
            /**
             * Recipes: id, name, portions, category
             */
            st.execute("CREATE TABLE Recipes(id INTEGER PRIMARY KEY NOT NULL UNIQUE, "
                    + "name TEXT NOT NULL, "
                    + "portions INTEGER, "
                    + "category TEXT)");
            st.execute("CREATE INDEX idx_recipe_id ON Recipes (id)");
            /**
             * Stuff: id, stuff_name, amount
             */
            st.execute("CREATE TABLE Stuff(id INTEGER NOT NULL,"
                    + "stuff_name TEXT, "
                    + "amount TEXT)");
            /**
             * Guidance: id, row, text
             */
            st.execute("CREATE TABLE Guidance(id INTEGER NOT NULL,"
                    + "row INTEGER NOT NULL, "
                    + "text TEXT)");
            st.execute("COMMIT");
            db.close();
            System.out.println("Database " + this.dbase + ".db has been created.");
            return true;
        } catch (SQLException s) {
            System.out.println("Database error in createRecipeDB: " + s.getMessage());
            return false;
        }

    }

    // TODO: Tarkista toteutus! Luo myös Stuff ja Guidance tauluille lisäys- ja muut tarvittavat metodit
    // id, name, portions, category, stuff_id, guidance_id
    //public void addRecipe(String recipeName, int portionAmount, String recipeCategory) throws SQLException {
    public boolean addRecipe(Recipe recipe, Map ingredients, List instructions) {

        boolean success = false;

        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();

            // TODO: Lisää puuttuvat kentät -> reseptiin lisätään ensin perustiedot, sitten aineet ja lopuksi ohjeet
            String recipeName = recipe.getRecipeName();
            int portionAmount = recipe.getPortionAmount();
            String recipeCategory = recipe.getCategory();

//            Recipe recipe = this.getRecipe(recipeName);
//            if (recipe == null) {
            st.execute("BEGIN TRANSACTION");

            p = db.prepareStatement("INSERT INTO Recipes(name,portions,category) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            p.setString(1, recipeName);
            p.setInt(2, portionAmount);
            p.setString(3, recipeCategory);

            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();
//            st.execute("COMMIT");
//            } else {
//                throw new SQLException("Recipe details have already been added to database.");
//            }
            // Getting recipe id for adding ingredients and instructions
            // st.execute("BEGIN TRANSACTION");
            p = db.prepareStatement("SELECT id FROM Recipes WHERE name=?");
            p.setString(1, recipeName);
            r = p.executeQuery();
            int recipeID = r.getInt("id");

//            st.execute("ALTER TABLE Recipes ADD COLUMN stuff_id INTEGER REFERENCES Stuff");
//            st.execute("ALTER TABLE Recipes ADD COLUMN guide_id INTEGER REFERENCES Guidance");
//            p = db.prepareStatement("INSERT INTO Recipes(stuff_id,guide_id) VALUES (?,?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            p.setInt(1, recipeID);
//            p.setInt(2, recipeID);

            st.execute("COMMIT");
            System.out.println("Reseptin id: " + recipeID); // POISTETTAVA

            this.addStuff(ingredients, recipeID);
            this.addGuidance(instructions, recipeID);

//            st.execute("BEGIN TRANSACTION");
//            st.execute("ALTER TABLE Recipes ADD COLUMN stuff_id INTEGER");
//            st.execute("ALTER TABLE Recipes ADD COLUMN guide_id INTEGER");
//            p.setInt(4, recipeID);
//            p.setInt(5, recipeID);
//            st.execute("COMMIT");
            db.close();
            success = true;
        } catch (SQLException s) {
            System.out.println("Database error in addRecipe: " + s.getMessage());
            success = false;
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

    // TODO: Muokattava tätä ja seuraavaa metodia, jotta haetaan myös id:llä..
    public Recipe searchRecipebyName(String recipeName) {
        // System.out.println("Searcing a recipe from database..");
        Recipe recipe = null;
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            recipe = this.getRecipe(recipeName);
            db.close();
        } catch (SQLException s) {
            recipe = null;
        }
        return recipe;
    }

    // TODO: Haku id:llä! Ja db:n generoimat id:t???
    private Recipe getRecipe(String recipeName) throws SQLException {
        // System.out.println("Getting recipe details..");
        System.out.println("Haetaan reseptiä nimeltään " + recipeName);
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Recipes WHERE name=?");
        p.setString(1, recipeName);

        r = p.executeQuery();
        int recipeID = r.getInt("id");
        Recipe recipe = new Recipe(r.getString("name"),
                r.getInt("portions"),
                r.getString("category"));
        st.execute("COMMIT");
        System.out.println("getRecipe-metodissa: " + recipe);
        return recipe;
    }

    // TODO: Muokattava tätä ja seuraavaa metodia, jotta haetaan myös id:llä..
    public Recipe searchRecipebyStuff(String stuff) {
        // System.out.println("Searcing a recipe from database..");
        Recipe recipe = null;
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            // TODO: raaka-aineella haku
            //recipe = this.getRecipe(recipeName);
            db.close();
        } catch (SQLException s) {
            recipe = null;
        }
        return recipe;
    }

}
