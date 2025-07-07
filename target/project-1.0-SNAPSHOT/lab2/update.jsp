<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<sql:setDataSource var="myDs" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver://localhost:1433;databaseName=Userdb;encrypt=true;trustServerCertificate=true"
                   user="sa1" password="123456"/>

<html>
<head>
    <title>更新用户信息</title>
    <style>
        .form-group { margin: 15px 0; }
        input[type="text"], input[type="password"], input[type="email"] { width: 300px; }
    </style>
</head>
<body>
<h2>更新用户信息</h2>

<!-- 获取URL中的用户ID -->
<c:set var="userId" value="${param.id}"/>

<!-- 查询用户当前信息 -->
<sql:query dataSource="${myDs}" var="userQuery">
    SELECT * FROM usertable WHERE id = ?
    <sql:param value="${userId}"/>
</sql:query>

<!-- 显示更新表单 -->
<c:if test="${userQuery.rows[0] != null}">
    <form method="post" action="<c:url value='/lab2/update.jsp'/>">
        <!-- 隐藏ID参数 -->
        <input type="hidden" name="id" value="${userId}"/>

        <div class="form-group">
            用户名：<input type="text" name="username"
                       value="${userQuery.rows[0].username}" required>
        </div>
        <div class="form-group">
            密码：<input type="password" name="password"
                      value="${userQuery.rows[0].password}" required minlength="6">
        </div>
        <div class="form-group">
            邮箱：<input type="email" name="email"
                      value="${userQuery.rows[0].email}" required>
        </div>
        <div class="form-group">
            性别：
            <input type="radio" name="gender" value="male"
                ${userQuery.rows[0].gender == 'male' ? 'checked' : ''}> 男
            <input type="radio" name="gender" value="female"
                ${userQuery.rows[0].gender == 'female' ? 'checked' : ''}> 女
        </div>
        <div class="form-group">
            生日：<input type="date" name="birthDate"
                      value="<fmt:formatDate value='${userQuery.rows[0].birthdate}' pattern='yyyy-MM-dd'/>" required>
        </div>
        <button type="submit">保存修改</button>
    </form>

    <!-- 处理表单提交（更新数据库） -->
    <c:if test="${not empty param.username}">
        <sql:update dataSource="${myDs}" var="updateResult">
            UPDATE usertable
            SET username = ?, password = ?, email = ?, gender = ?, birthdate = ?
            WHERE id = ?
            <sql:param value="${param.username}"/>
            <sql:param value="${param.password}"/>
            <sql:param value="${param.email}"/>
            <sql:param value="${param.gender}"/>
            <sql:param value="${param.birthDate}"/>
            <sql:param value="${param.id}"/>
        </sql:update>

        <c:if test="${updateResult > 0}">
            <div style="color:green;">更新成功！记录ID：${userId}</div>
            <a href="<c:url value='/lab2/register.jsp'/>">返回用户列表</a>
        </c:if>
    </c:if>
</c:if>
</body>
</html>