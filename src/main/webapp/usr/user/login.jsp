<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/11
  Time: 2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>登录</h2>
<font color="red">${requestScope["login.message"]}</font><br>
<form action="${pageContext.request.contextPath}/user/login" method="post" target="_top">
    用户名称：<input type="text" name="username" size="40"><br>
    密码：<input type="password" name="password" size="40"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
