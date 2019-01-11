package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Светлана on 21.08.2018.
 */

@Repository
@EnableTransactionManagement
public class RolesDaoHibernate {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Set<Role> getAllRoles() throws Exception {
        List<Role> list = null;
        list = (List<Role>) em.createQuery("SELECT r FROM Role r").getResultList();
        return new HashSet<Role>(list);
    }

    @Transactional
    public Role getRoleByName(String name) throws Exception {
        String jpql = "select r from Role r where r.name = :name";
        Role role= (Role) em.createQuery(jpql).setParameter("name", name).getSingleResult();
        return role;
    }
}
