<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style="max-width: 800px; margin: 50px auto;">
    <h2>用户列表</h2>
    <c:if test="${empty userList}">
        <p>当前没有用户数据</p>
    </c:if>

    <c:forEach items="${userList}" var="user" varStatus="status">
        <div class="user-card" style="margin-bottom: 30px; padding: 20px; border: 1px solid #eee;">
            <h3>用户 ${status.count}</h3>
            <p><strong>用户名：</strong> ${user.username}</p>
            <p><strong>邮箱：</strong> ${user.email}</p>
            <p><strong>性别：</strong> ${user.gender eq 'male' ? '男' : '女'}</p>
            <p><strong>生日：</strong>
                <c:choose>
                    <c:when test="${not empty user.birthDate}">
                        <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/>
                    </c:when>
                    <c:otherwise>未设置</c:otherwise>
                </c:choose>
            </p>
        </div>
    </c:forEach>
</div>
<%@ include file="footer.jsp" %>