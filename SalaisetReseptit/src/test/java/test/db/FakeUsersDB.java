/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import recipes.db.UsersInterface;
import recipes.domain.User;

/**
 *
 * @author aebjork
 */
public class FakeUsersDB implements UsersInterface {

    private String testUbase;
    private String path;

    private Connection db;
    private Statement st;
    private PreparedStatement p;
    private ResultSet r;

    public FakeUsersDB(String ubase) {
        this.testUbase = ubase;
        this.path = "jdbc:sqlite:" + this.testUbase + ".db";
    }

    @Override
    public String getDBPath() {
        return this.path;
    }

    @Override
    public boolean createUsersDB() {

        try {
            // Creates table TestUsers
            //this.path = "jdbc:sqlite:" + this.dbase + ".db";
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            st.execute("BEGIN TRANSACTION");
            st.execute("PRAGMA foreign_keys = ON");
            // Users: username, password, firstname, lastname, email
            st.execute("CREATE TABLE TestUsers(username TEXT PRIMARY KEY NOT NULL UNIQUE, "
                    + "password TEXT NOT NULL, "
                    + "firstname TEXT NOT NULL, "
                    + "lastname TEXT NOT NULL, "
                    + "email TEXT NOT NULL)");
            st.execute("COMMIT");
            System.out.println("Database " + this.testUbase + ".db has been created.");
            db.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Database error in createUsersDB for testing: " + s.getMessage());
            return false;
        }

    }

    @Override
    // public void addUser(String uname, String pword, String fname, String lname, String email) throws SQLException {
    public boolean addUser(User testUser) throws SQLException {
        
        String uname = testUser.getUsername();
        String pword = testUser.getPassword();
        String fname = testUser.getFirstname();
        String lname = testUser.getLastname();
        String email = testUser.getEmail();

        try {
            db = DriverManager.getConnection(path);
            st = db.createStatement();

//            User user = this.getUser(uname);
//            if (user == null) {
            st.execute("BEGIN TRANSACTION");

            p = db.prepareStatement("INSERT INTO TestUsers(username,password,firstname,lastname,email) VALUES (?,?,?,?,?)",
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
            return true;
        } catch (SQLException s) {
            System.out.println("Database error in addUser for testing: " + s.getMessage());
            return false;
        }

    }

    @Override
    public User searchUser(String user_name) throws SQLException {
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
        p = db.prepareStatement("SELECT * FROM TestUsers WHERE username=?");
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
