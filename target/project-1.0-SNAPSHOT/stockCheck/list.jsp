<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理系统 - 库存盘点</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center mt-4">企业物资管理系统</h1>
    <div class="row mt-4">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3>库存盘点记录</h3>
                </div>
                <div class="card-body">
                    <a href="<%=request.getContextPath()%>/stockCheck/form" class="btn btn-success mb-2">新增盘点</a>
                    <table class="table table-striped table-bordered">
                        <thead class="bg-light">
                        <tr>
                            <th>盘点单号</th>
                            <th>物资名称</th>
                            <th>仓库</th>
                            <th>实际库存</th>
                            <th>系统库存</th>
                            <th>差异</th>
                            <th>状态</th>
                            <th>盘点日期</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${checks}" var="check">
                            <tr>
                                <td>${check.checkNo}</td>
                                <td>${check.materialName}</td>
                                <td>${check.warehouseName}</td>
                                <td>${check.actualQuantity}</td>
                                <td>${check.systemQuantity}</td>
                                <td>${check.difference}</td>
                                <td>${check.status}</td>
                                <td>${check.checkDate}</td>
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