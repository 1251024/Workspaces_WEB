<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;Charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>insert</h1>

	<!-- 액션에는 값을 전달받을 경로 url -->
	<!-- request 객체는 특정번지까지 헤더고 나머지 바디인데 헤더는 길이가 정해져있고 바디는 무한정 늘어남, 겟은 헤더부분에 저장 포스트는 바디에 저장되어 길이가 얼마나 길어질지 모르면 포스트형태로 바디에 저장 -->
	<!-- form태그안에 name을 써야함-->
	<form action="myinsert_res.jsp" method="POST">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
	
			<tr>
				<th>제목</th>
				<td><input type="text" name ="title"></td>
			</tr>
	
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="작성">
					<input type="button" value="취소" onclick="">
				</td>
			</tr>
		</table>
	</form>


</body>
</html>