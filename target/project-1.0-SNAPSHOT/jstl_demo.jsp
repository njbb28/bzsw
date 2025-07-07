<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Demo</title>
</head>
<body>
<h1>JSTL 演示</h1>

<h2>用户列表</h2>
<c:forEach items="${userList}" var="user">
    <div>
        <p>ID: ${user.id}</p>
        <p>用户名: ${user.username}</p>
        <p>邮箱: ${user.email}</p>
        <p>性别: ${user.gender}</p>
        <p>生日: <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/></p>
    </div>
    <hr>
</c:forEach>

<h2>条件判断</h2>
<c:if test="${isLoggedIn}">
    <p>用户已登录</p>
</c:if>
<c:choose>
    <c:when test="${score >= 90}">
        <p>成绩优秀</p>
    </c:when>
    <c:when test="${score >= 80}">
        <p>成绩良好</p>
    </c:when>
    <c:otherwise>
        <p>成绩一般</p>
    </c:otherwise>
</c:choose>
</body>
</html>