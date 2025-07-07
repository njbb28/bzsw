<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理系统 - 采购计划</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center mt-4">企业物资管理系统</h1>
    <div class="row mt-4">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3>采购计划列表</h3>
                </div>
                <div class="card-body">
                    <a href="<%=request.getContextPath()%>/purchasePlan/generate" class="btn btn-success mb-2">生成采购计划</a>
                    <table class="table table-striped table-bordered">
                        <thead class="bg-light">
                        <tr>
                            <th>计划编号</th>
                            <th>物资名称</th>
                            <th>需求数量</th>
                            <th>计划日期</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${plans}" var="plan">
                            <tr>
                                <td>${plan.planNo}</td>
                                <td>${plan.materialName}</td>
                                <td>${plan.requiredQuantity}</td>
                                <td>${plan.planDate}</td>
                                <td>${plan.status}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-sm">详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>