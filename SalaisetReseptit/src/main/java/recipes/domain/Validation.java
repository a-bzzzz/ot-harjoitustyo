/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

import java.sql.SQLException;
import recipes.db.UsersInterface;

/**
 *
 * @author aebjork
 */
public class Validation {

    private UsersInterface udbase;

    public Validation(UsersInterface udbase) {
        this.udbase = udbase;
    }

    public User validate(String username, String password) throws SQLException {
        User loginUser = udbase.searchUser(username);
        // System.out.println("kirjautuva käyttäjä on: " + loginUser); 
        if (loginUser == null) {
            if (this.udbase.getDBPath().equals("jdbc:sqlite:UsersDatabase.db")) {
                throw new SQLException("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy.");
            } else {
                System.out.println("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy.");
            }
        }
        if (loginUser == null || !loginUser.getPassword().equals(password)) {
            loginUser = null;
            if (this.udbase.getDBPath().equals("jdbc:sqlite:UsersDatabase.db")) {
                throw new SQLException("VÄÄRÄ SALASANA! Yritä uudelleen tai rekisteröidy.");
            } else {
                System.out.println("VÄÄRÄ SALASANA! Yritä uudelleen tai rekisteröidy.");
            }
        }
        return loginUser;
    }

    public boolean newValidate(User newUser) {
        String fname = newUser.getFirstname();
        String lname = newUser.getLastname();
        String email = newUser.getEmail();
        String uname = newUser.getUsername();
        String pword = newUser.getPassword();

        if (fname.isBlank() || lname.isBlank() || email.isBlank() || uname.isBlank() || pword.isBlank()) {
            return false;
        } else {
            return true;
        }
    }
}
