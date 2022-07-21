<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>

    <title>상세보기</title>

    <style>
        table {
            width: 100%;
<!--            border: 1px solid #444444;-->
            border-collapse: collapse;
        }

        table td {
                border-radius: 10px;
<!--            border: 1px solid #444444;-->
            text-align: middle;
            padding : 15px;
<!--            background-color:#F0FFFF;-->
        }
    </style>

</head>
<body>
<div style="text-align: center;">

<div style="width: 100%; height: 180px; background-color: #c5d4d4">
    <button onclick="location.href='/boardView'" style="margin:10px;  margin-left:80% "> x </button>
    <h1 style = "margin-down : 50px">상세보기</h1>
</div>
        <div>
            <td colspan="2" align="center">
                <c:if test="${board.writer eq sessionScope.loginUser.nickName}">
                <input onclick="location.href='/updateBoardView?id=${board.id}'" style="margin:10px;  margin-left:80% " type="submit" value="수정"/>
                <input onclick="location.href='/deleteBoard?id=${board.id}'" style="margin:10px;  margin-left:80% " type="submit" value="삭제"/>
                </c:if>>
            </td>
        </div>

        <table style="width: 90%; margin: auto">


            <tr>
                <td width="120" style="background-color: #c5d4d4; color: black">제목</td>
                <td><h3>${board.title}</h3></td>
            </tr>

            <tr>
                <td style="background-color: #c5d4d4; color: black">작성자</td>
                <td><h3>${board.writer}</h3></td>
            </tr>

            <tr>
                <td style="background-color: #c5d4d4; color: black">작성시간</td>
                <td><fmt:parseDate value="${board.modifiedAt}" pattern = "yyyy-MM-dd'T'HH:mm" type="both"/>
                                    <fmt:formatDate pattern = "dd.MM.yyyy HH:mm" value="${parseDateTime}"/></td>
            </tr>

            <tr>
                <td style="background-color: #c5d4d4; color: black">내용</td>
                <td><h3>${board.content}</h3></td>
            </tr>

        </table>

</div>

<div class="input-group mb-3" style = "width:70%; margin:auto">
  <input type="text" class="form-control" placeholder="댓글을 달아주세요" aria-label="Recipient's username" aria-describedby="button-addon2">
  <button class="btn btn-outline-secondary" type="button" id="button-addon2">댓글달기</button>
</div>


<ol class="list-group list-group-numbered" style="width:70%; margin:auto" >
    <li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="ms-2 me-auto">
            <div class="fw-bold">작성자</div>
            댓글내용
        </div>
        <div class="btn-group" role="group" aria-label="Basic outlined example">
          <button type="button" class="btn btn-outline-primary">수정</button>
          <button type="button" class="btn btn-outline-primary">삭제</button>
        </div>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="ms-2 me-auto">
            <div class="fw-bold">Subheading</div>
            Cras justo odio
        </div>
         <div class="btn-group" role="group" aria-label="Basic outlined example">
                  <button type="button" class="btn btn-outline-primary">Left</button>
                  <button type="button" class="btn btn-outline-primary">Right</button>
                </div>
    </li>
    <li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="ms-2 me-auto">
            <div class="fw-bold">Subheading</div>
            Cras justo odio
        </div>
         <div class="btn-group" role="group" aria-label="Basic outlined example">
                  <button type="button" class="btn btn-outline-primary">Left</button>
                  <button type="button" class="btn btn-outline-primary">Right</button>
                </div>
    </li>
</ol>


</body>
</html>