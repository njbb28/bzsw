<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>供应商列表</title>
</head>
<body>
<h1>供应商列表</h1>
<a href="${pageContext.request.contextPath}/supplier/form">添加供应商</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>供应商名称</th>
        <th>联系人</th>
        <th>电话</th>
        <th>地址</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${suppliers}" var="supplier">
        <tr>
            <td>${supplier.supplierId}</td>
            <td>${supplier.supplierName}</td>
            <td>${supplier.contactName}</td>
            <td>${supplier.phone}</td>
            <td>${supplier.address}</td>
            <td>${supplier.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/supplier/edit?id=${supplier.supplierId}">编辑</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>