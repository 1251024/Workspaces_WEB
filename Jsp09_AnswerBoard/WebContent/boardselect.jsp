<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>DETAIL</h1>
	
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="60" readonly="readonly">${dto.content }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan ="2" align="right">
					<input type = "button" value ="목록" onclick="location.href='answer.do?command=list'">
					<input type = "button" value ="수정" onclick="">
					<input type = "button" value ="삭제" onclick="">
					<input type = "button" value ="답변" onclick="location.href='answer.do?command=answerform&boardno=${dto.boardno }'"><!-- 글번호보내기 -->
				</td>
			</tr>
		</table>
</body>
</html>