<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>供应商表单</title>
</head>
<body>
<h1>供应商表单</h1>
<form action="${pageContext.request.contextPath}/supplier/update" method="post">
    <input type="hidden" name="supplierId" value="${supplier.supplierId}">
    <label for="supplierName">供应商名称:</label>
    <input type="text" id="supplierName" name="supplierName" value="${supplier.supplierName}"><br>
    <label for="contactName">联系人:</label>
    <input type="text" id="contactName" name="contactName" value="${supplier.contactName}"><br>
    <label for="phone">电话:</label>
    <input type="text" id="phone" name="phone" value="${supplier.phone}"><br>
    <label for="address">地址:</label>
    <input type="text" id="address" name="address" value="${supplier.address}"><br>
    <label for="email">邮箱:</label>
    <input type="text" id="email" name="email" value="${supplier.email}"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>