package _3_1_1.dao;

import _3_1_1.models.Role;
import _3_1_1.models.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl_JPA implements UserDao {

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
    public Set<Role> getRolesFromText(String text) {
        Set<Role> roles = new HashSet<>();

        text = text.toLowerCase();

        if ( text.contains("admin") ) {
            Role role = getRoleByName("ROLE_ADMIN");
            roles.add(role);
        }
        if ( text.contains("user") ) {
            Role role = getRoleByName("ROLE_USER");
            roles.add(role);
        }

        return roles;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.login = :login", User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        //User usertobeUpdated = getUserById(id);
        entityManager.merge(user);
    }

    @Override

    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        User u = entityManager.createQuery("select u from User u join fetch u.roles where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return u;
    }


}