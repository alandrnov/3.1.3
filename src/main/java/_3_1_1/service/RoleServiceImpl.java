package _3_1_1.service;

import _3_1_1.dao.RoleDAO;
import _3_1_1.models.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }


    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
