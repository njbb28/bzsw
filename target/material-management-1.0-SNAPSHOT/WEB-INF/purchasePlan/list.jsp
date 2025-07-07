<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>采购计划 - 企业物资管理系统</title>
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
    <h2>采购计划管理</h2>
    <a href="${pageContext.request.contextPath}/purchasePlan/form" class="btn">新增采购计划</a>

    <div class="card">
        <table>
            <tr>
                <th>计划编号</th>
                <th>物资名称</th>
                <th>需求数量</th>
                <th>计划日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${plans}" var="plan">
                <tr>
                    <td>${plan.planNo}</td>
                    <td>${plan.materialName}</td>
                    <td>${plan.requiredQuantity}</td>
                    <td>${plan.planDate}</td>
                    <td>
                        <span style="color:
                        <c:if test="${plan.status == '未处理'}">red</c:if>
                        <c:if test="${plan.status == '已批准'}">green</c:if>
                        <c:if test="${plan.status == '已拒绝'}">gray</c:if>
                        <c:if test="${plan.status == '已执行'}">blue</c:if>
                                ">${plan.status}</span>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/purchasePlan/view?id=${plan.id}">查看</a>
                        <c:if test="${plan.status == '未处理'}">
                            <a href="${pageContext.request.contextPath}/purchasePlan/approve?id=${plan.id}">批准</a>
                            <a href="${pageContext.request.contextPath}/purchasePlan/reject?id=${plan.id}">拒绝</a>
                        </c:if>
                        <c:if test="${plan.status == '已批准'}">
                            <a href="${pageContext.request.contextPath}/purchasePlan/form?action=edit&id=${plan.id}">编辑</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>