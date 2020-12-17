package recipes.test;

import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.Info;
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
    User testUser;

    @Before
    public void setUp() throws SQLException, Exception {

        String tub = "TestUsersDatabase";
        testUBase = new FakeUsersDB(tub);
        check = new Validation(testUBase);
        testUser = new User("ta", "tb", "test@email.fi", "testPerson", "testPword");

        User testDBExisting = this.testUBase.searchUser("testPerson");
        if (testDBExisting == null) {
            testUBase.createUsersDB();
            testUBase.addUser(testUser);
        }

    }

    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndLoginRight() throws SQLException {
        assertTrue(check.validate("testPerson", "testPword") != null);
        assertThat("test@email.fi", is(equalTo(check.validate("testPerson", "testPword").getUser().getEmail())));
    }

    @Test
    public void checksUserCorrectlyWhenUsernameWrong() throws SQLException {
        assertTrue(check.validate("testAnother", "testPword").getUser() == null);
        assertTrue(check.validate("testAnother", "testPword").getText().equals("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy."));
    }

    @Test
    public void checksUserCorrectlyWhenUserIsCreatedAndPasswordWrong() throws SQLException {
        assertTrue(check.validate("testPerson", "tiili").getUser() == null);
        assertTrue(check.validate("testPerson", "tiili").getText().equals("VÄÄRÄ SALASANA! Yritä uudelleen tai rekisteröidy."));
    }

    @Test
    public void checksPasswordCorrectlyWhenUserIsNOTCreated() throws SQLException {
        assertFalse(check.validate("testAnother", "anotherPassword").getUser() != null);
        assertTrue(check.validate("testAnother", "anotherPassword").getText().equals("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy."));
    }

    @Test
    public void checksPasswordCorrectlyWhenUserIsNOTCreatedButPasswordIsRegistered() throws SQLException {
        assertFalse(check.validate("testAnother", "testPassword").getUser() != null);
        assertTrue(check.validate("testAnother", "testPassword").getText().equals("KÄYTTÄJÄÄ EI TUNNISTETTU! Yritä uudelleen tai rekisteröidy."));
    }

    @Test
    public void checksNewUserRightWhenNoBlanks() throws SQLException {
        assertTrue(check.newValidate(testUser));
    }

    @Test
    public void checksNewUserRightWhenAllBlanks() throws SQLException {
        assertFalse(check.newValidate(new User("", "", "", "", "")));
    }
    
    @Test
    public void checksNewUserRightWhenFirstnameIsBlank() throws SQLException {
        assertFalse(check.newValidate(new User("", "tb", "test@email.fi", "testPerson", "testPword")));
    }
    
    @Test
    public void checksNewUserRightWhenLastnameIsBlank() throws SQLException {
        assertFalse(check.newValidate(new User("ta", "", "test@email.fi", "testPerson", "testPword")));
    }

    @Test
    public void checksNewUserRightWhenEmailIsBlank() throws SQLException {
        assertFalse(check.newValidate(new User("ta", "tb", "", "testPerson", "testPword")));
    }
    
    @Test
    public void checksNewUserRightWhenUsernameIsBlank() throws SQLException {
        assertFalse(check.newValidate(new User("ta", "tb", "test@email.fi", "", "testPword")));
    }
    
    @Test
    public void checksNewUserRightWhenPasswordIsBlank() throws SQLException {
        assertFalse(check.newValidate(new User("ta", "tb", "test@email.fi", "testPerson", "")));
    }

}
