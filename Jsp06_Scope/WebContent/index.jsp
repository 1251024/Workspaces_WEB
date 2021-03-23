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

<%
	pageContext.setAttribute("pageId", "my pageContext value");
	request.setAttribute("requestId", "my request value");
	session.setAttribute("sessionId", "my session value");
	application.setAttribute("applicationId", "my application value");
%>
	<h1>INDEX</h1>

	pageId:<%=pageContext.getAttribute("pageId") %><br>
	requsetId:<%=request.getAttribute("requestId") %><br>
	sessionId:<%=session.getAttribute("sessionId") %><br>
	applicationId: <%=application.getAttribute("applicationId") %><br>
	
	<a href = "result.jsp">result</a><br>
	<!-- 객체가 달라서, 리퀘스트가 달라서 -->
	
	<a href = "scope.do?mytest=1">test</a>
	
	<form action="scope.do"method="post">
		<input type = "hidden" name = "myRequest" value="my request value 2"><!-- myRequest 값을 통해서 값 전달 -->
		<!-- 파라미터는 외부에서 전달하는 값을 받아줄 변수/아규먼트는 외부에서 전달하는 값 -->
		<input type = "submit" value="controller">
	</form>

	<%-- <% pageContext.forward("scope.do"); %> --%>
	<!-- !주석은 html이라서 자바영역 응답된 이후에 응답된다 그래서 자바영역은 %주석으로 해줘야함-->
	<!-- servlet에 위임할것->servlet에 request가 위임됨-> result 페이지가 나옴- >setAttribute가 있어서 값이 나옴   -->
	
</body>
</html>