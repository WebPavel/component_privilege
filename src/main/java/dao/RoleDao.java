package dao;

import entity.Privilege;
import entity.Role;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    public int add(Role role) throws SQLException {
        String sql = "insert into biz_role values(null, ?, ?, ?, ?)";
        Object[] params = {role.getName(), role.getDescription(), new Date(), new Date()};
        return queryRunner.update(sql, params);
    }

    public List<Role> findAll() throws SQLException {
        String sql = "select * from biz_role";
        return queryRunner.query(sql, new BeanListHandler<Role>(Role.class));
    }

    public Role findById(Integer id) throws SQLException {
        String sql = "select * from biz_role where id = ?";
        Object[] params = {id};
        return queryRunner.query(sql, new BeanHandler<Role>(Role.class), params);
    }

    public Role findPrivilegeById(final Integer id) throws SQLException {
        String sql = "select role.id, role.name, role.description, privilege.id, privilege.name from biz_role role, biz_role_privilege rp, biz_privilege privilege where role.id = rp.roleId and rp.privilegeId = privilege.id and role.id=?";
        Object[] params = {id};
        return queryRunner.query(sql, new ResultSetHandler<Role>() {
            @Override
            public Role handle(ResultSet rs) throws SQLException {
                Role role = new Role();
                List<Privilege> privileges = new ArrayList<>();
                if (!rs.next()) {
                    return findById(id);
                }
                rs.previous();
                while (rs.next()) {
                    role.setId(rs.getInt(1));
                    role.setName(rs.getString(2));
                    role.setDescription(rs.getString(3));
                    Privilege privilege = new Privilege();
                    privilege.setId(rs.getInt(4));
                    privilege.setName(rs.getString(5));
                    privileges.add(privilege);
                }
                if (privileges.size() == 0) {
                    role.setPrivileges(null);
                } else {
                    role.setPrivileges(privileges);
                }
                return role;
            }
        }, params);
    }

    public int deletePrivilegeById(Connection connection, Integer id) throws SQLException {
        String sql = "delete from biz_role_privilege where roleId = ?";
        Object[] params = {id};
        return queryRunner.update(connection, sql, params);
    }

    public int[] addPrivilegeById(Connection connection, Integer id, Integer[] privilegeIds) throws SQLException {
        String sql = "insert into biz_role_privilege values(?,?)";
        Object[][] params = new Object[privilegeIds.length][2];
        for (int i = 0; i < privilegeIds.length; i++) {
            params[i][0] = id;
            params[i][1] = privilegeIds[i];
        }
        return queryRunner.batch(connection, sql, params);
    }

    public int deleteAllPrivilege(Integer id) throws SQLException {
        String sql = "delete from biz_role_privilege where roleId = ?";
        Object[] params = {id};
        return queryRunner.update(sql, params);
    }
}
