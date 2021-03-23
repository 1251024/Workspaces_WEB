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

	<h1>useBean을 통한 객체 생성!</h1>
	
	<!-- Score sc = new Score();와 똑같은 역할 -->
	<!-- bean = class, instance -->
	<!-- java에서 instance를 사용하겠다 -->
	<!-- com.el.dto.Score클래스를 sc라는 이름으로 객체 사용할 거야 , scope 세션은 세션에 저장됨-->
	<jsp:useBean id = "sc" class = "com.el.dto.Score" scope = "session"></jsp:useBean>
	<!-- session scope에 sc라는 이름의 값이 없으면 객체생성해서 담아두자 -->
	

	<!-- setProperty는 게터세터 -->
	<!-- sc.setName("홍길동"); -->
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	
	<!-- sc.getName(); -->
	<jsp:getProperty property="name" name="sc"/>
	
	<button onclick = "location.href = 'result.jsp'" value = "result">result</button>
	
</body>
</html>