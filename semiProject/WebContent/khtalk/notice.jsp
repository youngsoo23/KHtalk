<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<script type="text/javascript">
	$(document).ready(function() {
		$('#write').on('click', function() {
			$('form').attr("action", "nwriteform.do");
			$('form').submit();
		});

	})
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
					<li><a href="index" style="color:white" >메&nbsp;&nbsp;&nbsp;&nbsp;인</a></li>
					<li id="active"><a href="notice.do" style="color:white">공지사항</a></li>
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
					<div class="groups">
						<h1 class="page-header">공지사항</h1>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>제목&emsp;&emsp;&emsp;&emsp;&emsp;</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.nList}" var="dto">
									<tr>
										<td><a href="nview.do?num=${dto.num}">${dto.subject}</a></td>
										<td>운영자</td>
										<td>${dto.reg_date}</td>
										<td>${dto.readcount}</td>
									</tr>
								</c:forEach>
								<c:url var="cpage" value="nview.do">
									<c:param name="num" value="${dto.num }" />
									<c:param name="pageNum" value="${npdto.currentPage }" />
								</c:url>


							</tbody>

						</table>


					</div>
					<!-- <a href="nwriteForm.do" class="btn btn-default">글쓰기</a> -->
					<c:choose>
						<c:when test="${'hello'==sessionScope.userId}">
							<input type="button" value="글쓰기" id="write"
								class="btn btn-default" />
						</c:when>
					</c:choose>
					<div class="pagelist" align="center">

						<!-- 이전 -->
						<c:if test="${npdto.startPage>1}">
							<a href="notice.do?pageNum=${npdto.startPage-npdto.blockPage}">이전</a>
						</c:if>
						<!-- 페이지 -->
						<c:forEach begin="${requestScope.npdto.startPage}"
							end="${requestScope.npdto.endPage}" var="i">
							<span> <c:choose>
									<c:when test="${i==requestScope.npdto.currentPage}">
										<a href="notice.do?pageNum=${i}" class="pagecolor">${i}</a>
									</c:when>
									<c:otherwise>
										<a href="notice.do?pageNum=${i}">${i}</a>
									</c:otherwise>
								</c:choose>
							</span>
						</c:forEach>

						<!-- 다음 -->
						<c:if test="${npdto.endPage<npdto.totalPage}">
							<a href="notice.do?pageNum=${npdto.startPage+npdto.blockPage}">다음</a>
						</c:if>
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