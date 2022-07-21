<%@page contentType="text/html; charset=UTF-8" %>
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


    <title>회원가입</title>
    <style>

    </style>

    <script>
        	function pwcheck1() {

        		var password = document.getElementById('pw');//비밀번호
        		var passwordcheck = document.getElementById('pwcheck');	//비밀번호 확인 값

        		if(!password.value){
        		    alert("비밀번호를 입력해주세요.");
        		}

        		if(password.value == passwordcheck.value){//password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
        			alert("비밀번호 일치");
        		}
        		else{
        		    alert("비밀번호가 일치하지 않습니다.");
        		}
        	}
        </script>

</head>
<body>
    <div style="width: 100%; height: 180px; background-color: #c5d4d4">
        <button onclick="location.href='/boardView'" style="margin:10px;  margin-left:80% "> x </button>
        <h1 style = "margin-left:46%">회원가입</h1>
    </div>

<form action="<c:url value='/signup'/>" method="post">
    <div style="width:40%; margin: 80px auto">
        <div class="input-group mb-3">
            <span class="input-group-text" id="inputGroup-sizing-default">이름</span>
            <input type="text" name="username" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group mb-3">
          <span class="input-group-text" id="inputGroup-sizing-default">아이디</span>
          <input type="text" name="nickName" class="form-control" placeholder="아이디를 입력해주세요" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="inputGroup-sizing-default">비밀번호</span>
            <input id="pw" type="password" name="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="input-group mb-3">
          <input id="pwcheck" type="password" name="passwordCheck" class="form-control" placeholder="비밀번호 확인" aria-label="Recipient's username" aria-describedby="button-addon2">
          <button onclick="pwcheck1();" class="btn btn-outline-secondary" type="button" id="button-addon2">PW 확인</button>
        </div>

         <input style="margin:10px;  margin-left:80% " type="submit" value="등록"/>
         <h2 style="color:red; margin:auto">${errortext}</h2>
    </div>
</form>



</body>
</html>

