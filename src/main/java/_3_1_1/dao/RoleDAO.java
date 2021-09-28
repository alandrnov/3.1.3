package _3_1_1.dao;


import _3_1_1.models.Role;

import java.util.List;


public interface RoleDAO {

    Role getRoleByName(String name);

    List<Role> getAllRoles();
}
