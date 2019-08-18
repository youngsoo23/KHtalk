<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판_글 수정</title>
<style type="text/css">
#userId {
	border: none;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {

				$('form').on(
						'submit',
						function() {
							$('[name=content]').val(
									$('[name=content]').val().replace(/\n/gi,
											'<br/>'));
						});

				$('#cancel').on('click', function() {
					var chk = confirm("글 작성을 취소하시겠습니까?");
					if (chk) {
						$('form').attr('action', 'freelist.do');
						$('form').submit(); //submit 이벤트 강제 발생
					} else
						return false;
				});
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
					<li><a href="index" style="color:white" >메&nbsp;&nbsp;&nbsp;&nbsp;인</a></li>
					<li><a href="notice.do" style="color:white">공지사항</a></li>
					<li id="active"><a href="freeboard" style="color:white">자유&nbsp;게시판</a></li>
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
						<h1 class="page-header">자유게시판</h1>
						<jsp:scriptlet>pageContext.setAttribute("cr", "\r"); //Space
			pageContext.setAttribute("cn", "\n"); //Enter
			pageContext.setAttribute("crcn", "\r\n");//Space,Enter</jsp:scriptlet>
						<form name="frm" method="post" enctype="multipart/form-data"
							action="freeupdatePro.do">
							<div class="group-item">
								<input type="hidden" name="num" value="${dto.num}" /> <input
									type="hidden" name="pageNum" value="${param.pageNum}" /> <input
									type="hidden" name="searchKey" value="${param.searchKey}" /> <input
									type="hidden" name="searchWord" value="${param.searchWord}" />
								<table>

									<tr>
										<td width="20%">글쓴이</td>
										<td width="80%"><input type="text" name="userId"
											id="userId" value="${sessionScope.userId}"
											readonly="readonly" /></td>
									</tr>

									<tr>
										<td width="20%">제목</td>
										<td width="80%"><input type="text" name="subject"
											value="${dto.subject}" /></td>
									</tr>

									<tr>
										<td width="20%">내용</td>
										<td width="80%"><textarea name="content" rows="15"
												cols="80">${fn:replace(dto.content,'<br/>',crcn)}</textarea></td>
									</tr>

									<tr>
										<td width="20%">파일첨부</td>
										<td width="80%" id="fileDiv"><input type="file"
											name="upload" /><span>${dto.upload}</span></td>
									</tr>

								</table>

							</div>
							<input type="submit" value="수정" class="btn btn-default" /> <input
								type="button" value="취소" class="btn btn-default" id="cancel" />
						</form>
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
<!-- 	<script src="/semiProject//khtalk/js/bootstrap.js"></script> -->
<!-- 	<script src="/semiProject//khtalk/js/ekko-lightbox.js"></script> -->
</body>
</html>