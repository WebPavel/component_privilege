<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/10
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${empty existUser}">
        <h2>您还没有登录，请先<a href="${pageContext.request.contextPath}/usr/user/login.jsp" target="main">登录</a>！</h2>
    </c:if>
    <c:if test="${not empty existUser}">
        <c:if test="${existUser.username == 'admin'}">
            <h4>权限管理</h4>
            <a href="${pageContext.request.contextPath}/biz/privilege/add_privilege.jsp" target="main">添加权限</a><br>
            <a href="${pageContext.request.contextPath}/privilege/list" target="main">查看权限</a><br>

            <h4>角色管理</h4>
            <a href="${pageContext.request.contextPath}/biz/role/add_role.jsp" target="main">添加角色</a><br>
            <a href="${pageContext.request.contextPath}/role/list" target="main">查看角色</a><br>

            <h4>用户管理</h4>
            <a href="${pageContext.request.contextPath}/usr/user/add_user.jsp" target="main">添加用户</a><br>
            <a href="${pageContext.request.contextPath}/user/list" target="main">查看用户</a><br>
        </c:if>
        <c:if test="${existUser.username != 'admin'}">
            <h4>订单管理</h4>
            <a href="${pageContext.request.contextPath}/biz/order/list_order.jsp" target="main">查看订单</a><br>
            <a href="${pageContext.request.contextPath}/biz/order/edit_order.jsp" target="main">编辑订单</a><br>
            <a href="${pageContext.request.contextPath}/biz/order/pay_order.jsp" target="main">支付订单</a><br>
        </c:if>

        <a href="${pageContext.request.contextPath}/user/logout" target="_top">注销</a><br>
    </c:if>
</body>
</html>
