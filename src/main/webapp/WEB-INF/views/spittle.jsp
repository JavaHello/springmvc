<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luokai
  Date: 17-6-21
  Time: 上午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Spittle
<c:forEach items="${spittleList}" var="item">
    ${item.message} &nbsp; ${item.time}<br>
</c:forEach>
</body>
</html>
