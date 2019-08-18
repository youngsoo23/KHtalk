<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 
	 //검색버튼 이벤트
	$('#searchBtn').click(function(){
		$('form').attr('action','freelist.do');
		$('form').submit();
	}) ;
	
	//검색어 고정
	$('[name=searchWord]').val('${fpdto.searchWord}');
	if ('${fpdto.searchKey}'=='null') {
		$('[name=searchWord]').val('');
	} else {
		switch ('${fpdto.searchKey}') {
		//searchKey가 all일 때 검색창값 입력되면 안됨
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
		case 'content':
			$('[value=content]').prop('selected', 'selected');
			break;
		}
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Dobble Social Network: Group Page</title>

<!-- Bootstrap core CSS -->
<!-- <link href="/semiProject/khtalk/css/bootstrap.css" rel="stylesheet"> -->

<!-- Custom styles for this template -->
<!-- <link href="/semiProject/khtalk/css/style.css" rel="stylesheet"> -->
<!-- <link href="/semiProject/khtalk/css/font-awesome.css" rel="stylesheet"> -->

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
					<div class="group">
						<h1 class="page-header">자유게시판</h1>
							<table class="table table-hover">
								<thead>
									<th>제목&emsp;&emsp;&emsp;&emsp;&emsp;</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>조회수</th>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.fList}" var="list">
										<tr>
											<td><c:if test="${list.re_level!=0}">
													<img src="../khtalk/img/level.gif"
														width="${list.re_level*10}" />
											      RE:
											</c:if> 
											<c:url var="cpage" value="freeview.do">
											<c:param name="num" value="${list.num}" />
											<c:param name="pageNum" value="${fpdto.currPage}" />
											<c:param name="searchKey" value="${fpdto.searchKey}" />
											<c:param name="searchWord" value="${fpdto.searchWord}" />
											</c:url>
											<a href="${cpage}">${list.subject}</a>
												<c:if test="${!empty list.upload}">
													<img src="../khtalk/img/file.gif" alt="file" />
												</c:if></td>
											<td>${list.userId}</td>
											<td>${list.reg_date}</td>
											<td>${list.readcount}</td>
										</tr>

									</c:forEach>
								</tbody>

							</table>
						</div>
						<a href="freelist.do" class="btn btn-default">목록</a>
						<c:choose>
						   <c:when test="${not empty sessionScope.userId}">
						       <a href="freewriteForm.do" class="btn btn-default">글쓰기</a>
						   </c:when>
						   <c:otherwise></c:otherwise>
						</c:choose>

						<div class="pagelist" align="center">
							
							<!-- 이전 페이지 -->
							<c:if test="${fpdto.startPage>1}">
								<a href="freelist.do?pageNum=${fpdto.startPage-fpdto.blockPage}&searchKey=${fpdto.searchKey}&searchWord=${fpdto.searchWord}">이전</a>
							</c:if>
							<!-- 페이지 번호 -->
							<c:forEach begin="${requestScope.fpdto.startPage}"
								end="${requestScope.fpdto.endPage}" var="i">
								<span> <c:choose>
										<c:when test="${i==requestScope.fpdto.currPage}">
											<a href="freelist.do?pageNum=${i}&searchKey=${fpdto.searchKey}&searchWord=${fpdto.searchWord}" class="pagecolor">${i}</a>
										</c:when>
										<c:otherwise>
											<a href="freelist.do?pageNum=${i}&searchKey=${fpdto.searchKey}&searchWord=${fpdto.searchWord}">${i}</a>
										</c:otherwise>
									</c:choose>
								</span>
							</c:forEach>

							<!-- 다음 페이지 -->
							<c:if test="${fpdto.endPage<fpdto.totalPage}">
								<a href="freelist.do?pageNum=${fpdto.startPage+fpdto.blockPage}&searchKey=${fpdto.searchKey}&searchWord=${fpdto.searchWord}">다음</a>
							</c:if>
						</div>
						
						</br>
						<!-- 검색 상자 -->
						<form align="center">
							<select name="searchKey">
								<option value="all">선택</option>
								<option value="subject">제목</option>
								<option value="content">내용</option>
								<option value="userId">글쓴이</option>
							</select> <input type="text" name="searchWord" /> <input type="button"
								value="검색" id="searchBtn" class="btn btn-default" />
						</form>
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