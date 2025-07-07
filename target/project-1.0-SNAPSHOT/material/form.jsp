<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资表单</title>
</head>
<body>
<h1>物资表单</h1>
<form action="${pageContext.request.contextPath}/material/update" method="post">
    <input type="hidden" name="id" value="${material.id}">
    <label for="name">名称:</label>
    <input type="text" id="name" name="name" value="${material.name}"><br>
    <label for="specs">规格:</label>
    <input type="text" id="specs" name="specs" value="${material.specs}"><br>
    <label for="manufacturer">制造商:</label>
    <input type="text" id="manufacturer" name="manufacturer" value="${material.manufacturer}"><br>
    <label for="price">价格:</label>
    <input type="text" id="price" name="price" value="${material.price}"><br>
    <label for="minStock">最小库存:</label>
    <input type="text" id="minStock" name="minStock" value="${material.minStock}"><br>
    <label for="maxStock">最大库存:</label>
    <input type="text" id="maxStock" name="maxStock" value="${material.maxStock}"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>