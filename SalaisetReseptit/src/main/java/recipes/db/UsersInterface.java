package recipes.db;

import java.sql.SQLException;
import recipes.domain.User;

/**
 * This interface introduces the methods for data access objects, which are using the actual user database.
 * @see recipes.domain.User 
 */
public interface UsersInterface {
    
    /**
     * Returns the bath of the user database.
     * @return path database path as String
     */
    String getDBPath();
    
    /**
     * Creates the user database (if not already created), either the actual or the "fake" database for testing.
     * @return true, if creating new user database is successful, otherwise false
     */
    boolean createUsersDB();
    
    /**
     * Adds a user object to user database.
     * @param user user object
     * @throws SQLException 
     * @return true, if adding the user object to user database is successful, otherwise false
     */
    boolean addUser(User user) throws SQLException;
    
    /**
     * Searches a user object from the user database by the username.
     * @param username user's username
     * @throws SQLException 
     * @see recipes.domain.User
     * @return user user object, if a user with the given username has been created, otherwise null
     */
    User searchUser(String username) throws SQLException;
    
}
