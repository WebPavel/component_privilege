package web.servlet.role;

import entity.Role;
import exception.RoleException;
import service.RoleService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/role/grantPrivilege")
public class GrantPrivilegeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String[] pids = request.getParameterValues("pid");
        RoleService roleService = new RoleService();
        try {
            Role role = roleService.findById(Integer.parseInt(id));
            roleService.grantPrivilege(role, StringUtils.toInteger(pids));
            response.sendRedirect(request.getContextPath()+"/index.jsp");
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
