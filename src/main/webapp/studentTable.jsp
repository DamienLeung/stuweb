<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dfbz.service.StudentService" %>
<%@ page import="com.dfbz.service.impl.StudentServiceImpl" %>
<%@ page import="com.dfbz.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.4.1.min.js"></script>
    <style>
        .h1 {
            text-align: center;
        }

        .banner > td {
            font-weight: 600;
            font-size: 18px;
        }

        td:last-child {
            width: 150px;
        }

        .info {
            font-weight: 700;
            color: red;
            display: none;
        }

        .info2 {
            font-weight: 600;
            color: red;
            display: none;
        }

    </style>
    <title>Student Table</title>
</head>
<body>
<%
    StudentService service = new StudentServiceImpl();
    List<Student> list = service.getListById();
    pageContext.setAttribute("stuList", list);
%>
<div class="container">
    <p class="h1">學生信息</p>
    <form action="<c:url value="/student/dior"/>" method="post">
        <table class="table table-hover">
            <tr class="banner">
                <td><input type="checkbox" class="selectAll" ></td>
                <td>學號</td>
                <td>姓名</td>
                <td>聯繫方式</td>
                <td>家庭住址</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${pageScope.stuList}" var="student">
                <tr>
                    <td><input type="checkbox" class="select" name="id" value="${student.id}"></td>
                    <td>${student.id}</td>
                    <td>${student.studentN}</td>
                    <td>${student.phone}</td>
                    <td>${student.addr}</td>
                    <td><input type="button" class="btn btn-primary update" value="複製">
                        <input type="button" class="btn btn-danger delete" value="刪除"></td>
                </tr>
            </c:forEach>

        </table>
        <br>

        <button class="btn btn-danger" id="delete" type="submit" disabled>批量刪除</button>
        <button class="btn btn-primary" id="refresh" type="button">刷新表格</button>
    </form>
    <br>
    <form action="<c:url value="/student/addTo"/>" class="update" method="post">
        <div class="form-group form-inline">
            <input type="text" class="form-control id" placeholder="學號" name="id">
            <input type="text" class="form-control name" placeholder="姓名" name="studentN">
            <input type="text" class="form-control phone" placeholder="聯繫方式" name="phone">
            <input type="text" class="form-control addr" placeholder="家庭住址" name="addr">
            <input class="btn btn-primary" type="submit" id="update" value="提交" disabled>
        </div>
    </form>
    <label class="info h4">請按格式輸入學號（第一位數字不能爲0）！</label>
    <label class="info2 h4">請輸入正確的電話號碼格式！</label>
    <br>

</div>
<script src="js/tableJs.js"></script>
</body>
</html>