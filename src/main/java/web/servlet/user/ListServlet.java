package web.servlet.user;

import entity.User;
import exception.UserException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> userList = null;
        try {
            userList = userService.findAll();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/usr/user/list_user.jsp").forward(request, response);
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
