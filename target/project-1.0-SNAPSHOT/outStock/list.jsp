<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理系统 - 出库记录</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center mt-4">企业物资管理系统</h1>
    <div class="row mt-4">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3>出库记录列表</h3>
                </div>
                <div class="card-body">
                    <a href="<%=request.getContextPath()%>/outStock/form" class="btn btn-success mb-2">登记出库</a>
                    <table class="table table-striped table-bordered">
                        <thead class="bg-light">
                        <tr>
                            <th>出库单号</th>
                            <th>物资名称</th>
                            <th>出库数量</th>
                            <th>出库日期</th>
                            <th>领料部门</th>
                            <th>领料人</th>
                            <th>库管员</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${outStocks}" var="outStock">
                            <tr>
                                <td>${outStock.outStockNo}</td>
                                <td>${outStock.materialName}</td>
                                <td>${outStock.quantity}</td>
                                <td>${outStock.outDate}</td>
                                <td>${outStock.deptName}</td>
                                <td>${outStock.requesterName}</td>
                                <td>${outStock.keeperName}</td>
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