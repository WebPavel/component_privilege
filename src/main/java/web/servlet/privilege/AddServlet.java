package web.servlet.privilege;

import entity.Privilege;
import org.apache.commons.beanutils.BeanUtils;
import service.PrivilegeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/privilege/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Privilege privilege = new Privilege();
        try {
            BeanUtils.populate(privilege, request.getParameterMap());
            PrivilegeService privilegeService = new PrivilegeService();
            privilegeService.add(privilege);
            response.getWriter().write("<h2>添加成功<h2>");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<h2>添加失败<h2>");
            return;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
