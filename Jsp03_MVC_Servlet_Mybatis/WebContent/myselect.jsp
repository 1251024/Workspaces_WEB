<%@page import="com.mvc.dto.MVCBoardDto"%>
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

<script type="text/javascript">

	function deleteProc(seq){
		if(confirm(seq+"번 글을 삭제하시겠습니까?")){
			location.href = "myservlet.do?command=delete&seq="+seq;
		}
	}
</script>



</head>
<%
	MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");
	//request.setAttribute("dto", dto); -> 컨트롤러에서 request객체에 담은 값을 가져온다
	//명시적으로 변수명과 같은타입으로 해주기 위해서 형변환해준다
	System.out.println(dto.getWriter());

%>
<body>

	<h1>selectOne</h1>
	
	<table border = "1">
	
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
		</tr>
		
		<tr>
			<td colspan = "2" align="right">
				<input type="button" value="목록" onclick="location.href='myservlet.do?command=list'">
				<input type="button" value="수정" onclick="location.href='myservlet.do?command=updateform&seq=<%=dto.getSeq() %>'">
				<input type="button" value="삭제" onclick="deleteProc(<%=dto.getSeq() %>);">
			</td>
		</tr>
	
	</table>

</body>
</html>