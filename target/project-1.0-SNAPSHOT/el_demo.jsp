<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>EL Demo</title>
</head>
<body>
<h1>EL 演示</h1>
<p>Request Msg: ${requestMsg}</p>
<p>Session Msg: ${sessionScope.sessionMsg}</p>
<p>Application Msg: ${applicationScope.appMsg}</p>

<h2>用户信息</h2>
<p>ID: ${user.id}</p>
<p>用户名: ${user.username}</p>
<p>邮箱: ${user.email}</p>
<p>性别: ${user.gender}</p>
<p>生日: <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/></p>

<h2>集合数据</h2>
<p>水果数组: ${fruits[0]}, ${fruits[1]}, ${fruits[2]}</p>
<p>城市列表: <c:forEach items="${cities}" var="city">${city} </c:forEach></p>
</body>
</html>