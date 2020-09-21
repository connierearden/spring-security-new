package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements  UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findUserByName(s);
    }

    @Transactional
    @Override
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        if (id!=null) {
            return userDao.findUserById(id);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (!user.getName().isEmpty() && !user.getPassword().isEmpty()){
            userDao.updateUser(user);
        }
    }
}