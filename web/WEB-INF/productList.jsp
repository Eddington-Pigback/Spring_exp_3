<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <style>
        table { border-collapse: collapse; width: 90%; margin: 20px auto; }
        th, td { border: 1px solid #999; padding: 8px; text-align: center; }
        h2 { margin-left: 5%; }
        .bar { margin-left: 5%; }
        .bar a { color: #007bff; text-decoration: none; margin-right: 18px; }
        .message { width: 90%; margin: 10px auto; padding: 10px; background: #e8f7ee;
                   border: 1px solid #b7e4c7; color: #1e7e34; text-align: center; }
        .empty { text-align: center; color: #888; padding: 20px; }
    </style>
</head>
<body>
<h2>商品列表</h2>
<div class="bar">
    <a href="${pageContext.request.contextPath}/addProduct">继续录入</a>
    <a href="${pageContext.request.contextPath}/">返回首页</a>
</div>

<c:if test="${not empty message}">
    <div class="message">${message}</div>
</c:if>

<table>
    <thead>
    <tr>
        <th>商品ID</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品编号</th>
        <th>上市日期</th>
        <th>商品介绍</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="prod">
        <tr>
            <td>${prod.id}</td>
            <td><c:out value="${prod.name}"/></td>
            <td>¥<fmt:formatNumber value="${prod.price}" type="number"
                                   minFractionDigits="2" maxFractionDigits="2"/></td>
            <td><c:out value="${prod.code}"/></td>
            <td><fmt:formatDate value="${prod.saleDate}" pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${prod.description}"/></td>
        </tr>
    </c:forEach>
    <c:if test="${empty productList}">
        <tr>
            <td class="empty" colspan="6">暂无商品，请先录入商品信息。</td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>
