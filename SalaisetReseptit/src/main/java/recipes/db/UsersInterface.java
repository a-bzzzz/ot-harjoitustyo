/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.db;

import java.sql.SQLException;
import recipes.domain.User;

/**
 *
 * @author aebjork
 */
public interface UsersInterface {
    
    String getDBPath();
    
    boolean createUsersDB();
    
    boolean addUser(User user) throws SQLException;
    
    User searchUser(String username) throws SQLException;
    
}
