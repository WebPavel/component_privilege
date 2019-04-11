package web.servlet.role;

import entity.Privilege;
import entity.Role;
import service.PrivilegeService;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/role/privilegeList")
public class PrivilegeListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RoleService roleService = new RoleService();
        PrivilegeService privilegeService = new PrivilegeService();
        try {
            Role role = roleService.findPrivilegeById(Integer.parseInt(id));
            List<Privilege> privilegeList = privilegeService.findAll();
            request.setAttribute("role", role);
            request.setAttribute("privilegeList", privilegeList);
            request.getRequestDispatcher("/biz/role/grant_privilege.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
