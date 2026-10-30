<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>录入商品信息</title>
    <style>
        form { width: 420px; margin: 30px auto; }
        .item { margin: 12px 0; }
        label { display: inline-block; width: 90px; }
        input, textarea { width: 260px; padding: 4px; box-sizing: border-box; }
        textarea { height: 100px; }
        button { padding: 6px 16px; border: none; color: white; border-radius: 4px; cursor: pointer; }
        .btn-add { background-color: #28a745; }
        .btn-reset { background-color: #007bff; margin-left: 8px; }
        .back { display: block; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
<h2 style="text-align:center">录入商品信息</h2>
<form action="${pageContext.request.contextPath}/saveProduct" method="post">
    <div class="item">
        <label>商品名称:</label>
        <input type="text" name="name" required>
    </div>
    <div class="item">
        <label>商品价格:</label>
        <input type="number" step="0.01" min="0" name="price" value="0.00" required>
    </div>
    <div class="item">
        <label>商品编号:</label>
        <input type="text" name="code">
    </div>
    <div class="item">
        <label>上市日期:</label>
        <input type="date" name="saleDate">
    </div>
    <div class="item">
        <label>商品介绍:</label>
        <textarea name="description"></textarea>
    </div>
    <div class="item" style="text-align:center">
        <button type="submit" class="btn-add">添加</button>
        <button type="reset" class="btn-reset">重置</button>
    </div>
</form>
<a class="back" href="${pageContext.request.contextPath}/productList">查看所有商品</a>
<a class="back" href="${pageContext.request.contextPath}/">返回首页</a>
</body>
</html>
