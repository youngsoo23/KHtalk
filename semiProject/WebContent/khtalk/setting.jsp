<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiProject/khtalk/js/profile.js"></script>
<style type="text/css">
#navbar, #btn-primary {
	background-color: #142F62;
}

#active, #btn-primary:hover {
	background-color: #385DA1;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
$('#quit').on('click', function() {
	alert("회원 탈퇴 되었습니다..");
	$('.form-inline').attr('action', 'quit').submit();
})
	
});
</script>
</head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Dobble Social Network: Profile Page</title>

<!-- Bootstrap core CSS -->
<link href="/semiProject/khtalk/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/semiProject/khtalk/css/style.css" rel="stylesheet">
<link href="/semiProject/khtalk/css/font-awesome.css" rel="stylesheet">
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<nav class="navbar navbar-default" id="navbar">
		<div class="container">
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
					<li><a href="index" style="color:white" >메&nbsp;&nbsp;&nbsp;&nbsp;인</a></li>
					<li><a href="notice.do" style="color:white">공지사항</a></li>
					<li><a href="freeboard" style="color:white">자유&nbsp;게시판</a></li>
					<li><a href="studyList" style="color:white">스터디</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.userId}">
							<li id="active"><a href="profile" style="color:white">프로필</a></li>
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
					<c:choose>
						<c:when test="${not empty sessionScope.userId}">
							<div class="profile">
								<h1 class="page-header">${sessionScope.userName}'sProfile</h1>
								<form class="profileSetting" method="post"
									enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-4">
											<img src="/semiProject/${requestScope.mypicture}"
												class="img-thumbnail" id="pic" alt=""> 
												<input
												type="hidden" name="oldpicture" id="oldpicture"
												value="${requestScope.mypicture}" />
										</div>
										<div class="col-md-8">
											<button type="button" class="btn btn-danger" id="quit"
												name="quit" style="float: right">
												<i class="fa fa-sign-out" aria-hidden="true"></i>탈퇴
											</button>
											<ul>
												<li><strong>Name:</strong>${sessionScope.userName}</li>
												<li><strong>Email:</strong>${sessionScope.userEmail}</li>
												<li><strong>Content:</strong> <textarea name="content"
														id="content" class="form-control"
														placeholder="Write your bio!"></textarea></li>
											</ul>
										</div>
									</div>
									<div>
										<button type="button" class="btn btn-default"
											id="pictureUpload" name="pictureUpload" style="float: left">
											<i class="fa fa-cog fa-spin"></i>사진 삽입
										</button>
										<input type="file" name="filepath" id="filepath"
											style="display: none" />
										<button type="button" class="btn btn-default" id="save"
											name="save" style="float: right">
											<i class="fa fa-cog fa-spin"></i>SAVE
										</button>
									</div>
								</form>
								<br> <br>
							</div>
						</c:when>
					</c:choose>
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

	<footer>
		<div class="container">
			<p>KH TALK 5조 &nbsp; Copyright &copy,&nbsp;2019 서울특별시 중구 남대문로 120
				대일빌딩 2F, 3F</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<!-- 	<script src="/semiProject/khtalk/js/bootstrap.js"></script> -->
</body>
</html>