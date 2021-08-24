package _3_1_1.service;

import _3_1_1.dao.UserDao;
import _3_1_1.models.Role;
import _3_1_1.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getRolesFromText(String text) {
        return userDao.getRolesFromText(text);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }
    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
