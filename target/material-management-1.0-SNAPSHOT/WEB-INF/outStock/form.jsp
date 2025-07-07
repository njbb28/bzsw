<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增出库记录 - 企业物资管理系统</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; }
        .header { background-color: #2c3e50; color: white; padding: 15px; }
        .container { width: 90%; max-width: 800px; margin: 20px auto; }
        .card { background-color: white; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); padding: 20px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input, .form-group select, .form-group textarea {
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
        <h2>新增出库记录</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/outStock/add" method="post">
            <div class="form-group">
                <label>物资</label>
                <select name="materialId" required>
                    <option value="">请选择物资</option>
                    <c:forEach items="${materials}" var="material">
                        <option value="${material.id}">${material.name} (库存: ${material.stock})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>数量</label>
                <input type="number" name="quantity" min="1" required>
            </div>
            <div class="form-group">
                <label>出库日期</label>
                <input type="date" name="outDate" value="${defaultDate}" required>
            </div>
            <div class="form-group">
                <label>申请部门</label>
                <select name="deptId" required>
                    <option value="">请选择部门</option>
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>申请人</label>
                <select name="requesterId" required>
                    <option value="">请选择申请人</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}">${user.realName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>库管员</label>
                <select name="keeperId" required>
                    <option value="">请选择库管员</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}">${user.realName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>备注</label>
                <textarea name="remark" rows="3"></textarea>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">提交</button>
                <a href="${pageContext.request.contextPath}/outStock/list" class="btn btn-danger">取消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>