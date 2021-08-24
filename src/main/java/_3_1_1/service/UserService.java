package _3_1_1.service;




import _3_1_1.models.Role;
import _3_1_1.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    Role getRoleByName(String name);

    public Set<Role> getRolesFromText(String text);

    List<User> getAllUsers();

    User getUser(long id);

    User getUserByLogin(String login);

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    List<Role> getAllRoles();

    User getUserById(Long id);
}
