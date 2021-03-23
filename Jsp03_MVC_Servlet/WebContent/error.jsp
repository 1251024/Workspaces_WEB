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
<title>kh</title>
</head>
<body>
	
	<h2>에러 발생 :<%=request.getAttribute("message") %></h2>
	<a href="/kh/index.jsp">홈으로</a>
	<a href="javascript:history.go(-1);">이전 페이지 이동</a>

</body>
</html>