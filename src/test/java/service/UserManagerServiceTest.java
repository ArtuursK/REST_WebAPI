package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;

import entity.User;
import exceptions.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

/**
 * Test class for {@link UserManagerService}
 * @author Arturs Kempelis
 */

public class UserManagerServiceTest {

    @InjectMocks
    private final UserManagerService userManagerService = new UserManagerService();

    @Before
    public void setUp() {
    }


    @Test
    public void getUsers() {

    }

    @Test
    public void getUserWithUsername() throws SQLException, BusinessException {
        String username = "Test_User_Nr_1";
        List<User> users = userManagerService.getUser(username);
        assertNotNull("No users found for provided username", users);
        assertEquals("Usernames do not match", username, users.get(0).getUsername());
    }

    @Test
    public void deleteUser() {

    }

    @Test
    public void updateUser() {

    }

    @Test
    public void addUser() {

    }




}
