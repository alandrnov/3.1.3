package _3_1_1.dao;

import _3_1_1.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImplJPA implements RoleDAO {

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

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }
}
