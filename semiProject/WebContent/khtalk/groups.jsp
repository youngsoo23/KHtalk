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
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiProject/khtalk/js/index.js"></script>

<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#searchBtn').click(function() {
			$('#search').attr('action', 'studyList').submit();
		});
		$('#logchk1').on('click', function() {
			alert("로그인이 필요합니다")
		})
		$('#logchk2').on('click', function() {
			alert("로그인이 필요합니다")
		})


		$('[name=searchWord]').val('${pdto.searchWord}');
		switch ('${pdto.searchKey}') {
		case 'all':
			$('[value=all]').prop('selected', 'selected');
			$('[name=searchWord]').val('');
			break;
		case 'subject':
			$('[value=subject]').prop('selected', 'selected');
			break;
		case 'userId':
			$('[value=userId]').prop('selected', 'selected');
			break;
		case 'semicontent':
			$('[value=semicontent]').prop('selected', 'selected');
			break;
		}
	});
</script>
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
					<li id="active"><a href="studyList" style="color:white">스터디</a></li>
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
						<h1 class="page-header">스터디 게시판</h1>
						<c:forEach items="${requestScope.aList}" var="dto">
							<c:url var="cpage" value="StudyDetailView.do">
								<c:param name="num" value="${dto.num}" />
								<c:param name="subject" value="${dto.subject}" />
								<c:param name="pageNum" value="${pdto.currentPage }" />
								<c:param name="searchKey" value="${pdto.searchKey }" />
								<c:param name="searchWord" value="${pdto.searchWord }" />
							</c:url>
							<div class="group-item">
								<img src="/semiProject/${dto.main_photo }" class="img-thumbnail"
									style="height: 100px; width: 100px" alt="">
								
									<form method="post" action="StudyDetailView.do">
										<input type="hidden" name="studyid" value="${dto.num}">
										<input type="hidden" name=leaderId value="${dto.userId}">
										<h4>${dto.subject}</h4>
										<p>작성 날짜 :${dto.reg_date }</p>
										<c:choose>
										<c:when test="${sessionScope.userId==null }">
										<a href="#userId"><input type="button" id="logchk1" class="btn btn-default" value="상세보기" style="float:right"></a>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-default" style="float:right">상세보기</button>
										</c:otherwise>
										</c:choose>
									</form>
								
							</div>
							<div class="clearfix"></div>
						</c:forEach>
						<c:choose>
							<c:when test="${not empty sessionScope.userId}">
								<p>
									<a href="StudyAddForm.do" class="btn btn-default">방 만들기</a>
								</p>
							</c:when>
						</c:choose>
						<div class="pagelist" align="center">
						<!-- 이전 -->
						<c:if test="${pdto.startPage > 1}">
							<a href="studyList?pageNum=${pdto.startPage-pdto.blockPage }">이전</a>
						</c:if>

						<!-- 페이지 -->
						<c:forEach begin="${requestScope.pdto.startPage }"
							end="${requestScope.pdto.endPage}" var="i">
							<span> <c:choose>
									<c:when test="${i==requestScope.pdto.currentPage }">
										<a href="studyList?pageNum=${i}" class="pagecolor">${i} </a>
									</c:when>
									<c:otherwise>
										<a href="studyList?pageNum=${i}">${i} </a>
									</c:otherwise>
								</c:choose>
							</span>
						</c:forEach>

						<!-- 다음 -->
						<c:if test="${pdto.endPage<pdto.totalPage }">
							<a href="studyList?pageNum=${pdto.startPage+pdto.blockPage }">다음</a>
						</c:if>
					</div>
					</div>
					<br/>
					<div class="text-center">
						<form id="search">
							<select name="searchKey">
								<option value="all">검색어를 입력</option>
								<option value="subject">제목</option>
								<option value="semicontent">간략 내용</option>
								<option value="userId">글쓴이</option>
							</select> <input type="text" name="searchWord" /> <input type="button"
								value="검색" class="btn btn-default" id="searchBtn" />
						</form>
					</div>
					
				</div>
				<!-- 싸이드 리스트  -->
				<div class="col-md-4">
					<jsp:include page="include/Profile.jsp"></jsp:include>
					<div class="panel panel-default groups">
						<c:choose>
							<c:when test="${not empty sessionScope.userId}">
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
<!-- </body> -->
</html>