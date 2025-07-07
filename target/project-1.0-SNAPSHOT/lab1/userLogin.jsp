<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<!-- 使用相对路径访问同级目录下的Servlet -->
<form action="UserRoleServlet" method="post">
    用户名：<input type="text" name="username" required><br>
    角色：
    <select name="role">
        <option value="user">普通用户</option>
        <option value="admin">管理员</option>
    </select><br>
    <input type="submit" value="登录">
</form>
</body>
</html>