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
	$(document).ready(
			function() {
				$('form').on(
						'submit',
						function() {
							$('[name=semicontent]').val(
									$('[name=semicontent]').val().replace(
											/\n/gi, '<br/>'));
						});
				
				$('#filepath').on('change', function() {
					var str = $('#filepath').val();
					console.log(str);

					//이미지 첨부파일인지 체크
					var patt = /(.jpg$|.gif$|.png$)/gi; // i 를 추가 시 대소문자 구분 안함
					var result = str.match(patt);

					if (!result) {
						alert('jpg, gif, png 확장자만 가능합니다.');
						$('#filepath').val('');
						return false;
					}

					//파일첨부 사이즈 체크
					console.log(this.files + " , " + this.files[0]);
					if (this.files && this.files[0]) {
						console.log('size:' + this.files[0].size);
						if (this.files[0].size > 1000000000) {
							console.log('1GB바이트 이하만 첨부할 수 있습니다.');
							$('#filepath').val('');
							return false;
						}
					}

					//파일을 읽기 위한 FileReader객체 생성
					var reader = new FileReader();

					//File내용을 읽어 dataURL 형식의 문자열로 저장
					reader.readAsDataURL(this.files[0]);

					//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
					reader.onload = function(e) {
						//이미지 Tag의 src속성에 읽어들인 File내용을 지정
						$('img').attr('src', e.target.result);
					}

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
					<form name="frm" method="post" enctype="multipart/form-data"
						action="StudyAdd.do">

						<input type="hidden" name="searchKey" value="${param.searchKey }" />
						<input type="hidden" name="searchWord"
							value="${param.searchWord }" />

						<table>
							<tr>
								<td align="right" colspan="2"><a href="studyList">리스트</a></td>
							</tr>

							<tr>
								<td width="20%" align="left">글쓴이 (아이디)</td>
								<td width="80%"><input type="hidden" name="userId" value="${sessionScope.userId }" />${sessionScope.userId }</td>
							</tr>

							<tr>
								<td width="20%" align="left">제목</td>
								<td width="80%"><input type="text" name="subject" /></td>
							</tr>

							<tr>
								<td width="20%" align="left">간략내용</td>
								<td width="80%"><textarea name="semicontent" rows="2"
										cols="40"></textarea></td>
							</tr>

							<tr>
								<td width="20%" align="left">내용</td>
								<td width="80%"><textarea name="content" rows="20"
										cols="60"></textarea></td>
							</tr>

							<tr>
								<td width="20%" align="left">메인 사진 첨부<img width="100"
									height="100" id="test" /></td>
								<td width="80%" id="fileDiv"><input type="file"
									name="filepath" id="filepath" /></td>
							</tr>

							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="확인" class="btn btn-default"/>&nbsp;&nbsp;&nbsp; <input type="reset" value="취소" class="btn btn-default" /></td>
							</tr>
						</table>
					</form>
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
<!-- 	<script src="/semiProject/khtalk/js/ekko-lightbox.js"></script> -->
</body>
</html>