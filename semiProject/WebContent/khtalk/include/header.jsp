<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dobble Social Network</title>
<!-- Bootstrap core CSS -->
<link href="/semiProject/khtalk/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/semiProject/khtalk/css/style.css" rel="stylesheet">
<link href="/semiProject/khtalk/css/font-awesome.css" rel="stylesheet">
<link href="/open-iconic/font/css/open-iconic-bootstrap.css" rel="stylesheet">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiProject/khtalk/js/index.js"></script>
<style type="text/css">
#navbar {
	background-color: #142F62;
}

#active {
	background-color: #385DA1;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<body>
	<header>
		<div class="container">
			<a href="index"> <img src="/semiProject/khtalk/img/khtalk_logo_reverse.png"
				class="logo" alt="">
			</a>
			<form class="form-inline" method="post">
				<!-- 로그인되면 안보여줌 -->
				<c:choose>
					<c:when test="${empty sessionScope.userId}">
						<div class="form-group">
							<label class="sr-only" for="exampleInputEmail3">User Id</label> <input
								type="text" class="form-control" id="userId" name="userId"
								placeholder="Enter UserId">

						</div>
						<div class="form-group">
							<label class="sr-only" for="exampleInputPassword3">Password</label>
							<input type="password" class="form-control" id="userPassword"
								name="userPassword" placeholder="Enter Password">
						</div>
						<button type="submit" class="btn btn-default">Sign in</button>
						<button type="button" class="btn btn-default" id="signUp"
							name="signUp">Sign up</button>
						<br />
					</c:when>
					<c:otherwise>
						<div class="form-group">
							<h1>${sessionScope.userName}님&nbsp;</h1>
						</div>
						<div class="form-group">
							<h2>환영합니다.</h2>
						</div>
						<button type="button" class="btn btn-default" id="logOut"
							name="logOut"><span class="fa fa lock-unlocked" ></span>logout</button>
					</c:otherwise>
				</c:choose>
				<br>
			</form>
		</div>
	</header>

	<!-- Modal SignUp -->
	<div class="modal fade" id="mySignUp" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content 회원가입-->
			<div class="modal-content">
				<div class="modal-header">
					<div class="jumbotron" style="padding-top: 20px;">
						<form method="post" id="signUpForm" style="padding: 5px"
							name="sign">
							<h3 style="text-align: center;">회원가입 화면</h3>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="아이디"
									name="userId" maxlength="20" id="id">
								<div id="id_check"></div>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" placeholder="비밀번호"
									name="userPassword" id="pw1" maxlength="20">
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="비밀번호 재확인" id="pw2" name="userPasswordconfirm"
									maxlength="20">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="이름"
									name="userName" maxlength="20">
							</div>
							<div class="form-group">
								<input type="email" class="form-control" placeholder="email"
									name="userEmail" maxlength="100">
							</div>
							<div class="form-group" style="text-align: center;">
								<div class="btn-group" data-toggle="buttons">
									<span>
										<button type="button" id="reset" class="btn btn-danger">취소</button>
										<button type="button" id="signup" class="btn btn-success">가입</button>
									</span>

								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>