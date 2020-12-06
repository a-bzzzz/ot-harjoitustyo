package recipes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import recipes.domain.User;

/**
 * This class/data access object offers an interface to the actual user database. GUI calls
 * methods of this class, and this class is in connection to User class.
 * @see recipes.domain.User
 */
public class UsersDB implements UsersInterface {

    private String ubase;
    private String path;

    private Connection db;
    private Statement st;
    private PreparedStatement p;
    private ResultSet r;

    /**
     * This class/data access object offers an interface to the actual recipe database. GUI calls
     * methods of this class, and this class is in connection to Recipe class.
     * @param ubase name of the user database
     * @see UsersInterface, the interface for user data access objects
     */
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
    public boolean addUser(User newUser) {
        String uname = newUser.getUsername();
        String pword = newUser.getPassword();
        String fname = newUser.getFirstname();
        String lname = newUser.getLastname();
        String email = newUser.getEmail();
        boolean success = false;
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
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
            success = true;
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error in addUser: " + s.getMessage());
            try {
                db.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex); // ?????
            }
            success = false;            
        }
        return success;
    }

    @Override
    public User searchUser(String userName) {
        User user = null;
        try {
            db = DriverManager.getConnection(this.path);
            st = db.createStatement();
            user = this.getUser(userName);
            db.close();
        } catch (SQLException s) {
            System.out.println("Database error in searchUser: " + s.getMessage());
            user = null;
            try {
                db.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex); // ????
            }
        }       
        return user;
    }

    private User getUser(String userName) throws SQLException {
        st.execute("BEGIN TRANSACTION");
        p = db.prepareStatement("SELECT * FROM Users WHERE username=?");
        p.setString(1, userName);

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
