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
public class UsersDB implements UsersInterface {

    private String ubase;
    private String path;

    private Connection db;
    private Statement st;
    private PreparedStatement p;
    private ResultSet r;

    public UsersDB(String ubase) {
        this.ubase = ubase;
        this.path = "jdbc:sqlite:" + this.ubase + ".db";
    }

    @Override
    public String getDBPath() {
        return this.path;
    }

    @Override
    public boolean createUsersDB() {

        try {
            // Creates table Users
            //this.path = "jdbc:sqlite:" + this.dbase + ".db";
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
            st.execute("COMMIT");
            // System.out.println("Database " + this.ubase + ".db has been created.");
            db.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Database error in createUsersDB: " + s.getMessage());
            return false;
        }

    }

    @Override
    // public void addUser(String uname, String pword, String fname, String lname, String email) throws SQLException {
    public boolean addUser(User newUser) {

        String uname = newUser.getUsername();
        String pword = newUser.getPassword();
        String fname = newUser.getFirstname();
        String lname = newUser.getLastname();
        String email = newUser.getEmail();

        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();

//            User user = this.getUser(uname);
//            if (user.getUsername() != uname) {
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
            // System.out.println("Lisätty käyttäjä: \n" + newUser.toString());
//            } else {
//                // throw new SQLException("User details have already been added to database.");
//                throw new SQLException("Username is already in use.");
//            }
            db.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Database error in addUser: " + s.getMessage());
            return false;
        }
    }

    @Override
    public User searchUser(String user_name) {
        User user = null;
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();            
            user = this.getUser(user_name);   
            db.close();
        } catch (SQLException s) {
            user = null;
        }
        return user;
    }

    private User getUser(String user_name) throws SQLException {
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Users WHERE username=?");
        p.setString(1, user_name);

        r = p.executeQuery();
        User user = new User(r.getString("firstname"),
                r.getString("lastname"),
                r.getString("email"),
                r.getString("username"),
                r.getString("password"));
        // System.out.println("Etsittävä käyttäjä on: " + user.toString()); 
        return user;
    }

}
