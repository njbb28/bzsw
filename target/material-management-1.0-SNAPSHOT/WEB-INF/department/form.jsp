<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门管理 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .container { width: 90%; max-width: 1200px; margin: 0 auto; padding: 20px 0; }
        .header .container { display: flex; justify-content: space-between; align-items: center; }
        h1 { margin: 0; font-size: 1.8rem; }
        .nav-link { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 4px; transition: background-color 0.3s; }
        .nav-link:hover { background-color: rgba(255, 255, 255, 0.2); }

        .page-title { margin: 30px 0 20px; color: #2c3e50; }
        .action-btn { display: inline-block; background-color: #3498db; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-bottom: 20px; font-weight: 500; }
        .action-btn:hover { background-color: #2980b9; }
        form { background-color: white; padding: 25px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .form-title { margin-top: 0; color: #2c3e50; }
        .form-table { width: 100%; border-collapse: separate; border-spacing: 0 15px; }
        .form-table th { width: 120px; color: #7f8c8d; padding: 8px 0; text-align: left; }
        .form-table td { padding: 0; }
        input, select { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ddd; border-radius: 4px; }
        button { background-color: #27ae60; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 1rem; }
        button:hover { background-color: #2ecc71; }
        .required { color: #e74c3c; margin-left: 5px; }
        .error-message { color: #e74c3c; font-weight: bold; margin: 10px 0; }
        .back-link { color: #7f8c8d; text-decoration: none; }
        .back-link:hover { color: #3498db; }
        .footer { margin-top: 50px; padding: 20px 0; background-color: #2c3e50; color: #bdc3c7; text-align: center; font-size: 0.9rem; }
        @media (max-width: 768px) {
            .form-table { border-spacing: 0 10px; }
            .form-table th { width: 100px; font-size: 0.9rem; }
        }
    </style>
</head>
<body>
<div class="header">
    <div class="container">
        <h1>企业物资管理系统</h1>
        <a href="${pageContext.request.contextPath}/" class="nav-link">首页</a>
        <a href="${pageContext.request.contextPath}/baseData/list" class="nav-link">基础数据</a>
    </div>
</div>

<div class="container">
    <h2 class="page-title">
        <c:if test="${action == 'edit'}">编辑部门</c:if>
        <c:if test="${action == 'add' || empty action}">新增部门</c:if>
    </h2>
    <a href="${pageContext.request.contextPath}/department/list" class="back-link">返回部门列表</a>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/department/save" method="post">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${action == 'edit'}">
            <input type="hidden" name="id" value="${department.id}">
        </c:if>

        <h3 class="form-title">部门信息</h3>
        <table class="form-table">
            <tr>
                <th>部门名称：<span class="required">*</span></th>
                <td><input type="text" name="deptName" value="${department.deptName}" required></td>
            </tr>
            <tr>
                <th>部门负责人：</th>
                <td><input type="text" name="leaderName" value="${department.leaderName}"></td>
            </tr>
            <tr>
                <th>联系电话：</th>
                <td><input type="text" name="contactPhone" value="${department.contactPhone}"></td>
            </tr>
            <tr>
                <th colspan="2">
                    <button type="submit">保存</button>
                    <a href="${pageContext.request.contextPath}/department/list" style="margin-left: 10px;">取消</a>
                </th>
            </tr>
        </table>
    </form>
</div>

<div class="footer">
    <div class="container">
        企业物资管理系统 &copy; 2025
    </div>
</div>
</body>
</html>