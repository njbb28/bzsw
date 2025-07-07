<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>企业物资管理系统</title>
    <style>
        .navbar { background-color: #333; overflow: hidden; }
        .navbar a { float: left; color: white; text-align: center; padding: 14px 16px; text-decoration: none; }
        .navbar a:hover { background-color: #ddd; color: black; }
        .navbar .right { float: right; }
        .container { padding: 20px; }
    </style>
</head>
<body>
<div class="navbar">
    <a href="<%= request.getContextPath() %>/index.jsp">首页</a>

    <c:if test="${sessionScope.user != null}">
        <a href="<%= request.getContextPath() %>/inStock/list">入库管理</a>
        <a href="<%= request.getContextPath() %>/outStock/list">出库管理</a>
        <a href="<%= request.getContextPath() %>/material/list">物资管理</a>
        <a href="<%= request.getContextPath() %>/purchasePlan/list">采购计划</a>
        <a href="<%= request.getContextPath() %>/stockCheck/list">库存盘点</a>

        <div class="right">
            欢迎，<c:out value="${sessionScope.user.realName}" default="${sessionScope.user.username}" />
            <a href="<%= request.getContextPath() %>/logout">退出</a>
        </div>
    </c:if>
</div>

<div class="container">
    <h1>企业物资管理系统</h1>

    <c:if test="${sessionScope.user == null}">
        <p>请先 <a href="<%= request.getContextPath() %>/login">登录</a> 以访问系统功能。</p>
    </c:if>

    <c:if test="${sessionScope.user != null}">
        <p>当前登录用户：<c:out value="${sessionScope.user.realName}" default="${sessionScope.user.username}" /> (${sessionScope.user.role})</p>
        <p>系统功能导航：请使用上方菜单访问相应功能。</p>
    </c:if>
</div>
</body>
</html>