<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL 函数 Demo</title>
</head>
<body>
<c:set var="str" value="Hello, JSTL! This is a test."/>

<p>字符串长度：${fn:length(str)}</p>
<p>是否包含 "JSTL"：${fn.contains(str, "JSTL") ? "是" : "否"}</p>
<p>替换后的字符串：${fn.replace(str, "test", "demo")}</p>
<p>分割后的数组：
    <c:forEach var="part" items="${fn.split(str, ', ')}">
        [${part}]
    </c:forEach>
</p>
</body>
</html>