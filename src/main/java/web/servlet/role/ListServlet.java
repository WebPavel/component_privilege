package web.servlet.role;

import entity.Role;
import exception.RoleException;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/role/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleService roleService = new RoleService();
        List<Role> roleList = null;
        try {
            roleList = roleService.findAll();
            request.setAttribute("roleList", roleList);
            request.getRequestDispatcher("/biz/role/list_role.jsp").forward(request, response);
            return;
        } catch (RoleException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
