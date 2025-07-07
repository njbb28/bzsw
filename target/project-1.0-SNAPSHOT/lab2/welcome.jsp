<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/> <!-- 引用上下文路径 -->

<html>
<head>
    <title>欢迎页</title>
    <style>
        .container { width: 500px; margin: 50px auto; text-align: center; }
        .welcome { color: #2E7D32; font-size: 24px; margin-bottom: 20px; }
        .logout { margin-top: 30px; }
        .logout a { color: #1976D2; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <!-- 使用<cout>安全输出用户名，符合PPT要求 -->
    <h1 class="welcome">欢迎，<c:out value="${param.username}"/>!</h1>
    <p>您已成功登录系统</p>

    <!-- 返回登录页的链接 -->
    <div class="logout">
        <a href='<c:url value="/lab2/login.jsp"/>'>退出登录</a>
    </div>
</div>
</body>
</html>