package recipes.domain;

import java.util.Objects;

/**
 * The class defining the users of this recipe book. Interacts with UsersDB
 * class.
 *
 * @see recipes.db.UsersDB
 */
public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    /**
     * Constructor, creates a user details of an application user.
     * @param firstname user's first name
     * @param lastname user's last name
     * @param email user's email address
     * @param username the 1st part of user credentials, username, unique user
     * identifier
     * @param password the 2nd part of user credentials, password, secret word
     * for login
     */
    public User(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter, returns the first name of the user.
     * @return user's first name as String
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Getter, returns the last name of the user.
     * @return user's last name as String
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Getter, returns the email address of the user.
     * @return user's email address as String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter, returns the username of the user.
     * @return username as String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter, returns the password of the user.
     * @return user's password as String
     */
    public String getPassword() {
        return this.password;
    }

}
