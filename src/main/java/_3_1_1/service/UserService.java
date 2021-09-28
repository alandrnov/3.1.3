package _3_1_1.service;


import _3_1_1.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User getUserByLogin(String login);

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User getUserById(Long id);


    void processOAuthPostLogin(String username, Map<String, Object> attributes);
}
