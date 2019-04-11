package service;

import dao.RoleDao;
import entity.Role;
import exception.RoleException;
import org.apache.commons.dbutils.DbUtils;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleService {
    private RoleDao roleDao = new RoleDao();
    public void add(Role role) throws RoleException {
        try {
            roleDao.add(role);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoleException("添加失败");
        }
    }

    public List<Role> findAll() throws RoleException {
        try {
            return roleDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoleException("查找失败");
        }
    }

    public Role findById(Integer id) throws RoleException {
        try {
            return roleDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoleException("查找失败");
        }
    }

    public Role findPrivilegeById(Integer id) throws RoleException {
        try {
            return roleDao.findPrivilegeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoleException("查找失败");
        }
    }

    /**
     * 涉及事务：先删除角色原所有权限，再添加新的权限
     * @param role
     * @param privilegeIds
     */
    public void grantPrivilege(Role role, Integer[] privilegeIds) throws RoleException {
        // 取消所有权限
        if (privilegeIds == null) {
            try {
                roleDao.deleteAllPrivilege(role.getId());
                return;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RoleException("删除失败");
            }
        }
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            roleDao.deletePrivilegeById(connection, role.getId());
            roleDao.addPrivilegeById(connection, role.getId(), privilegeIds);
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
