package web.servlet.privilege;

import entity.Privilege;
import exception.PrivilegeException;
import service.PrivilegeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/privilege/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrivilegeService privilegeService = new PrivilegeService();
        List<Privilege> privilegeList = null;
        try {
            privilegeList = privilegeService.findAll();
            request.setAttribute("privilegeList", privilegeList);
            request.getRequestDispatcher("/biz/privilege/list_privilege.jsp").forward(request, response);
            return;
        } catch (PrivilegeException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
