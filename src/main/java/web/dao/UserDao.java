package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List  getAllUsers();
    void saveUser(User user);
    User findUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    User findUserByName(String name);

}
