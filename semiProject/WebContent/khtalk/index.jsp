<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>메인화면</title>
<!-- Bootstrap core CSS -->
<link href="/semiProject/khtalk/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/semiProject/khtalk/css/style.css" rel="stylesheet">
<link href="/semiProject/khtalk/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiProject/khtalk/js/index.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style type="text/css">
#navbar, #btn-primary {
	background-color: #142F62;
}

#active, #btn-primary:hover {
	background-color: #385DA1;
}
</style>


</head>



<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<nav class="navbar navbar-default" id="navbar">
		<div class="container">
			<!-- Modal SignUp -->
			<div class="modal fade" id="mySignUp" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content 회원가입-->
					<div class="modal-content">
						<div class="modal-header">
							<div class="jumbotron" style="padding-top: 20px;">
								<form method="post" id="signUpForm" action="signUp" name="sign">
									<h3 style="text-align: center;">회원가입 화면</h3>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="아이디"
											name="userID" maxlength="20">
									</div>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="비밀번호"
											name="userPassword" maxlength="20">
									</div>
									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="비밀번호 재확인" name="userPasswordconfirm"
											maxlength="20">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="이름"
											name="userName" maxlength="20">
									</div>
									<div class="form-group">
										<input type="email" class="form-control" placeholder="email"
											name="userEmail" maxlength="20">
									</div>
									<div class="form-group" style="text-align: center;">
										<div class="btn-group" data-toggle="buttons">
											<span>
												<button type="button" id="reset" class="btn btn-danger">취소</button>
												<button onclick="form_submit()" class="btn btn-success">가입</button>
											</span>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="active"><a href="index" style="color:white" >메&nbsp;&nbsp;&nbsp;&nbsp;인</a></li>
					<li><a href="notice.do" style="color:white">공지사항</a></li>
					<li><a href="freeboard" style="color:white">자유&nbsp;게시판</a></li>
					<li><a href="studyList" style="color:white">스터디</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.userId}">
							<li><a href="profile" style="color:white">프로필</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<ul class="bxslider" style="list-style-type:none">
						<li><img src="/semiProject/khtalk/img/1.jpg" width="650px"
							height="400px" align="middle" /></li>
						<!-- <li><img src="/semiProject/khtalk/img/4.jpg" width="750px"
							height="400px" align="middle" /></li>
						<li><img src="/semiProject/khtalk/img/2.jpg" width="750px"
							height="400px" align="middle" /></li> -->
					</ul>
					<br /> <br />
					<hr />
					<div class="panel panel-default groups">
						<div class="panel-heading">
							<h3 class="panel-title" style="font-weight: bold">공지사항</h3>
						</div>
						<div class="panel-body">

							<c:forEach items="${requestScope.noticelist}" var="dto" step="1"
								end="3">
								<div class="group-item">
									<i class="fa fa-bolt" aria-hidden="true"></i>&emsp; <a
										href="nview.do?num=${dto.num}"><c:out
											value="${dto.subject}" /></a>
									<%-- <h4>
										<a href="#" class=""><c:out value="${dto.subject}" /></a>
									</h4> --%>
								</div>
								<div class="clearfix"></div>
							</c:forEach>
							<c:choose>
								<c:when test="${not empty sessionScope.userId}">
									<a href="notice.do" class="btn btn-primary" id="btn-primary"
										style="float: right">공지사항 보기</a>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
				<!-- 싸이드 리스트  -->
				<div class="col-md-4">
					<jsp:include page="include/Profile.jsp"></jsp:include>
					<div class="panel panel-default groups">
						<c:choose>
							<c:when test="${not empty sessionScope.userId }">
								<jsp:include page="include/userGroupList.jsp"></jsp:include>
							</c:when>
							<c:otherwise>
								<jsp:include page="include/groupList.jsp"></jsp:include>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer id="foot">
		<div class="container">
			<p>KH TALK 5조 &nbsp; Copyright &copy,&nbsp;2019 서울특별시 중구 남대문로 120
				대일빌딩 2F, 3F</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->


</body>
</html>