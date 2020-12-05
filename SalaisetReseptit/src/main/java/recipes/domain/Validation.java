package recipes.domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import recipes.db.UsersInterface;

/**
 *
 * @author aebjork
 */
public class Validation {

    private UsersInterface udbase;
    private Info<User, String> userInfo;

    public Validation(UsersInterface udbase) {
        this.udbase = udbase;
    }

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
