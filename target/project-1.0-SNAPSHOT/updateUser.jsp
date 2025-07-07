<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="com.example.models.User" %>
<div style="max-width: 400px; margin: 50px auto;">
    <h2>更新用户信息</h2>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>
    <form method="post" action="<%= request.getContextPath() %>/updateUser">
        <input type="hidden" name="id" value="<%= user.getId() %>">

        <label>用户名：</label>
        <input type="text" name="username" value="<%= user.getUsername() %>" required><br>

        <label>密码：</label>
        <input type="password" name="password" value="<%= user.getPassword() %>" required><br>

        <label>邮箱：</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required><br>

        <label>性别：</label>
        <select name="gender">
            <option value="男" <%= user.getGender().equals("男") ? "selected" : "" %>>男</option>
            <option value="女" <%= user.getGender().equals("女") ? "selected" : "" %>>女</option>
        </select><br>

        <label>生日：</label>
        <input type="date" name="birthDate"
               value="<%= user.getBirthDate() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(user.getBirthDate()) : "" %>"
               required><br>

        <input type="submit" value="保存更新">
    </form>
</div>
<%@ include file="footer.jsp" %>