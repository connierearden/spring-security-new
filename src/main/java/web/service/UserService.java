package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List  getAllUsers();
    void saveUser(User user);
    User findUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    User findUserByName(String name);
}
