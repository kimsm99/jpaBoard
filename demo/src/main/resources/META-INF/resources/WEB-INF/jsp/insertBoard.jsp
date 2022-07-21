<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
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
    <h1 style = "margin-down : 50px">글쓰기</h1>
</div>

<c:set var="id" value="${board.id}" />

<c:choose>
    <c:when test="${empty id}">
        <form action="insertBoard" method="post">

            <div>
                <td colspan="2" align="center">
                    <input style="margin:10px;  margin-left:80% " type="submit" value="등록"/>
                </td>
            </div>

            <table style="width: 90%; margin: auto">


                <tr>
                    <td width="70" style="background-color: #c5d4d4; color: black">제목</td>
                    <td><input style="width:95%" type="text" name="title"/></td>
                </tr>

                <tr>
                    <td style="background-color: #c5d4d4; color: black">내용</td>
                    <td><textarea style="width:95%" name="content" cols="40" rows="10"></textarea></td>
                </tr>
                <input style="width:95%" type="text" name="writer" value="${sessionScope.loginUser.nickName}"/>


            </table>
        </form>
    </c:when>

    <c:otherwise>
            <form action="<c:url value='/updateBoard?id=${board.id}' />" method="post">

                <div>
                    <td colspan="2" align="center">
                        <input style="margin:10px;  margin-left:80% " type="submit" value="수정"/>
                    </td>
                </div>
                <table style="width: 90%; margin: auto">
                    <tr>
                        <td width="70" style="background-color: #c5d4d4; color: black">제목</td>
                        <td><input style="width:95%" type="text" name="title" value = "${board.title}"/></td>
                    </tr>

                    <tr>
                        <td style="background-color: #c5d4d4; color: black">내용</td>
                        <td>
                            <textarea style="width:95%" name="content" cols="40" rows="10">${board.content}</textarea>
                        </td>
                    </tr>

                </table>
            </form>
    </c:otherwise>
</c:choose>

</div>
</body>
</html>