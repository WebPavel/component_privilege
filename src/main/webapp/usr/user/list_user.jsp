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
    <caption>用户列表</caption>
    <tr>
        <th>用户名称</th>
        <th>昵称</th>
        <th>操作</th>
    </tr>
    <c:if test="${empty userList}">
        暂时没有数据哦！
    </c:if>
    <c:if test="${not empty userList}">
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.nickname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/roleList?id=${user.id}" target="main">查看角色</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
