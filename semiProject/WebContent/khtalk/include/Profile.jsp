<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.userId}">
			<div class="panel panel-default friends">
				<div class="panel-heading">
					<h3 class="panel-title">${sessionScope.userName }'s Info</h3>
				</div>
				<div class="panel-body" style="text-align: center">
					<div class="row" style="margin: auto">
						<div>
							<img src="/semiProject/${requestScope.mypicture}"
								class="img-thumbnail" name="profilepicture"
								style="width: 150px; height: 150px" alt="">
						</div>
						<br/>
						<div class="col-md-8" style="display: table">
							<ul>
								<li><strong>Name:</strong>${sessionScope.userName}</li>
								<li><strong>Email:</strong>${sessionScope.userEmail}</li>
								<li><strong>bio:</strong>${requestScope.content}</li>

							</ul>
						</div>
					</div>
					<div class="clearfix"></div>
					<br/>
					<a class="btn btn-primary" href='setting' id="btn-primary">setting my profile</a>
				</div>
			</div>
		</c:when>
	</c:choose>
</body>
</html>