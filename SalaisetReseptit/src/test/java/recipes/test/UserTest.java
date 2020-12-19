package recipes.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.domain.User;

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

}
