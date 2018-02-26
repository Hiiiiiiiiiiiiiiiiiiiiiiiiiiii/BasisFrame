<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form action="/get" method="post">
        <input name="number" type="text">
        <button>点击查询</button>
    </form>
    <div>
        <c:if test="${product==null}">
            无
        </c:if>
        ${product.productName}
        ${product.price}
        ${product.marketPrice}
    </div>
</body>
</html>