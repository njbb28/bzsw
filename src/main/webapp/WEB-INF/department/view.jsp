<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看部门 - 企业物资管理系统</title>
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
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 25px; margin-bottom: 30px; }
        .info-table { width: 100%; border-collapse: separate; border-spacing: 0 10px; }
        .info-table tr { vertical-align: top; }
        .info-table th { width: 120px; color: #7f8c8d; padding: 8px 0; text-align: left; }
        .info-table td { padding: 8px 0; border-bottom: 1px solid #eee; }
        .actions { margin-top: 20px; }
        .action-link { display: inline-block; background-color: #3498db; color: white; padding: 10px 15px; text-decoration: none; border-radius: 4px; margin-right: 10px; transition: background-color 0.3s; }
        .action-link:hover { background-color: #2980b9; }
        .back-link { color: #7f8c8d; text-decoration: none; }
        .back-link:hover { color: #3498db; }
        .footer { margin-top: 50px; padding: 20px 0; background-color: #2c3e50; color: #bdc3c7; text-align: center; font-size: 0.9rem; }
        @media (max-width: 768px) {
            .info-table { border-spacing: 0 5px; }
            .info-table th { width: 100px; font-size: 0.9rem; }
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
    <h2 class="page-title">查看部门</h2>
    <a href="${pageContext.request.contextPath}/department/list" class="back-link">返回部门列表</a>

    <c:if test="${empty department}">
        <div class="card">
            <p style="color: #e74c3c; font-weight: bold;">未找到该部门信息</p>
        </div>
    </c:if>

    <c:if test="${not empty department}">
        <div class="card">
            <table class="info-table">
                <tr>
                    <th>部门ID：</th>
                    <td>${department.id}</td>
                </tr>
                <tr>
                    <th>部门名称：</th>
                    <td>${department.deptName}</td>
                </tr>
                <tr>
                    <th>部门负责人：</th>
                    <td>${department.leaderName}</td>
                </tr>
                <tr>
                    <th>联系电话：</th>
                    <td>${department.contactPhone}</td>
                </tr>
            </table>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/department/form?action=edit&id=${department.id}" class="action-link">编辑</a>
                <a href="${pageContext.request.contextPath}/department/list" class="action-link">返回列表</a>
            </div>
        </div>
    </c:if>
</div>

<div class="footer">
    <div class="container">
        企业物资管理系统 &copy; 2025
    </div>
</div>
</body>
</html>