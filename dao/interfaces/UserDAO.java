package dao.interfaces;

import models.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user,int id);
    void deleteUser(int id);
    boolean registerUser(User user);
    User getUserByUsername(String username);
    void updateColumnUser(int id,String column,Object val);
   
}
