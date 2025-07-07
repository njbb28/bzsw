<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %> <!-- 引入头部 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="max-width: 500px; margin: 50px auto;">
    <h2>用户注册</h2>
    <c:if test="${not empty registerError}">
        <p style="color: red;">${registerError}</p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <label>用户名：</label>
        <input type="text" name="username" required><br>

        <label>密码：</label>
        <input type="password" name="password" required><br>

        <label>邮箱：</label>
        <input type="email" name="email" required><br>

        <label>性别：</label>
        <select name="gender">
            <option value="male">男</option>
            <option value="female">女</option>
        </select><br>

        <label>出生日期：</label>
        <input type="date" name="birthDate" required><br>

        <input type="submit" value="注册">
    </form>
</div>

<%@ include file="footer.jsp" %> <!-- 引入底部 -->