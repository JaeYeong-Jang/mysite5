<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header nav -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- /header nav -->

		<div id="container" class="clearfix">

			<!-- aside -->
			<c:import url="/WEB-INF/views/includes/asideGuestBook.jsp"></c:import>
			<!-- aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-password" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72"
											rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						
					</form>

					<div id="listArea"></div>
					<!-- 
						<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					 -->
					<!-- //guestRead -->
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	//화면 로딩되기 직전
	$(document).ready(function() {//타이밍 정의
	console.log("화면 로딩 직전");
	
	//ajax 요청하기
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},

		//dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			
			//화면에 그리기
			for(var i = 0; i < guestList.length; i++){
				render(guestList[i], "down"); //방명록 글을 하나씩 추가하는 함수
			
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	});
	//로딩 끝난 후
	//등록버튼 클릭 할 때
	$("#btnSubmit").on("click", function(){
		event.preventDefault(); //기존의 버튼 폼 사용막는 코드
		console.log("등록버튼 클릭");
		
		//name 값 읽어오기
		//var userName = $("#input-uname").val();
		//password 값 읽어오기
		//var userPassword = $("#input-password").val();
		//content 값 읽어오기
		//var userContent = $("[name='content']").val();
		//값 한번에 읽어와서 하나로 묶기
		var guestBookVo = {
				name : $("#input-uname").val(),
				password : $("#input-password").val(),
				content : $("[name='content']").val()
		};
		
		$.ajax({
			
			//url : "${pageContext.request.contextPath }/api/guestbook/write?name=" + userName + "&password=" + userPassword + "&content=" + userContent,		
			url : "${pageContext.request.contextPath }/api/guestbook/write",
			type : "get",
			//contentType : "application/json",
			//data : {name: userName, password : userPassword, content : userContent},
			data : guestBookVo,
			//dataType : "json",
			success : function(guestBookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookVo); // 웹 콘솔창에 표기
				render(guestBookVo, "up");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	});
	
	//방명록 1개씩 렌더링하는 함수
	function render(guestBookVo, type){
		var str = "";
		
		str += '<table class="guestRead">';
		str += '<colgroup>';
		str += '	<col style="width: 10%;">';
		str += '	<col style="width: 40%;">';
		str += '	<col style="width: 40%;">';
		str += '	<col style="width: 10%;">';
		str += '</colgroup>';
		str += '<tr>';
		str += '	<td>'+ guestBookVo.no +'</td>';
		str += '	<td>'+ guestBookVo.name +'</td>';
		str += '	<td>'+ guestBookVo.regDate +'</td>';
		str += '	<td><a href="">[삭제]</a></td>';
		str += '</tr>';
		str += '<tr>';
		str += '	<td colspan=4 class="text-left">'+ guestBookVo.content +'</td>';
		str += '</tr>';
		str += '</table>';
		
		if(type === "down"){
			$("#listArea").append(str);
		}else if(type === "up"){
			$("#listArea").prepend(str);
		}else{
			console.log("방향을 지정해 주세요.");
		}
		
		
	}
</script>


</html>