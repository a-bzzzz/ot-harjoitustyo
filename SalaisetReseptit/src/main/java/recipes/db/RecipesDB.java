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
import recipes.domain.User;

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
        //this.path = "jdbc:sqlite:" + this.dbase + ".db";
    }
    
    public String getDBPath() {
        return this.path;
    }

    public void createRecipeDB() {

        try {
            // Creates tables: Users, Recipes, Stuff, Guidance
            this.path = "jdbc:sqlite:" + this.dbase + ".db";
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            st.execute("BEGIN TRANSACTION");
            st.execute("PRAGMA foreign_keys = ON");
            // Users: username, password, firstname, lastname, email
            st.execute("CREATE TABLE Users(username TEXT PRIMARY KEY NOT NULL UNIQUE, "
                    + "password TEXT NOT NULL, "
                    + "firstname TEXT NOT NULL, "
                    + "lastname TEXT NOT NULL, "
                    + "email TEXT NOT NULL)");
            // Recipes: id, name, portions, category, stuff_id, guidance_id
            st.execute("CREATE TABLE Recipes(id INTEGER PRIMARY KEY NOT NULL UNIQUE, "
                    + "name TEXT NOT NULL, "
                    + "portions INTEGER, "
                    + "category TEXT, "
                    + "stuff_id INTEGER REFERENCES Stuff, "
                    + "guidance_id INTEGER REFERENCES Guidance)");
            // Stuff: id, name, amount
            st.execute("CREATE TABLE Stuff(id INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "name TEXT"
                    + "amount TEXT)");
            // Guidance: id, row, line
            st.execute("CREATE TABLE Guidance(id INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "row INTEGER NOT NULL UNIQUE, "
                    + "line TEXT)");
            st.execute("COMMIT");
            // System.out.println("Database " + this.dbase + ".db has been created."); 
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error: " + s.getMessage());
        }

    }

    public void addUser(String uname, String pword, String fname, String lname, String email) throws SQLException {

        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();

//            User user = this.getUser(uname);
//            if (user == null) {
            st.execute("BEGIN TRANSACTION");

            p = db.prepareStatement("INSERT INTO Users(username,password,firstname,lastname,email) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            p.setString(1, uname);
            p.setString(2, pword);
            p.setString(3, fname);
            p.setString(4, lname);
            p.setString(5, email);

            p.executeUpdate();
            r = p.getGeneratedKeys();
            r.next();
            st.execute("COMMIT");
//            } else {
//                throw new SQLException("User details have already been added to database.");
//            }
            db.close();
        } catch (SQLException s) {
            System.out.println("Tietokantavirhe: " + s.getMessage());
        }
    }

    public User searchUser(String user_name) throws SQLException {
        // System.out.println("Searcing user from database..");
        User user = null;
        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();
            user = this.getUser(user_name);
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error: " + s.getMessage());
        }
        return user;
    }

    private User getUser(String user_name) throws SQLException {
        // System.out.println("Getting user details..");
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Users WHERE username=?");
        p.setString(1, user_name);

        r = p.executeQuery();
        User user = new User(r.getString("firstname"),
                r.getString("lastname"),
                r.getString("email"),
                r.getString("username"),
                r.getString("password"));
        return user;
    }

}
