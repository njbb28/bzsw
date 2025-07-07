<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div style="max-width: 400px; margin: 50px auto;">
    <h2>User Info (EL CODE)</h2>
    <p>ID : ${user.id}</p>
    <p>Username : ${user.username}</p>
    <p>Password : ${user.password}</p>
    <p>Email : ${user.email}</p>
    <p>Gender : ${user.gender}</p>
    <p>Birthdate :
        <c:choose>
            <c:when test="${not empty user.birthDate}">
                <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd"/>
            </c:when>
            <c:otherwise>
                未设置
            </c:otherwise>
        </c:choose>
    </p>

    <h2>User Info (JAVA CODE)</h2>
    <%
        com.example.models.User sessionUser = (com.example.models.User) session.getAttribute("user");
        out.println("ID :" + sessionUser.getId() + "<br>");
        out.println("Username :" + sessionUser.getUsername() + "<br>");
        out.println("Password :" + sessionUser.getPassword() + "<br>");
        out.println("Email :" + sessionUser.getEmail() + "<br>");
        out.println("Gender :" + sessionUser.getGender() + "<br>");

        // 修复日期格式化问题：添加空值检查
        java.util.Date birthDate = sessionUser.getBirthDate();
        if (birthDate != null) {
            out.println("Birthdate :" + new java.text.SimpleDateFormat("yyyy-MM-dd").format(birthDate) + "<br>");
        } else {
            out.println("Birthdate : 未设置<br>");
        }
    %>
    <a href="updateUser">更新用户信息</a>
</div>

<%@ include file="footer.jsp" %>