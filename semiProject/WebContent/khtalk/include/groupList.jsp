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
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
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
	/*
	 * $('#signUp').on('click',function(){
	 * $('.form-inline').attr("action","signUp").submit(); });
	 */
	$('#logchk1').on('click', function() {
		alert("로그인이 필요합니다")
	})
	$('#logchk2').on('click', function() {
		alert("로그인이 필요합니다")
	})
});
</script>
</head>

<body>
	<div class="panel-heading">
		<h3 class="panel-title">최근 개설된 스터디</h3>
	</div>
	<div class="panel-body">
		<c:forEach items="${requestScope.sList}" var="sdto">
			<c:url var="cpage" value="StudyDetailView.do">
				<c:param name="num" value="${sdto.num}" />
				<c:param name="subject" value="${sdto.subject}" />
				<c:param name="pageNum" value="${spdto.currentPage }" />
				<c:param name="searchKey" value="${spdto.searchKey }" />
				<c:param name="searchWord" value="${spdto.searchWord }" />
			</c:url>
			<div class="group-item">
				<img src="/semiProject/${sdto.main_photo }" class="img-thumbnail"
					style="height: 50px; width: 50px" alt="">
			
					<form method="post" action="StudyDetailView.do">
						<input type="hidden" name="studyid" value="${sdto.num}"> <input
							type="hidden" name=leaderId value="${sdto.userId}">
						<h6>${sdto.subject}</h6>
						<c:choose>
										<c:when test="${sessionScope.userId==null }">
										<a href="#userId"><input type="button" id="logchk2" class="btn btn-default" style="float: right" value="상세보기"></a>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-default" style="float: right">상세보기</button>
										</c:otherwise>
										</c:choose>
<!-- 										<button type="submit" class="btn btn-primary" style="float: right">상세보기</button> -->
					</form>
			
			</div>
			<div class="clearfix"></div>
		</c:forEach>
	</div>
</body>
</html>