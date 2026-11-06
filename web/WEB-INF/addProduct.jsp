<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>录入商品信息</title>
    <style>
        form { width: 460px; margin: 30px auto; }
        .item { margin: 12px 0; }
        label { display: inline-block; width: 90px; vertical-align: top; }
        input, textarea { width: 260px; padding: 4px; box-sizing: border-box; }
        textarea { height: 100px; }
        button { padding: 6px 16px; border: none; color: white; border-radius: 4px; cursor: pointer; }
        .btn-add { background-color: #28a745; }
        .btn-reset { background-color: #007bff; margin-left: 8px; }
        .back { display: block; text-align: center; margin-top: 10px; }
        /* 错误提示：独占一行，在输入框下方，红色，缩进对齐输入框 */
        .error {
            display: block;
            margin-left: 94px;
            margin-top:4px;
            color: #dc3545;
            font-size: 12px;
        }
    </style>
</head>
<body>
<h2 style="text-align:center">录入商品信息</h2>
<form:form modelAttribute="product"
           action="${pageContext.request.contextPath}/saveProduct" method="post">
    <div class="item">
        <label for="name">商品名称:</label>
        <form:input path="name" id="name"/>
            <%--错误：输入框下方红字--%>
        <form:errors path="name" cssClass="error"/>
    </div>
    <div class="item">
        <label for="price">商品价格:</label>
            <%--移除原生required，交给后端@DecimalMin校验；保留number与step方便输入--%>
        <form:input path="price" id="price" type="number" step="0.01"/>
        <form:errors path="price" cssClass="error"/>
    </div>
    <div class="item">
        <label for="code">商品编号:</label>
        <form:input path="code" id="code" placeholder="如：SKU-202610-0001"/>
        <form:errors path="code" cssClass="error"/>
    </div>
    <div class="item">
        <label for="saleDate">上市日期:</label>
        <form:input path="saleDate" id="saleDate" type="date"/>
        <form:errors path="saleDate" cssClass="error"/>
    </div>
    <div class="item">
        <label for="description">商品介绍:</label>
        <form:textarea path="description" id="description"/>
        <form:errors path="description" cssClass="error"/>
    </div>
    <div class="item" style="text-align:center">
        <button type="submit" class="btn-add">添加</button>
        <button type="reset" class="btn-reset">重置</button>
    </div>
</form:form>
<a class="back" href="${pageContext.request.contextPath}/productList">查看所有商品</a>
<a class="back" href="${pageContext.request.contextPath}/">返回首页</a>
</body>
</html>
