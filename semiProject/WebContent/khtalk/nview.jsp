<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
#view {
	border: 1px solid #ccc;
	width: 100%;
}

#view td {
	border: 1px solid #ccc;
	padding: 8px;
}

#view #index {
	background-color: #f4f4f4;
}

#navbar, #btn-primary {
	background-color: #142F62;
}

#active, #btn-primary:hover {
	background-color: #385DA1;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/semiProject/khtalk/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/semiProject/khtalk/css/style.css" rel="stylesheet">
<link href="/semiProject/khtalk/css/font-awesome.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		$('#list').on('click', function() {
			//location.href="list.do";
			$('#noticelist').attr("action", "notice.do").submit();
		});

		$('#update').on('click', function() {
			$('#noticeupdate').attr('action', 'nupdateForm.do').submit();
		});

		$('#del').on('click', function() {
			$('#trash').attr('action', 'ndelete.do').submit();
		});

	});
</script>

</head>
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
					<li><a href="index" style="color: white">메&nbsp;&nbsp;&nbsp;&nbsp;인</a></li>
					<li id="active"><a href="notice.do" style="color: white">공지사항</a></li>
					<li><a href="freeboard" style="color: white">자유&nbsp;게시판</a></li>
					<li><a href="studyList" style="color: white">스터디</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.userId}">
							<li><a href="profile" style="color: white">프로필</a></li>
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
					<div class="groups">
						<h1 class="page-header">공지사항</h1>
						<div class="group-item">

							<table id="view">
								<tr>
									<td width="20%" id="index">글쓴이</td>
									<td width="50%">${dto.userId}</td>
									<td width="15%" id="index">조회수</td>
									<td>${dto.readcount}</td>
								</tr>

								<tr>
									<td width="20%" id="index">제목</td>
									<td colspan="3">${dto.subject}</td>
								</tr>

								<tr>
									<td width="20%" id="index">날짜</td>
									<td colspan="3">${dto.reg_date}</td>
								</tr>

								<tr>
									<td width="20%" height="500px" id="index">내용</td>

									<td colspan="3" style="vertical-align: text-top">${dto.content}</td>
								</tr>



							</table>

						</div>
					
							<c:choose>
								<c:when test="${'hello'==sessionScope.userId}">
									<form name="frm" method="post" id="noticelist">
										<input type="hidden" name="num" value="${dto.num}" /> 
											<button type="submit" class="btn btn-default" id="list" style="float:left">목록</button>

									</form>
									<form name="frm" method="post" id="noticeupdate">

										<input type="hidden" name="num" value="${dto.num}" /> 
											<button type="submit" class="btn btn-default" id="update" style="float:left">수정</button>

									</form>
									<form name="frm" method="post" id="trash">
										<input type="hidden" name="num" value="${dto.num}" /> 
											<button type="submit" class="btn btn-default" id="del" style="float:left">삭제</button>
									</form>


								</c:when>
							</c:choose>
						
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
	<!-- 	<script src="js/bootstrap.js"></script> -->
</body>
</html>