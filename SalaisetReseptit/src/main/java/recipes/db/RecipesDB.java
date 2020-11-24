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

    public void createRecipeDB() {

        try {
            /**
             * Creates tables: Recipes, Stuff, Guidance Recipes: id, name,
             * portions, category, stuff_id, guidance_id
             */
            st.execute("CREATE TABLE Recipes(id INTEGER PRIMARY KEY NOT NULL UNIQUE, "
                    + "name TEXT NOT NULL, "
                    + "portions INTEGER, "
                    + "category TEXT, "
                    + "stuff_id INTEGER REFERENCES Stuff, "
                    + "guidance_id INTEGER REFERENCES Guidance)");
            /**
             * Stuff: id, name, amount
             */
            st.execute("CREATE TABLE Stuff(id INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "name TEXT"
                    + "amount TEXT)");
            /**
             * Guidance: id, row, line
             */
            st.execute("CREATE TABLE Guidance(id INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "row INTEGER NOT NULL UNIQUE, "
                    + "line TEXT)");
            st.execute("COMMIT");
            System.out.println("Database " + this.dbase + ".db has been created.");
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error in createRecipeDB: " + s.getMessage());
        }

    }

    // TODO: Tarkista toteutus! Luo myös Stuff ja Guidance tauluille lisäys- ja muut tarvittavat metodit
    // id, name, portions, category, stuff_id, guidance_id
    public void addRecipe(String recipeName, int portionAmount, String recipeCategory) throws SQLException {

        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();

//            Recipe recipe = this.getRecipe(recipeName);
//            if (recipe == null) {
            st.execute("BEGIN TRANSACTION");

            p = db.prepareStatement("INSERT INTO Recipes(name,portions,category) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            p.setString(2, recipeName);
            p.setInt(3, portionAmount);
            p.setString(4, recipeCategory);

            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();
            st.execute("COMMIT");
//            } else {
//                throw new SQLException("Recipe details have already been added to database.");
//            }
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error in addRecipe: " + s.getMessage());
        }
    }

    // TODO: Muokattava tätä ja seuraavaa metodia, jotta haetaan myös id:llä..
    public Recipe searchRecipebyName(String recipeName) throws SQLException {
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
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Recipes WHERE name=?");
        p.setString(2, recipeName);

        r = p.executeQuery();
        Recipe recipe = new Recipe(/*r.getInt("id"),*/
                r.getString("name"),
                r.getInt("portions"),
                r.getString("category")/*,
                r.getString("stuff_id")
                r.getString("guidance_id")*/);
        return recipe;
    }

}
