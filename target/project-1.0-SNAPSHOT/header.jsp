<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户中心</title>
    <style>
        .header {
            display: flex;
            justify-content: space-between;
            padding: 10px 20px;
            border-bottom: 1px solid #eee;
        }
        .login-status {
            text-align: right;
        }
    </style>
</head>
<body>
<div class="header">
    <img src="logo.jpg" alt="Logo" class="logo" style="height: 30px;">
    <div class="login-status">
        <c:if test="${not empty sessionScope.user}">
            欢迎，${user.username}! |
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            欢迎，Guest! |
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a> |
            <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
        </c:if>
    </div>
</div>