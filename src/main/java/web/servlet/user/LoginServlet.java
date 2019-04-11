package web.servlet.user;

import entity.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            UserService userService = new UserService();
            User loginUser = userService.login(user);
            if (loginUser == null) {
                request.setAttribute("login.message", "用户名或密码错误");
                request.getRequestDispatcher("/usr/user/login.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("existUser", loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
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
