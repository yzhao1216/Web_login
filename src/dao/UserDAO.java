package dao;

import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by EYlee on 7/27/15.
 */
public class UserDAO {
    public User findByUsername(String username) throws SQLException {
        User user = null;
        Connection con = null;

        try {
            con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE username=?");
            ps.setString(1,username);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                user = new User();
                user.setId(res.getInt("id"));
                user.setUsername(username);
                user.setName(res.getString("name"));
                user.setPwd(res.getString("pwd"));
                user.setGender(res.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally{
            con.close();

        }


        return user;
    }
}
