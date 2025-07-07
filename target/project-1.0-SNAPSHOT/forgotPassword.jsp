<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div style="max-width: 400px; margin: 50px auto;">
    <h2>重置密码</h2>
    <c:if test="${not empty resetError}">
        <p style="color: red;">${resetError}</p>
    </c:if>
    <c:if test="${not empty resetSuccess}">
        <p style="color: green;">${resetSuccess}</p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/forgotPassword">
        <label>用户名：</label>
        <input type="text" name="username" required><br>
        <label>新密码：</label>
        <input type="password" name="newPassword" required><br>
        <label>确认密码：</label>
        <input type="password" name="confirmPassword" required><br>
        <input type="submit" value="重置密码">
    </form>
</div>
<%@ include file="footer.jsp" %>