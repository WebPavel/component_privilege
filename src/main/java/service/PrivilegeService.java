package service;

import dao.PrivilegeDao;
import entity.Privilege;
import exception.PrivilegeException;

import java.sql.SQLException;
import java.util.List;

public class PrivilegeService {
    private PrivilegeDao privilegeDao = new PrivilegeDao();
    public void add(Privilege privilege) throws PrivilegeException {
        try {
            privilegeDao.add(privilege);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrivilegeException("添加失败");
        }
    }

    public List<Privilege> findAll() throws PrivilegeException {
        try {
            return privilegeDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PrivilegeException("查找失败");
        }
    }
}
