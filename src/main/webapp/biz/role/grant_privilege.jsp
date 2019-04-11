<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/11
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>title</title>
    <script type="text/javascript">
        function change(obj) {
            var options = f.pid;
            for(var i=0;i<options.length;i++) {
                options[i].checked = obj.checked;
            }
        }
    </script>
</head>
<body>
    <c:if test="${empty role}">
        角色不存在
    </c:if>
    <c:if test="${not empty role}">
        <table width="60%" border="1" align="center">
            <tr>
                <td>角色名称：</td>
                <td>${role.name}</td>
            </tr>
            <tr>
                <td>描述说明：</td>
                <td>${role.description}</td>
            </tr>
            <tr>
                <td>权限：</td>
                <td>
                    <c:if test="${empty role.privileges}">
                        您暂时没有任何权限！
                    </c:if>
                    <c:if test="${not empty role.privileges}">
                        <c:forEach var="privilege" items="${role.privileges}">
                            ${privilege.name}<br>
                        </c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>权限列表：</td>
                <td>
                    <form name="f" action="${pageContext.request.contextPath}/role/grantPrivilege" method="post" target="_top">
                        <input type="hidden" name="id" value="${role.id}">
                        <c:if test="${empty privilegeList}">
                            权限列表暂时没有任何权限！
                        </c:if>
                        <c:if test="${not empty privilegeList}">
                            <input type="checkbox" onchange="change(this);">全选<br>
                            <c:forEach var="p" items="${privilegeList}">
                                <input type="checkbox" name="pid" value="${p.id}">${p.name}<br>
                            </c:forEach>
                            <input type="submit" value="授权">
                        </c:if>
                    </form>
                </td>
            </tr>
        </table>
    </c:if>
</body>
</html>
