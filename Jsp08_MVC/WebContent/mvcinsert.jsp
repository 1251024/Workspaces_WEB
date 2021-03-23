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

	<form action="mvc.do" method="post">
		<!-- 어디로 보내는지 알려줘야하니까 insert res로 써줌 -->
		<input type="hidden" name="command" value="insertres">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type = "text" name = "writer"></td>

			</tr>
			<tr>
				<th>제목</th>
				<td><input type = "text" name = "title"></td>

			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60"name="content"></textarea></td>

			</tr>
			<tr>
				<td colspan ="2" align ="right">
					<input type = "submit" value="글작성">
					<input type = "button" value="취소" onclick="location.href='mvc.do?command=list'">
				</td>
			</tr>

		</table>
	
	
	</form>
</body>
</html>