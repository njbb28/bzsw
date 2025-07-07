<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 1200px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; text-align: left; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #f5f5f5; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 3px; cursor: pointer; }
        .btn-primary { background-color: #3498db; color: white; }
        .btn-danger { background-color: #e74c3c; color: white; }
        .btn-warning { background-color: #f39c12; color: white; }
        .btn-success { background-color: #2ecc71; color: white; }
        .search-box { margin-bottom: 20px; }
        .search-box input { padding: 8px; border: 1px solid #ddd; border-radius: 3px; }
        .search-box button { padding: 8px 12px; background-color: #3498db; color: white; border: none; border-radius: 3px; cursor: pointer; }
        .pagination { margin-top: 20px; }
        .pagination a { padding: 8px 12px; text-decoration: none; border: 1px solid #ddd; margin: 0 4px; }
        .pagination a.active { background-color: #3498db; color: white; }
        .alert { padding: 15px; margin-bottom: 20px; border: 1px solid transparent; border-radius: 4px; }
        .alert-success { color: #3c763d; background-color: #dff0d8; border-color: #d6e9c6; }
        .alert-danger { color: #a94442; background-color: #f2dede; border-color: #ebccd1; }
    </style>
</head>
<body>
<div class="header">
    <h1>企业物资管理系统</h1>
</div>
<div class="container">
    <div class="card">
        <h2>物资列表</h2>
        <div class="search-box">
            <form action="${pageContext.request.contextPath}/material/list" method="get">
                <input type="text" name="keyword" placeholder="请输入物资名称或规格">
                <button type="submit">搜索</button>
            </form>
        </div>
        <a href="${pageContext.request.contextPath}/material/form" class="btn btn-success">新增物资</a>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>物资名称</th>
                <th>规格型号</th>
                <th>生产厂家</th>
                <th>单价</th>
                <th>库存</th>
                <th>最低库存</th>
                <th>最高库存</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${materials}" var="material">
                <tr>
                    <td>${material.id}</td>
                    <td>${material.name}</td>
                    <td>${material.specs}</td>
                    <td>${material.manufacturer}</td>
                    <td>${material.price}</td>
                    <td ${material.stock < material.minStock ? 'style="color:red"' : ''}>${material.stock}</td>
                    <td>${material.minStock}</td>
                    <td>${material.maxStock}</td>
                    <td>${material.createTime}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/material/view?id=${material.id}" class="btn btn-primary">查看</a>
                        <a href="${pageContext.request.contextPath}/material/edit?id=${material.id}" class="btn btn-warning">编辑</a>
                        <a href="${pageContext.request.contextPath}/material/delete?id=${material.id}" class="btn btn-danger" onclick="return confirm('确定要删除吗?')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <a href="#">上一页</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">下一页</a>
        </div>
    </div>
</div>
</body>
</html>