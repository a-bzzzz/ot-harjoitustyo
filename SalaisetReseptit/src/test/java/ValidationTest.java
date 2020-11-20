/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.User;
import recipes.domain.Validation;
import test.db.FakeUsersDB;

/**
 *
 * @author aebjork
 */
public class ValidationTest {

    FakeUsersDB testUBase;
    Validation check;

    @Before
    public void setUp() throws SQLException, Exception {

        String tub = "TestUsersDatabase";
        testUBase = new FakeUsersDB(tub);
        check = new Validation(testUBase);

        User testDBExisting = this.testUBase.searchUser("testPerson");
        if (testDBExisting == null) {
            this.testUBase.createUsersDB();
            this.testUBase.addUser("testPerson", "testPword", "ta", "tb", "test@email.fi");
        }

    }

    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndLoginRight() throws SQLException {
        assertTrue(check.validate("testPerson", "testPword") != null);
        assertThat("test@email.fi", is(equalTo(check.validate("testPerson", "testPword").getEmail())));
    }

    @Test
    public void checksUserCorrectlyWhenUsernameWrong() throws SQLException {
        assertTrue(check.validate("testAnother", "testPword") == null);
    }   

    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndPasswordWrong() throws SQLException {
        assertTrue(check.validate("testPerson", "tiili") == null);
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
