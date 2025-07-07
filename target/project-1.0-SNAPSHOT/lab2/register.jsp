<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 统一变量名为ctx -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<sql:setDataSource
        var="myDs"
        driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
        url="jdbc:sqlserver://localhost:1433;databaseName=Userdb;encrypt=true;trustServerCertificate=true"
        user="sa1"
        password="123456"
/>

<html>
<head>
    <title>用户注册</title>
    <style>
        .form-group{margin:15px 0;}
        table{width:80%;border-collapse:collapse;}
        th,td{padding:8px;border:1px solid #ddd;}
    </style>
</head>
<body>
<h2>用户注册系统</h2>

<!-- 表单提交路径改为当前页（register.jsp），避免重复提交 -->
<form method="post">
    <div class="form-group">
        用户名：<input type="text" name="username" required>
    </div>
    <div class="form-group">
        密码：<input type="password" name="password" required minlength="6">
    </div>
    <div class="form-group">
        邮箱：<input type="email" name="email" required>
    </div>
    <div class="form-group">
        性别：
        <input type="radio" name="gender" value="male" checked> 男
        <input type="radio" name="gender" value="female"> 女
    </div>
    <div class="form-group">
        生日：<input type="date" name="birthDate" required>
    </div>
    <button type="submit">提交注册</button>
</form>

<c:if test="${not empty param.username}">
    <sql:update dataSource="${myDs}" var="insertResult">
        INSERT INTO usertable (username, password, email, gender, birthdate)
        VALUES (?, ?, ?, ?, ?)
        <sql:param value="${param.username}"/>
        <sql:param value="${param.password}"/>
        <sql:param value="${param.email}"/>
        <sql:param value="${param.gender}"/>
        <sql:param value="${param.birthDate}"/>
    </sql:update>
    <c:if test="${insertResult > 0}">
        <div style="color:green;">注册成功！记录ID：${insertResult}</div>
    </c:if>
</c:if>

<h3>已注册用户列表</h3>
<sql:query dataSource="${myDs}" var="usersQuery">
    SELECT id, username, email, gender, birthdate FROM usertable ORDER BY id DESC
</sql:query>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>邮箱</th>
        <th>性别</th>
        <th>生日</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="row" items="${usersQuery.rows}">
        <tr>
            <td>${row.id}</td>
            <td>${row.username}</td>
            <td>${row.email}</td>
            <td>${row.gender}</td>
            <td><fmt:formatDate value="${row.birthdate}" pattern="yyyy-MM-dd"/></td>
            <!-- 新增更新链接（携带用户ID） -->
            <td>
                <a href="<c:url value='/lab2/update.jsp'>
                        <c:param name='id' value='${row.id}'/>
                    </c:url>">更新</a>
            </td>
            <!-- 新增删除链接（携带用户ID，添加确认提示） -->
            <td>
                <a href="<c:url value='/lab2/delete.jsp'>
                        <c:param name='id' value='${row.id}'/>
                    </c:url>"
                   onclick="return confirm('确认删除此用户？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>