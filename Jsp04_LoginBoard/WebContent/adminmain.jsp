<%
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Expires","0");
	//캐시에 저장 못하게 함
/*
	뒤로가기 했을때, 이전 화면이 보이는 이유
	->서버에서 문서를 받아오는 것이 아니라, 캐시에 저장된 값을 화면에 뿌려줌

	브라우저가 캐시에 응답결과(response)를 저장하지 않도록 설정
	응답객체의 헤더에 저장해주는것 
	response.setHeader("Pragma","no-cache");		//http 1.0
	response.setHeader("Cache-controle", "no-store");	//http 1.1
	response.setHeader("Expires","0");			//proxy server
	
	우리는 : http 1.1
*/
%>

<%@ page import="com.login.dto.MYMemberDto"%>
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
 	MYMemberDto dto =(MYMemberDto)session.getAttribute("dto");
	
	if (dto ==null){
		pageContext.forward("index.html");
		//dto에 값이 없으면 index로 보내쟈
		//sendRedirect로 해도 가능!!
	}
%>
	
	<div>
		<!-- 뒤로가기 누르면 캐시에 저장못하게 하면 dto에 값이 없어서 null-session에 있는값을 삭제해서 500뜨게됨 -->
		<span><%=dto.getMyid() %>님 환영합니다</span>
		<a href="logincontroller.jsp?command=logout">logout</a>
		
	</div>
	
	<div>
		<div>
			<a href = "logincontroller.jsp?command=listall">회원 전체 조회 (탈퇴한 회원 포함)</a>
		</div>
		<div>
			<a href = "logincontroller.jsp?command=listenabled">회원 전체 조회 (MYENABLED=Y)</a>
		</div>
	</div>

</body>
</html>