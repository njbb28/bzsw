<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<sql:setDataSource var="myDs" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url="jdbc:sqlserver://localhost:1433;databaseName=Userdb;encrypt=true;trustServerCertificate=true"
                   user="sa1" password="123456"/>

<html>
<head>
    <title>删除用户</title>
</head>
<body>
<c:set var="userId" value="${param.id}"/>

<!-- 执行删除操作 -->
<sql:update dataSource="${myDs}" var="deleteResult">
    DELETE FROM usertable WHERE id = ?
    <sql:param value="${userId}"/>
</sql:update>

<c:if test="${deleteResult > 0}">
    <div style="color:red;">用户已删除！记录ID：${userId}</div>
    <a href="<c:url value='/lab2/register.jsp'/>">返回用户列表</a>
</c:if>
</body>
</html>