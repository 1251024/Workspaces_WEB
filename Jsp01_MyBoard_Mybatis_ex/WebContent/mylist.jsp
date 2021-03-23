<%@page import="com.board.dao.MyBoardDao"%>
<%@page import="com.board.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<% 
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	MyBoardDao dao = new MyBoardDao();
	List<MyBoardDto> list = dao.selectList();	//dto를 가져오기
	
%>
<body>

	<h1>selectList</h1>
	
	<table border ="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
	for(MyBoardDto dto : list){
%>
		<tr>
			<th><%=dto.getSeq() %></th>
			<th><%=dto.getWriter() %></th>
			<th><a href="myselect.jsp?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></th>
									<!-- ?seq 이후는 해당 seq에 맞는 쿼리하나 key=value 해당 로우만 보려고-->
			
			<th><%=dto.getRegdate() %></th>
		</tr>
	
	
<%
	}
%>	
		<tr>
			<td colspan="4">
				<input type="button" value="글작성" onclick="location.href='myinsert.jsp'"/>
			</td>
		</tr>
	</table>
	

</body>
</html>