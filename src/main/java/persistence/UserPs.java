package persistence;

import entity.User;
import utility.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserPs {

    public UserPs() {
    }

    public static void createNewUser(User user, String passkey) throws SQLException {
        try{
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("insert into user (username, passphrase, created_at, status) values (?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, passkey);
            preparedStatement.setString(3, user.getTimeCreated());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println("Error saving new user" + ex);
        } finally {
            DBUtil.getConnection().close();
        }
    }

    public static List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from user where username=0");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setTimeCreated(rs.getString("created_at"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.getConnection().close();
        }
        return users;
    }

    public static void updateUser(User user, String status) throws SQLException {
        try{
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("update user set status = ? where username = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println("Error updating user" + ex);
        } finally {
            DBUtil.getConnection().close();
        }
    }

    public static User getUser(String username) throws SQLException {
        User user = new User();
        try{
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("select * from user where username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setTimeCreated(rs.getString("created_at"));
                user.setUsername(rs.getString("username"));
                user.setStatus(rs.getString("status"));
            }
            return user;
        }catch (SQLException ex){
            System.out.println("Error fetching user " + ex);
            return null;
        } finally {
            DBUtil.getConnection().close();
        }
    }

    public static void deleteUser(String username) throws SQLException {
        try{
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("delete from user where username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println("Error deleting user  " + ex);
        } finally {
            DBUtil.getConnection().close();
        }
    }

    public static String getUserPasskey(String username) throws SQLException {
        String storedPasskey = null;
        PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("select * from user where username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            storedPasskey = rs.getString("passphrase");
        }
        return storedPasskey;
    }

}
