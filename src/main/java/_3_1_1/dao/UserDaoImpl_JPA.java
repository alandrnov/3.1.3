package _3_1_1.dao;

import _3_1_1.models.Role;
import _3_1_1.models.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl_JPA implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.roles where u.login = :login", User.class)
                .setParameter("login", login);
                try {
                    return query.getSingleResult();
                }catch (NoResultException e) {
                    return null;
                }
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

        entityManager.merge(user);
    }

//    @Override
//
//    public List<Role> getAllRoles() {
//        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
//    }

    @Override
    public User getUserById(Long id) {
        User u = entityManager.createQuery("select u from User u join fetch u.roles where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return u;
    }


}