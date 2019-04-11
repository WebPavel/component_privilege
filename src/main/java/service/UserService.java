package service;

import dao.UserDao;
import entity.User;
import exception.UserException;
import org.apache.commons.dbutils.DbUtils;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    public void add(User user) throws UserException {
        try {
            userDao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("添加失败");
        }
    }

    public List<User> findAll() throws UserException {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("查找失败");
        }
    }

    public User login(User user) throws UserException {
        try {
            return userDao.find(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("查找失败");
        }
    }

    public User findRoleById(Integer id) throws UserException {
        try {
            return userDao.findRoleById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("查找失败");
        }
    }

    public User findById(Integer id) throws UserException {
        try {
            return userDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("查找失败");
        }
    }

    public void grantRole(User user, Integer[] roleIds) throws UserException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection();
            connection.setAutoCommit(false);
            // 未选择角色，配置默认角色
            userDao.deleteRoleById(connection, user.getId());
            if (roleIds == null) {
                userDao.addDefaultRole(connection, user.getId());
            } else {
                userDao.addRoleById(connection, user.getId(), roleIds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DbUtils.rollback(connection);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                DbUtils.commitAndClose(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
