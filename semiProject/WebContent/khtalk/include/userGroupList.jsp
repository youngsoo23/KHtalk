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

#active, #btn-primary:hover{
	background-color: #385DA1;
}
</style>
</head>
<body>
	<div class="panel-heading">
		<h3 class="panel-title">참여 중인 스터디</h3>
	</div>
	<div class="panel-body">
		<c:forEach items="${requestScope.uList}" end="2" step="1" var="udto">
			<c:url var="cpage" value="StudyDetailView.do">
				<c:param name="num" value="${udto.num}" />
				<c:param name="subject" value="${udto.subject}" />
				<c:param name="pageNum" value="${spdto.currentPage }" />
				<c:param name="searchKey" value="${spdto.searchKey }" />
				<c:param name="searchWord" value="${spdto.searchWord }" />
			</c:url>
			<div class="group-item">
				<img src="/semiProject/${udto.main_photo }" class="img-thumbnail"
					style="height:50px; width:50px" alt="">
					<form method="post" action="StudyDetailView.do">
				
						<input type="hidden" name="studyid" value="${udto.num}"> <input
							type="hidden" name=leaderId value="${udto.userId}">
						<h6>${udto.subject}</h6>
						<h4><button type="submit" class="btn btn-primary btn-xs" id="btn-primary" style="float: right; background-color:#142F62;" >상세보기</button></h4>
				
					</form>
			</div>
			<div class="clearfix"></div>
		</c:forEach>
	</div>
</body>
</html>