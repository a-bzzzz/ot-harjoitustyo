/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.db.RecipesDB;
import recipes.domain.User;
import recipes.domain.Validation;
import recipes.gui.RecipesGUI;

/**
 *
 * @author aebjork
 */
public class ValidationTest {

    String testDatabase;
    Validation check;
    RecipesDB testDB;

//    @BeforeClass
//    public static void setUpClass(RecipesDB database) {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    @Before
    public void setUp() throws SQLException {

        testDatabase = "recipesTestDatabase";
        testDB = new RecipesDB(testDatabase);
        check = new Validation(testDB);
        String testdbPath = testDB.getDBPath();
        if (testdbPath == null) {
            testDB.createTestDB();
            testDB.addTestUser("testPerson", "testPassword", "testFirstname", "testLastName", "test@email.fi");            
        }
        
    }

//    @After
//    public void tearDown() {
//    }
    
    
    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndLoginRight() throws SQLException {
        assertTrue(check.validate("testPerson", "testPassword") != null);
    }

    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndUsernameWrong()throws SQLException {
        //assertFalse(check.validate("testAnother", "testPassword") != null);
        assertThat("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy.", is(equalTo(check.validate("testAnother", "testPassword") )));
        
    }
    
    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndPasswordWrong()throws SQLException {
        assertFalse(check.validate("testPerson", "siili") != null);
    }

    @Test
    public void checksPasswordCorrectlyWhenUserIsNOTCreated() throws SQLException {
        assertFalse(check.validate("testAnother", "anotherPassword") != null);
    }
    
    @Test
    public void checksPasswordCorrectlyWhenUserIsNOTCreatedButPasswordIsRegistered() throws SQLException {
        assertFalse(check.validate("testAnother", "testPassword") != null);
    }
    
    
}
