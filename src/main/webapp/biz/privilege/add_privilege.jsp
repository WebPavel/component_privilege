<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/10
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>添加权限</h2>
    <form action="${pageContext.request.contextPath}/privilege/add" method="post">
        权限名称：<input type="text" name="name" size="40"><br>
        权限路径：<input type="text" name="visitedPath" size="80"><br>
        描述说明：<textarea rows="10" cols="40" name="description"></textarea><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>
