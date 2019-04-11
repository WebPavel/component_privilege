package web.servlet.user;

import entity.User;
import exception.UserException;
import service.UserService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/grantRole")
public class GrantRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String[] rids = request.getParameterValues("rid");
        UserService userService = new UserService();
        try {
            User user = userService.findById(Integer.parseInt(id));
            userService.grantRole(user, StringUtils.toInteger(rids));
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return;
        } catch (UserException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
