package app.service;

import app.dao.RolesDaoHibernate;
import app.model.Role;
import app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Светлана on 21.08.2018.
 */
@Service
public class RoleServiceImp {
    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImp.class);

    @Autowired
    private RolesDaoHibernate dao;

    public Set<Role> getAllRoles() {
        Set<Role> list = null;
        try {
            list = dao.getAllRoles();
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return list;
    }

    public Role getRoleByName(String name){
        Role role = null;
        try {
            role = dao.getRoleByName(name);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return role;
    }
}
