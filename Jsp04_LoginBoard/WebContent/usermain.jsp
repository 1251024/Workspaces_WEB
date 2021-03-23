<%@page import="com.login.dto.MYMemberDto"%>
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
	MYMemberDto dto = (MYMemberDto)session.getAttribute("dto");
	int myno = dto.getMyno();

%>
	<div>
	 	<span><%=dto.getMyid() %>님 환영합니다</span>
		<a href = "logincontroller.jsp?command=logout">logout</a>
	</div>
	
	<div>
		<div>
			<a href = "logincontroller.jsp?command=updateuser&myno=<%=myno %>">내 정보 수정</a>
		</div>
	</div>
	

</body>
</html>