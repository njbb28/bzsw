<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户仪表盘</title>
</head>
<body>
<h2>欢迎，${sessionScope.username}</h2>
<p>当前角色：${sessionScope.role}</p>

<c:choose>
    <c:when test="${sessionScope.role == 'admin'}">
        <div style="background-color: #e3f2fd; padding: 10px;">
            <h3>管理员面板</h3>
            <p>你拥有系统的完全访问权限！</p>
        </div>
    </c:when>
    <c:otherwise>
        <div style="background-color: #f5f5f5; padding: 10px;">
            <h3>用户面板</h3>
            <p>你拥有受限的访问权限。</p>
        </div>
    </c:otherwise>
</c:choose>

<!-- 使用相对路径返回同级目录下的login.jsp -->
<p><a href="userLogin.jsp">点击返回登录页面</a></p>

<!-- 安全检查：未登录时重定向到同级目录的login.jsp -->
<c:if test="${empty sessionScope.username}">
    <c:redirect url="userLogin.jsp"/>
</c:if>
</body>
</html>