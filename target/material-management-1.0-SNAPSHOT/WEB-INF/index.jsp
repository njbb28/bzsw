<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 1200px; margin: 20px auto; }
        .dashboard { display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; text-align: center; }
        .card h3 { color: #2c3e50; margin-top: 0; }
        .card p { color: #7f8c8d; margin-bottom: 20px; }
        .card a { background-color: #3498db; color: white; text-decoration: none; padding: 10px 15px; border-radius: 5px; display: inline-block; }
        .card a:hover { background-color: #2980b9; }
        .stats { display: grid; grid-template-columns: repeat(4, 1fr); gap: 15px; margin-top: 30px; }
        .stat-card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 15px; text-align: center; }
        .stat-value { font-size: 24px; font-weight: bold; color: #2c3e50; }
        .stat-label { font-size: 14px; color: #7f8c8d; }
        .error-message { color: red; font-weight: bold; margin: 20px 0; }
    </style>
</head>
<body>
<div class="header">
    <div class="container">
        <h1>企业物资管理系统</h1>
    </div>
</div>

<c:if test="${not empty error}">
    <div class="container">
        <div class="error-message">${error}</div>
    </div>
</c:if>

<div class="container">
    <div class="dashboard">
        <div class="card">
            <h3>物资管理</h3>
            <p>管理企业所有物资信息，包括新增、编辑和删除</p>
            <a href="${pageContext.request.contextPath}/material/list">进入管理</a>
        </div>
        <div class="card">
            <h3>入库管理</h3>
            <p>处理物资入库登记，更新库存数量</p>
            <a href="${pageContext.request.contextPath}/inStock/list">进入管理</a>
        </div>
        <div class="card">
            <h3>出库管理</h3>
            <p>处理物资出库登记，校验库存并更新</p>
            <a href="${pageContext.request.contextPath}/outStock/list">进入管理</a>
        </div>
        <div class="card">
            <h3>采购计划</h3>
            <p>根据库存情况生成和管理采购计划</p>
            <a href="${pageContext.request.contextPath}/purchasePlan/list">进入管理</a>
        </div>
        <div class="card">
            <h3>部门管理</h3>
            <p>管理部门基础信息</p>
            <a href="${pageContext.request.contextPath}/baseData/list">进入管理</a>
        </div>
    </div>

    <div class="stats">
        <div class="stat-card">
            <div class="stat-value">${materialCount}</div>
            <div class="stat-label">物资种类</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">${monthlyInStockCount}</div>
            <div class="stat-label">本月入库</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">${monthlyOutStockCount}</div>
            <div class="stat-label">本月出库</div>
        </div>
        <div class="stat-card">
            <div class="stat-value">${pendingPlanCount}</div>
            <div class="stat-label">待处理计划</div>
        </div>
    </div>
</div>
</body>
</html>