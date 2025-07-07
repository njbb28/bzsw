<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:choose>
    <c:when test="${param.username == 'admin' && param.password == '123456'}">
        <!-- 登录成功：重定向（符合 Todo 4） -->
        <c:redirect url="welcome.jsp">
            <c:param name="username" value="${param.username}"/> <!-- 符合 Todo 3 -->
        </c:redirect>
    </c:when>
    <c:otherwise>
        <!-- ✅ request 作用域 + 请求转发（符合 Todo 6 和应用逻辑图） -->
        <c:set var="message" value="用户名或密码错误" scope="request"/>
        <jsp:forward page="login.jsp"/> <!-- 转发保留 request 作用域 -->
    </c:otherwise>
</c:choose>