package _3_1_1.dao;

import _3_1_1.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl_JPA implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        try {
            entityManager
                    .createQuery("select r from Role r where r.role = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            Role r = new Role();
            r.setRole(name);
            entityManager.persist(r);
        }

        return entityManager
                .createQuery("select r from Role r where r.role = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

//    @Override
//    public Set<Role> getRolesFromText(String text) {
//        Set<Role> roles = new HashSet<>();
//
//        text = text.toLowerCase();
//
//        if ( text.contains("admin") ) {
//            Role role = getRoleByName("ROLE_ADMIN");
//            roles.add(role);
//        }
//        if ( text.contains("user") ) {
//            Role role = getRoleByName("ROLE_USER");
//            roles.add(role);
//        }
//
//        return roles;
//    }
    @Override

    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }
}
