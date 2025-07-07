<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL 格式化 Demo</title>
</head>
<body>
<%-- 格式化数字 --%>
<c:set var="price" value="9500.60"/>
<p>价格（货币格式）：<fmt:formatNumber value="${price}" type="currency" currencyCode="CNY"/></p>
<p>百分比：<fmt:formatNumber value="0.85" type="percent" minFractionDigits="2"/></p>

<%-- 格式化日期 --%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>