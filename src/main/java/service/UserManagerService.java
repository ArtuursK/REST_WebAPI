package service;

import entity.User;
import enums.EGeneralErrorCode;
import enums.EUserErrorDetail;
import enums.UserStatus;
import exceptions.BusinessException;
import persistence.UserPs;
import utility.PassUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagerService {

    public UserManagerService() {

    }

    public List<User> getAllUsers() throws SQLException{
        return UserPs.getUsers();
    }

    public List<User> getUser(String username) throws SQLException, BusinessException {
        List<User> users = new ArrayList<>();
        User user = UserPs.getUser(username);
        if(user != null){
            users.add(user);
        }else{
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_NOT_FOUND.getMessage());
        }
        return users;
    }

    public User addUser(String username, String passkey) throws SQLException, BusinessException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User userToCreate = new User();

        userToCreate.setUsername(username);
        userToCreate.setTimeCreated(timestamp.toString());
        userToCreate.setStatus(UserStatus.CREATED.getMessage());

        String hashedPasskey = null;
        try {
            hashedPasskey = PassUtil.getSaltedHash(passkey);
        } catch (Exception e) {
            throw BusinessException.create(EGeneralErrorCode.APPLICATION_ERROR, EUserErrorDetail.USER_PASSWORD_COULD_NOT_BE_HASHED.getMessage());
        }

        UserPs.createNewUser(userToCreate, hashedPasskey);
        return UserPs.getUser(userToCreate.getUsername());
    }

    public User updateUser(String username, String passkey, String status) throws Exception {
        User user = UserPs.getUser(username);
        if(user == null){
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_NOT_FOUND.getMessage());
        }
        if(checkPasskey(username, passkey)){
            UserPs.updateUser(user, status);
        }else{
            throw BusinessException.create(EGeneralErrorCode.APPLICATION_ERROR, EUserErrorDetail.USER_PASSWORD_INCORRECT.getMessage());
        }
        return null;
    }

    public void deleteUser(String username) throws SQLException, BusinessException {
        if(username == null){
            throw BusinessException.create(EGeneralErrorCode.BAD_REQUEST, EUserErrorDetail.USERS_USERNAME_INVALID.getMessage());
        }
        UserPs.deleteUser(username);
    }

    public boolean usernameExists(String username) throws SQLException {
        List<User> users = new ArrayList<>(UserPs.getUsers());
        for(User user : users){
            if(user.getUsername().equals(username)){
                //username already exists
                return true;
            }
        }
        return false;
    }

    //checks if user is authorized
    private boolean checkPasskey(String username, String passkey) throws Exception {
        return PassUtil.check(passkey, UserPs.getUserPasskey(username));
    }

}