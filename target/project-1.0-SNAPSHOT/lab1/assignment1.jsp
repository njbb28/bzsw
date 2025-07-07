<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL循环标签实验</title>
</head>
<body>
<h1>TODO1: 打印1到10</h1>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
    </c:forEach>
</ul>

<h1>TODO2: 打印奇数列表</h1>
<ul>
    <c:forEach var="i" begin="1" end="10" step="2">
        <li>${i}</li>
    </c:forEach>
</ul>

<h1>TODO3: 打印偶数列表</h1>
<ul>
    <c:forEach var="i" begin="2" end="10" step="2">
        <li>${i}</li>
    </c:forEach>
</ul>

<h1>TODO4: 遍历数组</h1>
<%-- 声明数组并存储到PageContext --%>
<%
    String[] fruits = {"苹果", "香蕉", "橙子", "芒果"};
    pageContext.setAttribute("fruits", fruits);
%>
<ul>
    <c:forEach var="fruit" items="${fruits}">
        <li>${fruit}</li>
    </c:forEach>
</ul>

<h1>TODO5: 打印字符串</h1>
<%-- 使用<c:forTokens>分割字符串 --%>
<c:forTokens var="word" items="one:two:three-four-five" delims=":-">
    <span>${word}</span>
</c:forTokens>
</body>
</html>