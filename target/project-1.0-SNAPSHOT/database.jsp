<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL SQL Demo</title>
</head>
<body>
<!-- 设置数据库连接 -->
<sql:setDataSource
        var="ds"
        driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/test"
        user="root"
        password="yourpassword"
/>

<!-- 查询用户表 -->
<sql:query var="result" dataSource="${ds}">
    SELECT id, username, email FROM users
</sql:query>

<h2>用户列表</h2>
<table border="1">
    <tr><th>ID</th><th>用户名</th><th>邮箱</th></tr>
    <c:forEach var="row" items="${result.rows}">
        <tr>
            <td>${row.id}</td>
            <td>${row.username}</td>
            <td>${row.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>