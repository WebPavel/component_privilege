<%@page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<frameset rows="15%,*">
    <frame src="${pageContext.request.contextPath}/logo.jsp" noresize="noresize" name="logo">
    <frameset cols="20%,*">
        <frame src="${pageContext.request.contextPath}/menu.jsp" noresize="noresize" name="menu">
        <frame src="${pageContext.request.contextPath}/main.jsp" noresize="noresize" name="main">
    </frameset>
</frameset>
</html>
