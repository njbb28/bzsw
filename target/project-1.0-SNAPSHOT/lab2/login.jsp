<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户登录</title>
    <style>
        /* 样式不变 */
    </style>
</head>
<body>
<div class="container">
    <!-- ✅ 从 request 作用域获取消息（与 validate.jsp 一致） -->
    <c:if test="${not empty requestScope.message}">
        <div class="error"><c:out value="${message}"/></div> <!-- ✅ 闭合正确，使用 c:out -->
    </c:if>

    <h2>用户登录</h2>
    <!-- ✅ 使用 c:url 生成路径（符合 Todo 1） -->
    <form action='<c:url value="/lab2/validate.jsp"/>' method="post"> <!-- 添加 ${ctx} 确保路径正确 -->
        <div class="form-group">
            <label>用户名:</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>密码:</label>
            <input type="password" name="password" required minlength="6">
        </div>
        <button type="submit">登录</button>
    </form>
</div>
</body>
</html>