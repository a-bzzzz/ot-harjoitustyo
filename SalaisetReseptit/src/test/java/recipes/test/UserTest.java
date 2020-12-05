package recipes.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.User;

/**
 *
 * @author aebjork
 */
public class UserTest {

    User testUser;

    @Before
    public void setUp() {
        testUser = new User("ta", "tb", "test@email.fi", "testPerson", "testPword");
    }

    @Test
    public void toStringWorksWhenRightUser() {
        assertThat("\nta tb\ntest@email.fi\ntestPerson\ntestPword", is(equalTo(testUser.toString())));
    }

    @Test
    public void getFirstnameWorks() {
        assertThat("ta", is(equalTo(testUser.getFirstname())));
    }

    @Test
    public void getLastnameWorks() {
        assertThat("tb", is(equalTo(testUser.getLastname())));
    }

    @Test
    public void getEmailWorks() {
        assertThat("test@email.fi", is(equalTo(testUser.getEmail())));
    }

    @Test
    public void getUsernameWorks() {
        assertThat("testPerson", is(equalTo(testUser.getUsername())));
    }

/*    
    @Test
    public void equalsWorksWhenSame() {
        assertTrue(this.testUser.equals(this.testUser));
    }

    @Test
    public void equalsWorksWhenSameFields() {
        User same = new User("ta", "tb", "test@email.fi", "testPerson", "testPword");
        assertTrue(this.testUser.equals(same));
    }
    
    @Test
    public void equalsWorksWhenOnlySameUsername() {
        User same = new User("other_ta", "other_tb", "other_test@email.fi", "testPerson", "other_testPword");
        assertTrue(this.testUser.equals(same));
    }
    
    @Test
    public void equalsWorksWhenOnlyUsernameDiffers() {
        User same = new User("ta", "tb", "test@email.fi", "other_testPerson", "testPword");
        assertFalse(this.testUser.equals(same));
    }
    
    @Test
    public void equalsWorksWhenAllDiffers() {
        User same = new User("other_ta", "other_tb", "other_test@email.fi", "other_testPerson", "other_testPword");
        assertFalse(this.testUser.equals(same));
    }
*/

}
