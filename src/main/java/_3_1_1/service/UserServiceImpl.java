package _3_1_1.service;

import _3_1_1.dao.RoleDAO;
import _3_1_1.dao.UserDao;
import _3_1_1.models.AuthenticationProvider;
import _3_1_1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;

    private final String DEFAULT_PASSWORD = "1234";

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthProvider(AuthenticationProvider.LOCAL);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        User currentUser = userDao.getUserById(updatedUser.getId());
        String newPassword = updatedUser.getPassword();
        String oldPassword = currentUser.getPassword();

        if (newPassword.equals("") || newPassword.equals(oldPassword)) {
            updatedUser.setPassword(oldPassword);
        } else {
            updatedUser.setPassword(passwordEncoder.encode(newPassword));
        }
        userDao.updateUser(updatedUser);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void processOAuthPostLogin(String username, Map<String, Object> attributes) {
        System.out.println(attributes);
        User newUser = new User();
        newUser.setLogin(username);
        if (username.contains("@gmail.com")) {
            newUser.setAuthProvider(AuthenticationProvider.GOOGLE);
            newUser.setFirstName((String) attributes.get("given_name"));
            newUser.setSecondName((String) attributes.get("family_name"));
        }

        newUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        newUser.setRoles(Collections.singleton(roleDAO.getRoleByName("ROLE_USER")));


        userDao.addUser(newUser);
    }
}
