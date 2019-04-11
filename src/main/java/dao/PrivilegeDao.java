package dao;

import entity.Privilege;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PrivilegeDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    public int add(Privilege privilege) throws SQLException {
        String sql = "insert into biz_privilege values(null, ?, ?, ?, ?, ?)";
        Object[] params = {privilege.getName(), privilege.getVisitedPath(), privilege.getDescription(), new Date(), new Date()};
        return queryRunner.update(sql, params);
    }

    public List<Privilege> findAll() throws SQLException {
        String sql = "select * from biz_privilege";
        return queryRunner.query(sql, new BeanListHandler<Privilege>(Privilege.class));
    }
}
