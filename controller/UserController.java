package controller;

import dao.interfaces.UserDAO;
import dao.impl.UserDAOImpl;
import models.User;
import models.Log;
import util.PasswordUtil;
import java.sql.Timestamp;

import java.util.List;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl();
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public String getUsernameById(int id)
    {
        User u=userDAO.getUserById(id);
        return u.getUsername();
    }

    public void updateUser(User user, int id) {
        userDAO.updateUser(user, id);
    }

    public void updateColumnUser(int id,String col,Object val)
    {
        userDAO.updateColumnUser(id, col, val);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public boolean registerUser(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) != null) {
            return false; // Username already exists
        }
        return userDAO.registerUser(user);
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            System.out.println("Username not found in DB.");
            return null;
        }

        if (PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            Log a=new Log(user.getUserId(),"LOGGED IN",new Timestamp(System.currentTimeMillis()));
            new LogController().addLog(a);
            return user;
        }
        
        return null;
    }
    public void logout(User user)
    {
        Log a=new Log(user.getUserId(),"LOGGED OUT",new Timestamp(System.currentTimeMillis()));
        new LogController().addLog(a);
    }

    

   
}
