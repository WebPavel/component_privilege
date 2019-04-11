<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/11
  Time: 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>添加用户</h2>
<form action="${pageContext.request.contextPath}/user/add" method="post">
    用户名称：<input type="text" name="username" size="40"><br>
    密码：<input type="password" name="password" size="40"><br>
    昵称：<input type="text" name="nickname" size="40"><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
