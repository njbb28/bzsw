<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入库管理 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 1200px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; text-align: left; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        a { color: #3498db; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .btn { background-color: #3498db; color: white; padding: 8px 15px; border-radius: 4px; text-decoration: none; display: inline-block; }
        .btn:hover { background-color: #2980b9; }
    </style>
</head>
<body>
<div class="header">
    <div class="container">
        <h1>企业物资管理系统</h1>
    </div>
</div>

<div class="container">
    <h2>入库记录管理</h2>
    <a href="${pageContext.request.contextPath}/inStock/form" class="btn">添加入库记录</a>

    <div class="card">
        <table>
            <tr>
                <th>入库单号</th>
                <th>物资名称</th>
                <th>供应商</th>
                <th>数量</th>
                <th>单价</th>
                <th>入库日期</th>
                <th>经手人</th>
                <th>保管人</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${inStocks}" var="inStock">
                <tr>
                    <td>${inStock.inStockNo}</td>
                    <td>${inStock.materialName}</td>
                    <td>${inStock.supplierName}</td>
                    <td>${inStock.quantity}</td>
                    <td>${inStock.price}</td>
                    <td>${inStock.inDate}</td>
                    <td>${inStock.handler}</td>
                    <td>${inStock.keeper}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/inStock/delete?id=${inStock.id}"
                           onclick="return confirm('确定要删除这条入库记录吗？')">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>