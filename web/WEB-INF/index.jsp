<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        body { text-align: center; margin-top: 100px; font-family: "Microsoft YaHei", Arial, sans-serif; }
        a { text-decoration: none; color: #007bff; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<h2><a href="${pageContext.request.contextPath}/addProduct">录入商品信息</a></h2>
<br/>
<h2><a href="${pageContext.request.contextPath}/productList">查看所有商品</a></h2>
</body>
</html>
