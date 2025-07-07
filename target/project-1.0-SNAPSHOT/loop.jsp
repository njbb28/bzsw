<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Loop Demo</title>
</head>
<body>
<h2>c:forEach 循环 1-10</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
    </c:forEach>
</ul>

<h2>c:forEach 隔步长循环 (step=2)</h2>
<ul>
    <c:forEach var="i" begin="1" end="10" step="2">
        <li>${i}</li>
    </c:forEach>
</ul>

<h2>c:choose 条件判断</h2>
<%-- 模拟分数判断 --%>
<c:set var="score" value="85"/>
<c:choose>
    <c:when test="${score >= 90}">
        <p>等级：优</p>
    </c:when>
    <c:when test="${score >= 80}">
        <p>等级：良</p>
    </c:when>
    <c:otherwise>
        <p>等级：中</p>
    </c:otherwise>
</c:choose>
</body>
</html>