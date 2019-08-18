<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	int z = 0;
%>

<%-- <c:set var="num[i]" value="${i}" /> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="/semiProject/khtalk/js/Study.js"></script> -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#searchBtn').click(function() {
			$('form').attr('action', 'studyList');
			$('form').submit();
		});
		$('#update').on('click', function() {
			$('#roomupdate').attr('action', 'StudyUpdateForm.do').submit();
		});

		$('#del').on('click', function() {
			$('#roomdel').attr('action', 'StudyDelete.do').submit();
		});
		$('#mainphoto').on('click', function() {
			alert("picture");
			$('#main_photo').click();
		});

		$("#main_photo").on('change', function() {
			var str = $("#main_photo").val();
			console.log(str);
			// 이미지 첨부파일인지 체크
			var patt = /(.jpg$|.gif$|.png$)/gi;
			var result = str.match(patt);

			if (!result) {
				alert("jpg, gif, png만 가능합니다.");
				$('#main_photo').val('');
				return false;
			}
			// 첨부파일 사이즈 체크
			console.log(this.files + "," + this.files[0]);
			// if (this.files && this.files[0]) {

			console.log("size: " + this.files[0].size);
			if (this.files[0].size > 1000000000) {
				alert("1GB 이하만 첨부할수 있습니다.");
				$("#main_photo").val('');
				return false;
			}
			// }

			// 파일을 읽기 위한 fileReader 객체 생성.
			var reader = new FileReader();
			// file 내용을 읽어 dataURL형식의 문자열로 저장
			reader.readAsDataURL(this.files[0]);
			// 파일 읽어들이기를 성공했읋때 호출되는 이벤트 핸들러
			reader.onload = function(e) {
				// 이미지Tag의 src속성에 읽어들인 File내용을 지정
				$("#pic").attr('src', e.target.result);
			}

		});// end change
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
					<div class="profile">
						<h1 class="page-header">${dto.subject }</h1>
						<div class="row">
							<div class="col-md-4">
								<img src="/semiProject/${dto.main_photo}" class="img-thumbnail"
									name="profilepicture" alt="">

							</div>

							<div class="col-md-8">
								<ul>
									<li class="text-right">작성 날짜 :${dto.reg_date }</li>
									<li class="text-right">조회수 : ${dto.readcount }</li>
									<li><strong>간략내용:</strong>${dto.semicontent}</li>
									<p></p>
									<li><strong>내용:</strong>${dto.content}</li>

								</ul>
							</div>

						</div>
						<c:if
							test="${requestScope.chkRoom == 0 && not empty sessionScope.userId}">
							<form method="post" action="StudyJoin.do">
								<input type="hidden" name="studyid"
									value="${requestScope.studyid}">
								<div>
									<button type="submit" class="btn btn-success"
										style="float: right">방 참여하기</button>
								</div>
							</form>
						</c:if>
						<c:if
							test="${requestScope.chkRoom== 1 && not empty sessionScope.userId}">
							<form method="post" action="StudyDeleteRoom.do">
								<input type="hidden" name="studyid"
									value="${requestScope.studyid}">
								<c:if
									test="${requestScope.roomnum!=requestScope.roommanagernum}">
									<div>

										<button type="submit" class="btn btn-danger"
											style="float: right">방나가기</button>
									</div>
								</c:if>
							</form>
						</c:if>

						
						<c:if test="${requestScope.roomnum==requestScope.roommanagernum}">
							<form id="roomdel">
								<input type="hidden" name="num" value="${dto.num }">
								<button type="button" class="btn btn-default" id="del"
									style="float: right">
									<i class="fa fa-cog fa-spin"></i> 삭제
								</button></form>
								<form id="roomupdate">
								<input type="hidden" name="num" value="${dto.num }">
								<button type="button" class="btn btn-default" id="update"
									name="update" style="float: right">
									<i class="fa fa-cog fa-spin"></i> Update
								</button>
							</form>
						</c:if>
				
						<c:choose>
							<c:when
								test="${requestScope.chkRoom == 1 && not empty sessionScope.userId}">
								<div class="row" style="margin-top: 50px;">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title">대화톡</h3>
											</div>
											<div class="panel-body">
												<form method="post" action="chatcomm">
													<div class="form-group">
														<textarea class="form-control"
															placeholder="내용을 입력해주세요." name="content"></textarea>
													</div>
													<input type="hidden" name="studyid"
														value="${requestScope.studyid}">
													<button type="submit" class="btn btn-default">작성</button>
													<div class="pull-right">

													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default post">
									<div class="panel-body">
										<div class="row">
											<c:forEach items="${requestScope.chatList}" var="list">
												<c:choose>
													<c:when test="${sessionScope.userId==list.userId}">
														<div class="col-sm-2">
															<div class="text-center">${list.userId }</div>
														</div>
														<div class="col-sm-10">
															<div class="clearfix"></div>

															<div class="comments">
																<div class="comment">
																	<a href="#" class="comment-avatar pull-left"><img
																		src="img/user.png" alt=""></a>
																	<form method="post" action="deleteComm">
																		<div class="comment-text">
																			<div>${list.content}<input type="submit"
																					value="x" class="btn btn-danger btn-xs"
																					style="float: right">
																			</div>
																			<input type="hidden" name="studyid"
																				value="${requestScope.studyid}"> <input
																				type="hidden" name="chatNum" value="${list.chatNum}">
																			<div style="float: right">${list.today }</div>
																		</div>
																	</form>
																</div>
															</div>
															<div class="clearfix"></div>
														</div>

													</c:when>
													<c:otherwise>
														<div class="col-sm-10">
															<div class="comments">
																<div class="comment">
																	<a href="#" class="comment-avatar pull-left"><img
																		src="img/user.png" alt=""></a>
																	<div class="comment-text">
																		<p>${list.content}</p>
																		<div style="float: right">${list.today }</div>
																	</div>
																</div>
																<div class="clearfix"></div>
															</div>
														</div>
														<div class="col-sm-2" style="float: right">
															<div class="text-center">${list.userId }</div>
														</div>

														<div class="clearfix"></div>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</div>
									</div>
								</div>
							</c:when>
						</c:choose>
						<form method="post">
							<input type="hidden" name="studyid"
								value="${requestScope.studyid}"> <input type="hidden"
								name="pageNum" value="${param.pageNum }" /> <input
								type="hidden" name="num" value="${dto.num }" /> <input
								type="hidden" name="searchKey" value="${param.searchKey }" /> <input
								type="hidden" name="searchWord" value="${param.searchWord }" />
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
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<!-- 	<script src="/semiProject/khtalk/js/bootstrap.js"></script> -->
</body>
</html>