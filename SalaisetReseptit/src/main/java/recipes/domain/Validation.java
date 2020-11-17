/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

import java.sql.SQLException;
import recipes.db.RecipesDB;

/**
 *
 * @author aebjork
 */
public class Validation {
    
    private RecipesDB dbase;

    public Validation(RecipesDB dbase) {
        this.dbase = dbase;
    }
    
    public User validate(String username, String password) throws SQLException {        
        User loginUser = dbase.searchUser(username);
        if (loginUser == null) {
            throw new SQLException("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy.");
        } 
        if (!loginUser.getPassword().equals(password)) {
            loginUser = null;
            throw new SQLException("VÄÄRÄ SALASANA! Yritä uudelleen tai rekisteröidy."); 
        }        
        return loginUser;
    }
       
}
