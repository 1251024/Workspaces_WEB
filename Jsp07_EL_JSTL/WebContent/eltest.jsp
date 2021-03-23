<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>EL</h1>
	<!-- 표현식 언어  -->
	<!-- Score score = (score)request.getAttribute("score");가 생략되어 500에러떠야하는데 표현식언어는 gracefully하게 처리해줘서 없던일로 만들어줌 -->
	
	<table border = "1">
		<tr>
			<th colspan = "2">${score.name }님의 점수표</th><!-- scope에서 작은거에서 큰순서대로 찾음 -->
		</tr>
		<tr>
			<th>국어</th>
			<td>${score.kor }</td>
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng }</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>${score.math }</td>
		</tr>
		<tr>
			<th>총점</th>
			<td>${score.sum }</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${score.avg }</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${score.grade }</td>
		</tr>
		
	</table>

</body>
</html>