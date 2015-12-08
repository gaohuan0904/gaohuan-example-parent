<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<shiro:guest>
    游客您好，请<a href="${pageContext.request.contextPath}/login">登录</a>

</shiro:guest>

<shiro:user>
    欢迎<shiro:principal/>,点击<a href="${pageContext.request.contextPath}/logout">退出</a>
    <a href="${pageContext.request.contextPath}/client">客户管理</a>
    <a href="${pageContext.request.contextPath}/user">用户管理</a>

</shiro:user>

</body>
</html>
