<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="loginBean" class="com.example.lab3.Login" scope="request"/>
<jsp:setProperty property="*" name="loginBean"/>

<c:choose>
    <c:when test="${loginBean.username == 'admin' && loginBean.password == '123456'}">
        <c:set var="user" value="${loginBean.username}" scope="session"/>
        <jsp:forward page="welcome.jsp"/>
    </c:when>
    <c:otherwise>
        <h3>用户名或密码错误！</h3>
        <jsp:include page="login.jsp"/>
    </c:otherwise>
</c:choose>