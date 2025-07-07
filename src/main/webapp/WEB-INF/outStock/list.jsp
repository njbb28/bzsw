<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>出库记录 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 1200px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; text-align: left; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #f5f5f5; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 3px; cursor: pointer; }
        .btn-primary { background-color: #3498db; color: white; }
        .btn-success { background-color: #2ecc71; color: white; }
        .search-box { margin-bottom: 20px; }
        .search-box input, .search-box select { padding: 8px; border: 1px solid #ddd; border-radius: 3px; margin-right: 10px; }
        .search-box button { padding: 8px 12px; background-color: #3498db; color: white; border: none; border-radius: 3px; cursor: pointer; }
        .pagination { margin-top: 20px; }
        .pagination a { padding: 8px 12px; text-decoration: none; border: 1px solid #ddd; margin: 0 4px; }
        .pagination a.active { background-color: #3498db; color: white; }
    </style>
</head>
<body>
<div class="header">
    <h1>企业物资管理系统</h1>
</div>
<div class="container">
    <div class="card">
        <h2>出库记录</h2>
        <div class="search-box">
            <form action="${pageContext.request.contextPath}/outStock/list" method="get">
                <select name="materialId">
                    <option value="">选择物资</option>
                    <c:forEach items="${materials}" var="material">
                        <option value="${material.id}">${material.name}</option>
                    </c:forEach>
                </select>
                <select name="deptId">
                    <option value="">选择部门</option>
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.deptName}</option>
                    </c:forEach>
                </select>
                <input type="date" name="startDate">
                <button type="submit">搜索</button>
            </form>
        </div>
        <a href="${pageContext.request.contextPath}/outStock/form" class="btn btn-success">新增出库</a>
        <table>
            <thead>
            <tr>
                <th>出库单号</th>
                <th>物资名称</th>
                <th>数量</th>
                <th>出库日期</th>
                <th>申请部门</th>
                <th>申请人</th>
                <th>库管员</th>
                <th>备注</th>
                <th>操作</th>
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
                    <td>${outStock.remark}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/outStock/view?id=${outStock.id}" class="btn btn-primary">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <a href="#">上一页</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">下一页</a>
        </div>
    </div>
</div>
</body>
</html>