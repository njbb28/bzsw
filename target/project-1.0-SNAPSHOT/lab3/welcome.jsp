<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.Date" %> <%-- 新增 import，导入 Date 类 --%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="loginBean" class="com.example.lab3.Login" scope="request"/>

<%
    // 显式转换时间戳为 Date 对象
    Date creationDate = new Date(pageContext.getSession().getCreationTime());
    Date lastAccessedDate = new Date(pageContext.getSession().getLastAccessedTime());
%>

<html>
<body>
<h2>欢迎，<jsp:getProperty property="username" name="loginBean"/></h2>

<h3>Session 信息</h3>
<p>用户：${sessionScope.user}</p>
<p>Session ID: ${pageContext.session.id}</p>
<p>创建时间: <fmt:formatDate value="<%= creationDate %>" type="both" dateStyle="long" timeStyle="long"/></p>
<p>最后访问时间: <fmt:formatDate value="<%= lastAccessedDate %>" type="both" dateStyle="long" timeStyle="long"/></p>
</body>
</html>