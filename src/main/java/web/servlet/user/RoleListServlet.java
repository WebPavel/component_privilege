package web.servlet.user;

import entity.Role;
import entity.User;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/roleList")
public class RoleListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        try {
            User user = userService.findRoleById(Integer.parseInt(id));
            List<Role> roleList = roleService.findAll();
            request.setAttribute("user", user);
            request.setAttribute("roleList", roleList);
            request.getRequestDispatcher("/usr/user/grant_role.jsp").forward(request, response);
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
