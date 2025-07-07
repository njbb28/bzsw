<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资详情 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 800px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        .detail-group { margin-bottom: 15px; }
        .detail-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .detail-group span { display: block; padding: 8px; border: 1px solid #ddd; border-radius: 3px; background-color: #f9f9f9; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 3px; cursor: pointer; border: none; }
        .btn-primary { background-color: #3498db; color: white; }
        .btn-group { margin-top: 20px; }
    </style>
</head>
<body>
<div class="header">
    <h1>企业物资管理系统</h1>
</div>
<div class="container">
    <div class="card">
        <h2>物资详情</h2>
        <div class="detail-group">
            <label>物资名称</label>
            <span>${material.name}</span>
        </div>
        <div class="detail-group">
            <label>规格型号</label>
            <span>${material.specs}</span>
        </div>
        <div class="detail-group">
            <label>生产厂家</label>
            <span>${material.manufacturer}</span>
        </div>
        <div class="detail-group">
            <label>单价</label>
            <span>${material.price}</span>
        </div>
        <div class="detail-group">
            <label>库存</label>
            <span ${material.stock < material.minStock ? 'style="color:red"' : ''}>${material.stock}</span>
        </div>
        <div class="detail-group">
            <label>最低库存</label>
            <span>${material.minStock}</span>
        </div>
        <div class="detail-group">
            <label>最高库存</label>
            <span>${material.maxStock}</span>
        </div>
        <div class="detail-group">
            <label>创建时间</label>
            <span>${material.createTime}</span>
        </div>
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/material/edit?id=${material.id}" class="btn btn-primary">编辑</a>
            <a href="${pageContext.request.contextPath}/material/list" class="btn btn-primary">返回列表</a>
        </div>
    </div>
</div>
</body>
</html>