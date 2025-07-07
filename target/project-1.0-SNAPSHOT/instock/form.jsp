<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入库表单</title>
</head>
<body>
<h1>入库表单</h1>
<form action="${pageContext.request.contextPath}/inStock/add" method="post">
    <label for="materialId">物资:</label>
    <select id="materialId" name="materialId">
        <c:forEach items="${materials}" var="material">
            <option value="${material.id}">${material.name}</option>
        </c:forEach>
    </select><br>
    <label for="supplierId">供应商:</label>
    <select id="supplierId" name="supplierId">
        <c:forEach items="${suppliers}" var="supplier">
            <option value="${supplier.supplierId}">${supplier.supplierName}</option>
        </c:forEach>
    </select><br>
    <label for="quantity">数量:</label>
    <input type="text" id="quantity" name="quantity"><br>
    <label for="price">价格:</label>
    <input type="text" id="price" name="price"><br>
    <label for="inDate">入库日期:</label>
    <input type="text" id="inDate" name="inDate" placeholder="yyyy-MM-dd"><br>
    <label for="handler">经手人:</label>
    <input type="text" id="handler" name="handler"><br>
    <label for="keeper">保管人:</label>
    <input type="text" id="keeper" name="keeper"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>