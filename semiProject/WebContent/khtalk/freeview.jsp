<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
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
<script type="text/javascript">
	$(document).ready(function() {
		//목록 버튼 클릭 시 list.do로 이동
		$('#list').on('click', function() {
			$('#flist').attr('action', 'freelist.do').submit(); //submit 이벤트 강제 발생
		})

		//답변 버튼 클릭 시 writeForm.do 요청
		$('#reply').on('click', function() {
			$('#freply').attr('action', 'freewriteForm.do').submit(); //submit 이벤트 강제 발생
		});

		//수정 버튼 클릭 시 updateForm.do 요청
		$('#update').on('click', function() {
			$('#fupdate').attr('action', 'freeupdateForm.do').submit();
		});

		$('#del').on('click', function() {
			var chk = confirm("정말로 삭제하시겠습니까?");
			if (chk) {
				$('#fdel').attr('action', 'freedelete.do').submit();
			} else
				return false;

		});
	});
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Dobble Social Network: Group Page</title>

<!-- Bootstrap core CSS -->
<link href="/semiProject//khtalk/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/semiProject//khtalk/css/style.css" rel="stylesheet">
<link href="/semiProject//khtalk/css/font-awesome.css" rel="stylesheet">

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
					<li><a href="notice.do" style="color: white">공지사항</a></li>
					<li id="active"><a href="freeboard" style="color: white">자유&nbsp;게시판</a></li>
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
					<div class="group">
						<h1 class="page-header">자유게시판</h1>
						<div class="group-item">
							<table id="view">
								<input type="hidden" value="${dto.num}" />
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

								<tr>
									<td width="20%" id="index">첨부파일</td>

									<td colspan="3"><a
										href="freedownload.do?filename=${dto.upload}">${dto.upload}</a></td>
								</tr>
							</table>
						</div>
						<form method="post" id="flist">
							<!-- 답변글 처리하기 위한 값 hidden으로 받음 -->
							<input type="hidden" name="num" value="${dto.num}" /> <input
								type="hidden" name="pageNum" value="${param.pageNum}" /> <input
								type="hidden" name="ref" value="${dto.ref}" /> <input
								type="hidden" name="re_step" value="${dto.re_step}" /> <input
								type="hidden" name="re_level" value="${dto.re_level}" /> <input
								type="hidden" name="searchKey" value="${param.searchKey}" /> <input
								type="hidden" name="searchWord" value="${param.searchWord}" />
							<button type="submit" class="btn btn-default" id="list" style="float:left">목록</button>
							</form>
							
							<c:choose>
								<c:when test="${not empty sessionScope.userId}">
								<form method="post" id="freply">
								<input type="hidden" name="num" value="${dto.num}" />
									<button type="submit" class="btn btn-default" id="reply" style="float:left">답변</button>
										</form>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${dto.userId==sessionScope.userId}">
								<form method="post" id="fupdate">
								<input type="hidden" name="num" value="${dto.num}" />
									<button type="submit" class="btn btn-default" id="update" style="float:left">수정</button>
										</form>
										<form method="post" id="fdel">
										<input type="hidden" name="num" value="${dto.num}" />
										<button type="submit" class="btn btn-default" id="del" style="float:left">삭제</button>
									
										</form>
								</c:when>
							</c:choose>
					</div>


				</div>
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
	<!-- 	<script src="/semiProject//khtalk/js/bootstrap.js"></script> -->
	<!-- 	<script src="/semiProject//khtalk/js/ekko-lightbox.js"></script> -->
</body>
</html>