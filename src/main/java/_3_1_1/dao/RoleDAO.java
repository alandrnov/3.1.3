package _3_1_1.dao;




import _3_1_1.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    Role getRoleByName(String name);

//    Set<Role> getRolesFromText(String text);

    List<Role> getAllRoles();
}
