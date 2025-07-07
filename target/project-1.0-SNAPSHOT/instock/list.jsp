<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入库列表</title>
</head>
<body>
<h1>入库列表</h1>
<a href="${pageContext.request.contextPath}/inStock/form">添加入库记录</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>物资名称</th>
        <th>供应商名称</th>
        <th>数量</th>
        <th>价格</th>
        <th>入库日期</th>
        <th>经手人</th>
        <th>保管人</th>
    </tr>
    <c:forEach items="${inStocks}" var="inStock">
        <tr>
            <td>${inStock.id}</td>
            <td>${inStock.materialName}</td>
            <td>${inStock.supplierName}</td>
            <td>${inStock.quantity}</td>
            <td>${inStock.price}</td>
            <td>${inStock.inDate}</td>
            <td>${inStock.handler}</td>
            <td>${inStock.keeper}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>