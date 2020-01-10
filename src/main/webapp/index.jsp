<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Document</title>
</head>
<body>
<div class="container">
    <p class="h1">測試</p>
    <form method="post" action="<c:url value="/user/login"/>">
        <div class="form-group">
            帳號：<input type="text" class="form-control" placeholder="請輸入帳號" name="username">
            密碼：<input type="password" class="form-control" placeholder="請輸入密碼" name="psw">
            <br>
            <button class="btn btn-primary" type="submit">登錄</button>

        </div>
    </form>
</div>
</body>
</html>