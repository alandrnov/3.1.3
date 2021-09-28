package _3_1_1.dao;


import _3_1_1.models.User;

import java.util.List;

public interface UserDao {


    List<User> getAllUsers();

    User getUser(long id);

    User getUserByLogin(String login);

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);
}
