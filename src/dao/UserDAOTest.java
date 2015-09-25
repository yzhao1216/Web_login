package dao;

import entity.User;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by EYlee on 7/27/15.
 */
public class UserDAOTest {
    @Test
    public void findByUsernameTest() throws SQLException {
        UserDAO dao = new UserDAO();
        User user =  dao.findByUsername("King");
        System.out.println(user);
    }
}
