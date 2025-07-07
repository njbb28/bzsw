<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.lab1.Dog, com.example.lab1.Person" %>

<%
    // 创建 Dog 和 Person 实例，存入 request 作用域
    Dog myDog = new Dog("Buddy", 3); // 示例数据
    Person person = new Person("Alice", myDog);
    request.setAttribute("person", person);

    // 创建 Dog 数组（模拟集合数据）
    Dog[] dogList = {
            new Dog("Max", 5),
            new Dog("Lucy", 2),
            new Dog("Charlie", 4)
    };
    request.setAttribute("dogList", dogList);
%>

<html>
<head>
    <title>Dog Bean 访问演示</title>
    <style>
        body { font-family: Arial, sans-serif; }
        h2 { color: #2c3e50; }
        ul { list-style: none; padding: 0; }
        li { margin: 5px 0; }
    </style>
</head>
<body>
<h2>EL 表达式访问嵌套属性</h2>
<p>
    主人：${person.name} <br>
    宠物狗：${person.dog.name}（年龄：${person.dog.age} 岁）
</p>

<h2>JSTL 遍历 Dog 集合</h2>
<ul>
    <c:forEach var="dog" items="${dogList}">
        <li>狗名：${dog.name}，年龄：${dog.age} 岁</li>
    </c:forEach>
</ul>
</body>
</html>