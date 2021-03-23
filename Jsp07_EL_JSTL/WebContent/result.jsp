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
	<!-- session scope에 sc라는 이름의 값이 있으면 sc라는 변수에 담아두자 -->
	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	
	<h1><jsp:getProperty property="name" name="sc"/></h1>
	
	
	<h1>${sc.name }</h1>
	<!-- session에 있는거 바로 갖다쓰기 -->

</body>
</html>