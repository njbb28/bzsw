<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>企业物资管理系统 - 登录</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .login-container { width: 300px; margin: 100px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        .error { color: red; margin-bottom: 10px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
<div class="login-container">
    <h2>企业物资管理系统</h2>

    <% if (request.getAttribute("errorMsg") != null) { %>
    <div class="error"><%= request.getAttribute("errorMsg") %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">登录</button>
    </form>
</div>
</body>
</html>