<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
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
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>) request.getAttribute("list");//형 변환-Type mismatch: cannot convert from Object to List<MVCBoardDto>
	//controller에서 request객체에 list라는 이름으로 list를 set햇음 
	//setAttribute는 무조건 object로 감싸서 가져옴
	//request내용을 가져올때 무슨타입인지 몰라서 object로 받음(오브젝트가 최상위객체)object타입을 형 변환해줌->엄마는 자식거쓸수있지만 자식타입에 부모객체를 넣을 순 없어서 명시적형변환

%>
<body>

	<h1>list</h1>
	
	<table border = "1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
		for(MVCBoardDto dto :list){
%>		
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href = "myservlet.do?command=select&seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
<%

		}
%>		
		<tr>
			<td colspan = "4"align="right">
				<input type = "button"value = "글작성" onclick="location.href='myservlet.do?command=insertform'">
			</td>
		</tr>
	</table>

</body>
</html>