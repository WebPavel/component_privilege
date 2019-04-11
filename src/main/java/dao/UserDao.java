package dao;

import entity.Role;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;
import utils.MD5Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    public int add(User user) throws SQLException {
        String sql = "insert into usr_user values(null, ?, ?, ?, ?, ?)";
        Object[] params = {user.getUsername(), MD5Utils.md5Encode(user.getPassword()), user.getNickname(), new Date(), new Date()};
        return queryRunner.update(sql, params);
    }

    public List<User> findAll() throws SQLException {
        String sql = "select * from usr_user";
        return queryRunner.query(sql, new BeanListHandler<User>(User.class));
    }

    public User find(User user) throws SQLException {
        String sql = "select * from usr_user where username = ? and password = ?";
        Object[] params = {user.getUsername(), MD5Utils.md5Encode(user.getPassword())};
        return queryRunner.query(sql, new BeanHandler<User>(User.class), params);
    }

    public User findById(Integer id) throws SQLException {
        String sql = "select * from usr_user where id = ?";
        Object[] params = {id};
        return queryRunner.query(sql, new BeanHandler<User>(User.class), params);
    }

    public User findRoleById(final Integer id) throws SQLException {
        String sql = "select user.id, user.username, user.nickname, role.id, role.name, role.description from usr_user user, biz_user_role ur, biz_role role where user.id=ur.userId and ur.roleId=role.Id and user.id=?";
        Object[] params = {id};
        return queryRunner.query(sql, new ResultSetHandler<User>() {
            @Override
            public User handle(ResultSet rs) throws SQLException {
                User user = new User();
                List<Role> roles = new ArrayList<>();
                if (!rs.next()) {
                    return findById(id);
                }
                rs.previous();
                while (rs.next()) {
                    user.setId(rs.getInt(1));
                    user.setUsername(rs.getString(2));
                    user.setNickname(rs.getString(3));
                    Role role = new Role();
                    role.setId(rs.getInt(4));
                    role.setName(rs.getString(5));
                    role.setDescription(rs.getString(6));
                    roles.add(role);
                }
                if (roles.size() == 0) {
                    user.setRoles(null);
                } else {
                    user.setRoles(roles);
                }
                return user;
            }
        }, params);
    }

    public int deleteRoleById(Connection connection, Integer id) throws SQLException {
        String sql = "delete from biz_user_role where userId = ?";
        Object[] params = {id};
        return queryRunner.update(connection, sql, params);
    }

    public int addDefaultRole(Connection connection, Integer id) throws SQLException {
        // 角色=1，代表普通用户
        int[] rows = addRoleById(connection, id, new Integer[]{1});
        return rows[0];
    }

    public int[] addRoleById(Connection connection, Integer id, Integer[] roleIds) throws SQLException {
        String sql = "insert into biz_user_role values(?,?)";
        Object[][] params = new Object[roleIds.length][2];
        for (int i = 0; i < roleIds.length; i++) {
            params[i][0] = id;
            params[i][1] = roleIds[i];
        }
        return queryRunner.batch(connection, sql, params);
    }
}
