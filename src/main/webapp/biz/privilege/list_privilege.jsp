<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/11
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table width="60%" border="1" align="center">
        <caption>权限列表</caption>
        <tr>
            <th>权限名称</th>
            <th>权限路径</th>
            <th>描述说明</th>
        </tr>
        <c:if test="${empty privilegeList}">
            暂时没有数据哦！
        </c:if>
        <c:if test="${not empty privilegeList}">
            <c:forEach items="${privilegeList}" var="privilege">
                <tr>
                    <td>${privilege.name}</td>
                    <td>${privilege.visitedPath}</td>
                    <td>${privilege.description}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
