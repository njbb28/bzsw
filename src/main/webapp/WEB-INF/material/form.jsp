<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${material == null ? '新增' : '编辑'}物资 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 800px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input, .form-group textarea, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 3px; cursor: pointer; border: none; }
        .btn-primary { background-color: #3498db; color: white; }
        .btn-danger { background-color: #e74c3c; color: white; }
        .btn-group { margin-top: 20px; }
        .alert { padding: 15px; margin-bottom: 20px; border: 1px solid transparent; border-radius: 4px; }
        .alert-danger { color: #a94442; background-color: #f2dede; border-color: #ebccd1; }
    </style>
</head>
<body>
<div class="header">
    <h1>企业物资管理系统</h1>
</div>
<div class="container">
    <div class="card">
        <h2>${material == null ? '新增' : '编辑'}物资</h2>
        <form action="${pageContext.request.contextPath}/material/${material == null ? 'add' : 'update'}" method="post">
            <c:if test="${not empty material}">
                <input type="hidden" name="id" value="${material.id}">
            </c:if>
            <div class="form-group">
                <label>物资名称</label>
                <input type="text" name="name" value="${material.name}" required>
            </div>
            <div class="form-group">
                <label>规格型号</label>
                <input type="text" name="specs" value="${material.specs}" required>
            </div>
            <div class="form-group">
                <label>生产厂家</label>
                <input type="text" name="manufacturer" value="${material.manufacturer}" required>
            </div>
            <div class="form-group">
                <label>单价</label>
                <input type="number" name="price" step="0.01" value="${material.price}" required>
            </div>
            <div class="form-group">
                <label>库存</label>
                <input type="number" name="stock" value="${material.stock}" required>
            </div>
            <div class="form-group">
                <label>最低库存</label>
                <input type="number" name="minStock" value="${material.minStock}" required>
            </div>
            <div class="form-group">
                <label>最高库存</label>
                <input type="number" name="maxStock" value="${material.maxStock}" required>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">保存</button>
                <a href="${pageContext.request.contextPath}/material/list" class="btn btn-danger">取消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>