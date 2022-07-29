<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>


    <title> 메인 페이지</title>
    <style>
        .banner {
            width: 100%;
            height: 180px;
            background-color: #c5d4d4;
            display: flex;
            flex-direction: row;
            padding :20px

            text-align: center;
            align-items: center;
            justify-content: center;

        }
        .title{
            margin: auto;

        }

    </style>

    <script>
        function needLogin(){
            alert("로그인이 필요한 기능입니다.");
        }
    </script>
</head>
<body>
    <div class = "banner">
        <div class = "title">
            <h1>게시판</h1>
        </div>

        <c:choose>
            <c:when test="${!empty sessionScope.loginUser.nickName}">
                <div class = "sign-in">

                    <div>
                        <h2>환영합니다 ${sessionScope.loginUser.nickName}님!</h2>
                    </div>
                    <div>
                    <form action="<c:url value='/logout' />">
                        <input type="submit" class="btn btn-outline-primary" value="로그아웃"/>
                    </form>

                        <button type="button" class="btn btn-success" onclick="location.href='/insertBoardView'">글쓰기</button>

                    </div>
                </div>
            </c:when>

            <c:otherwise>
               <div class = "sign-in">
               <form action="<c:url value='/login' />" method="post">
                   <div>

                       <p>ID: <input type="text" name="nickName"></p>
                       <p>PW: <input type="password" name="password"></p>
                   </div>
                   <div>
                       <input type="submit" class="btn btn-outline-primary"  value="로그인"/>
               </form>
                       <button type="button" class="btn btn-outline-secondary" onclick="location.href='/signupView'">회원가입</button>
                       <button type="button" class="btn btn-success" onclick="needLogin()">글쓰기</button>

                   </div>
               </div>
            </c:otherwise>
        </c:choose>
    </div>


    <c:if test="${!empty param.checkLogin}">
         <c:choose>
            <c:when test="${param.checkLogin eq 1}">
                <script type="text/javascript">
                       alert("아이디가 틀렸습니다.");
                       history.go(-1);
                   </script>
            </c:when>

             <c:when test="${param.checkLogin eq 2}">
                <script type="text/javascript">
                       alert("비밀번호가 틀렸습니다.");
                       history.go(-1);
                  </script>
            </c:when>
        </c:choose>
    </c:if>


    <c:forEach var="board" items="${boardList}">
        <a href="/getDetail?id=${board.id}" class="list-group-item list-group-item-action">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">${board.title}</h5>
                <small><fmt:parseDate value="${board.modifiedAt}" pattern = "yyyy-MM-dd'T'HH:mm" type="both"/>
                <fmt:formatDate pattern = "dd.MM.yyyy HH:mm" value="${parseDateTime}"/></small>
            </div>
            <p class="mb-1">${board.content}</p>
            <small>${board.writer}</small>
        </a>
    </c:forEach>


</body>
</html>






