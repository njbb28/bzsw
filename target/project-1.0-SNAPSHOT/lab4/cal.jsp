<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<!-- 表单提交至 /lab4/CalServlet（与Servlet映射路径一致） -->
<form action="CalServlet" method="post">
    第一个数：<input type="text" name="firstVal"><br>
    第二个数：<input type="text" name="secondVal"><br>
    姓　　名：<input type="text" name="name"><br>
    <input type="submit" name="action" value="Add">
    <input type="submit" name="action" value="Subtract">
    <input type="submit" name="action" value="Multiply">
    <input type="submit" name="action" value="Divide">
    <input type="submit" name="action" value="ComputeLength"><br>
</form>

<!-- 读取Cookie中的结果 -->
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("result".equals(cookie.getName())) {
                out.println("结果：" + cookie.getValue());
            }
        }
    }
%>
</body>
</html>