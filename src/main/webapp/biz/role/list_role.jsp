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
    <caption>角色列表</caption>
    <tr>
        <th>角色名称</th>
        <th>描述说明</th>
        <th>操作</th>
    </tr>
    <c:if test="${empty roleList}">
        暂时没有数据哦！
    </c:if>
    <c:if test="${not empty roleList}">
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.name}</td>
                <td>${role.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/role/privilegeList?id=${role.id}" target="main">查看权限</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
