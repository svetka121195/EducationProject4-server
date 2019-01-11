package app.service;

import app.dao.UsersDao;
import app.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserServiceImp implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImp.class);

    private UsersDao dao;

    @Autowired
    public void setDao(UsersDao dao) {
        this.dao = dao;
    }


    @Override
    public long getUserId(String login) {
        long id = -1;
        try {
            id = dao.getUserId(login);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return id;
    }

    @Override
    public void addUser(User user) {
        try {
            dao.addUser(user);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    @Override
    public User getUser(long id) {
        User user = null;
        try {
            user = dao.getUserById(id);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = dao.getUserByLogin(login);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        try {
            dao.deleteUser(id);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    @Override
    public void updateUser(User newUser) {
        try {
            dao.updateUser(newUser);
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = dao.getAllUsers();
        } catch (Exception e) {
            LOGGER.warn(e);
        }
        return list;
    }
}
