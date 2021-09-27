package _3_1_1.service;

import _3_1_1.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);

//    public Set<Role> getRolesFromText(String text);

    List<Role> getAllRoles();
}
