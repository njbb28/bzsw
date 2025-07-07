<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>物资列表</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        table, th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; text-align: left; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .action-btn { padding: 5px 10px; margin-right: 5px; }
    </style>
</head>
<body>
<h1>物资列表</h1>

<c:if test="${sessionScope.user.role == 'admin'}">
    <a href="<%= request.getContextPath() %>/material/form" class="action-btn">添加物资</a>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>名称</th>
        <th>规格</th>
        <th>生产厂家</th>
        <th>价格</th>
        <th>库存</th>
        <th>最低库存</th>
        <th>最高库存</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${materials}" var="material">
        <tr>
            <td>${material.id}</td>
            <td>${material.name}</td>
            <td>${material.specs}</td>
            <td>${material.manufacturer}</td>
            <td>${material.price}</td>
            <td>${material.stock}</td>
            <td>${material.minStock}</td>
            <td>${material.maxStock}</td>
            <td>${material.createTime}</td>
            <td>
                <a href="<%= request.getContextPath() %>/material/view?id=${material.id}" class="action-btn">查看</a>
                <c:if test="${sessionScope.user.role == 'admin'}">
                    <a href="<%= request.getContextPath() %>/material/edit?id=${material.id}" class="action-btn">编辑</a>
                    <a href="<%= request.getContextPath() %>/material/delete?id=${material.id}" class="action-btn" onclick="return confirm('确定要删除此物资吗？')">删除</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>