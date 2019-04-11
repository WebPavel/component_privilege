<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/12
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function change(obj) {
            var options = f.rid;
            for(var i=0;i<options.length;i++) {
                options[i].checked = obj.checked;
            }
        }
    </script>
</head>
<body>
<c:if test="${empty user}">
    用户不存在
</c:if>
<c:if test="${not empty user}">
    <table width="60%" border="1" align="center">
        <tr>
            <td>用户名称：</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>${user.nickname}</td>
        </tr>
        <tr>
            <td>角色：（默认配置‘普通用户’角色）</td>
            <td>
                <c:if test="${empty user.roles}">
                    您暂时没有任何角色！
                </c:if>
                <c:if test="${not empty user.roles}">
                    <c:forEach var="role" items="${user.roles}">
                        ${role.name}【${role.description}】<br>
                    </c:forEach>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>角色列表：</td>
            <td>
                <form name="f" action="${pageContext.request.contextPath}/user/grantRole" method="post" target="_top">
                    <input type="hidden" name="id" value="${user.id}">
                    <c:if test="${empty roleList}">
                        角色列表暂时没有任何角色！
                    </c:if>
                    <c:if test="${not empty roleList}">
                        <input type="checkbox" onchange="change(this);">全选<br>
                        <c:forEach var="r" items="${roleList}">
                            <input type="checkbox" name="rid" value="${r.id}">${r.name}<br>
                        </c:forEach>
                        <input type="submit" value="授予角色">
                    </c:if>
                </form>
            </td>
        </tr>
    </table>
</c:if>
</body>
</html>
