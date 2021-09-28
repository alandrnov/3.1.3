package _3_1_1.service;

import _3_1_1.models.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);


    List<Role> getAllRoles();
}
