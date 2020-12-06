package recipes.domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import recipes.db.UsersInterface;

/**
 * This class helps the RecipesGUI graphical user interface with checking the user credentials while login in.
 * Connects with the User objects and user DAO interface, UsersInterface. 
 * Uses Info class, when communicating to RecipesGUI.
 * @see recipes.gui.RecipesGUI
 * @see recipes.db.UsersInterface
 * @see recipes.domain.User
 * @see recipes.domain.Info
 */
public class Validation {

    private UsersInterface udbase;
    private Info<User, String> userInfo;

    /**
     * Constructor, creates a Validation object.
     * @param udbase interface of the user databases
     * @see recipes.db.UsersInterface
     */
    public Validation(UsersInterface udbase) {
        this.udbase = udbase;
    }

    /**
     * Takes care of checking the right username and password of the user, who tries to log into the system.
     * @param username login username, the first part of the user credentials
     * @param password login password, the second part of the user credentials
     * @see recipes.domain.User
     * @see recipes.domain.Info
     * @return Info object containing the User object, or null, and information text regarding the success of login
     * @throws SQLException
     */
    public Info<User, String> validate(String username, String password) throws SQLException {
        Info info = new Info(null, "");
        User loginUser = udbase.searchUser(username);
        String infotext = "";
        // System.out.println("kirjautuva käyttäjä on: " + loginUser); 
        if (loginUser == null) {
            infotext = "KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy.";
        } else if (!loginUser.getPassword().equals(password)) {
            loginUser = null;
            infotext = "VÄÄRÄ SALASANA! Yritä uudelleen tai rekisteröidy.";
        } else {
            infotext = loginUser.getUsername() + " on kirjautumassa..";
        }
        info.setUser(loginUser);
        info.setText(infotext);
        return info;
    }

    /**
     * Checks, that every input field is filled, when a new user registers to the system.
     * @param newUser User object, a new user of the system in registration phase
     * @return true, if all the entered fields are not empty, otherwise false
     */
    public boolean newValidate(User newUser) {
        String fname = newUser.getFirstname();
        String lname = newUser.getLastname();
        String email = newUser.getEmail();
        String uname = newUser.getUsername();
        String pword = newUser.getPassword();

        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || uname.isEmpty() || pword.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
}
