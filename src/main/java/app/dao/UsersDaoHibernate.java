package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class UsersDaoHibernate implements UsersDao {

    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public long getUserId(String login) throws Exception {
        long id = -1;
        String jpql = "select u from User u where u.login = :login";
        User user = em.createQuery(jpql, User.class).setParameter("login", login).getSingleResult();
        if (user != null) {
            id = user.getId();
        }
        return id;
    }

    @Override
    @Transactional
    public void addUser(User user) throws Exception {
        em.persist(user);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) throws Exception {
        String jpql = "select u from User u where u.login = :login";
        User user = (User) em.createQuery(jpql).setParameter("login", login).getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public User getUserById(long id) throws Exception {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(long id) throws Exception {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional
    public void updateUser(User user) throws Exception {
        em.merge(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws Exception {
        List<User> list = null;
        list = (List<User>) em.createQuery("SELECT u FROM User u").getResultList();
        return list;
    }
}
