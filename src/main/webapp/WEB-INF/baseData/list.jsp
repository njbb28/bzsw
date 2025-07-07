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
        table { width: 100%; border-collapse: collapse; margin-bottom: 30px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); background-color: white; }
        th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #eee; }
        th { background-color: #f8f9fa; font-weight: 600; }
        tr:hover { background-color: #f9f9f9; }
        .action-cell { white-space: nowrap; }
        .action-link { color: #3498db; text-decoration: none; margin-right: 10px; transition: color 0.3s; }
        .action-link:hover { color: #2980b9; }
        .delete-link { color: #e74c3c; }
        .delete-link:hover { color: #c0392b; }
        .footer { margin-top: 50px; padding: 20px 0; background-color: #2c3e50; color: #bdc3c7; text-align: center; font-size: 0.9rem; }
        @media (max-width: 768px) {
            th, td { padding: 10px; font-size: 0.9rem; }
            .action-cell { white-space: normal; }
            .action-link { display: block; margin: 5px 0; }
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
    <h2 class="page-title">部门管理</h2>
    <a href="${pageContext.request.contextPath}/department/form" class="action-btn">新增部门</a>

    <table>
        <tr>
            <th>ID</th>
            <th>部门名称</th>
            <th>部门负责人</th>
            <th>联系电话</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${departments}" var="dept">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.deptName}</td>
                <td>${dept.leaderName}</td>
                <td>${dept.contactPhone}</td>
                <td class="action-cell">
                    <a href="${pageContext.request.contextPath}/department/view?id=${dept.id}" class="action-link">查看</a>
                    <a href="${pageContext.request.contextPath}/department/form?action=edit&id=${dept.id}" class="action-link">编辑</a>
                    <a href="${pageContext.request.contextPath}/department/delete?id=${dept.id}" class="action-link delete-link" onclick="return confirm('确定要删除吗？此操作不可恢复。')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="footer">
    <div class="container">
        企业物资管理系统 &copy; 2025
    </div>
</div>
</body>
</html>