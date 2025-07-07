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
        form { margin-top: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="number"], input[type="date"], select, textarea {
            width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box;
        }
        textarea { height: 100px; }
        .btn { background-color: #3498db; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn:hover { background-color: #2980b9; }
        .required { color: red; }
    </style>
</head>
<body>
<div class="header">
    <div class="container">
        <h1>企业物资管理系统</h1>
    </div>
</div>

<div class="container">
    <h2>${empty plan.id ? '新增采购计划' : '编辑采购计划'}</h2>
    <a href="${pageContext.request.contextPath}/purchasePlan/list" class="btn">返回列表</a>

    <div class="card">
        <form action="${pageContext.request.contextPath}/purchasePlan/${empty plan.id ? 'add' : 'update'}" method="post">
            <input type="hidden" name="id" value="${plan.id}">

            <div class="form-group">
                <label for="materialId">物资名称 <span class="required">*</span></label>
                <select id="materialId" name="materialId" required>
                    <option value="">请选择物资</option>
                    <c:forEach items="${materials}" var="material">
                        <option value="${material.id}" ${plan.materialId == material.id ? 'selected' : ''}>${material.name} (规格: ${material.specs}, 库存: ${material.stock})</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="requiredQuantity">需求数量 <span class="required">*</span></label>
                <input type="number" id="requiredQuantity" name="requiredQuantity" min="1"
                       value="${plan.requiredQuantity}" required>
            </div>

            <div class="form-group">
                <label for="planDate">计划日期 <span class="required">*</span></label>
                <input type="date" id="planDate" name="planDate"
                       value="${not empty plan.planDate ? plan.planDate : ''}" required>
            </div>

            <div class="form-group">
                <label for="status">状态</label>
                <select id="status" name="status">
                    <option value="未处理" ${plan.status == '未处理' ? 'selected' : ''}>未处理</option>
                    <option value="已批准" ${plan.status == '已批准' ? 'selected' : ''}>已批准</option>
                    <option value="已拒绝" ${plan.status == '已拒绝' ? 'selected' : ''}>已拒绝</option>
                    <option value="已执行" ${plan.status == '已执行' ? 'selected' : ''}>已执行</option>
                </select>
            </div>

            <div class="form-group">
                <label for="remark">备注</label>
                <textarea id="remark" name="remark">${plan.remark}</textarea>
            </div>

            <button type="submit" class="btn">保存</button>
        </form>
    </div>
</div>
</body>
</html>