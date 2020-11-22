/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

import java.util.Objects;

/**
 *
 * @author aebjork
 */
public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public User(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "\n" + firstname + " " + lastname
                + "\n" + email + "\n" + username
                + "\n" + password;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
//        if (!Objects.equals(this.firstname, other.firstname)) {
//            return false;
//        }
//        if (!Objects.equals(this.lastname, other.lastname)) {
//            return false;
//        }
//        if (!Objects.equals(this.email, other.email)) {
//            return false;
//        }
//        if (!Objects.equals(this.username, other.username)) {
//            return false;
//        }
//        if (!Objects.equals(this.password, other.password)) {
//            return false;
//        }
//        return true;
        return this.username.equals(other.username);
    }
    */

}
