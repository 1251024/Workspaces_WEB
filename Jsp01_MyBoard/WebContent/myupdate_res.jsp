<%@page import="com.board.dao.MyBoardDao"%>
<%@page import="com.board.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;Charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MyBoardDto dto = new MyBoardDto(seq, null, title, content, null);//작성자랑 작성일은 수정안하기로 해서, 받아온것도 없고 데이터베이스에서 가져온것도 없음
	MyBoardDao dao = new MyBoardDao();	

	int res = dao.update(dto);
	if(res>0){
%>
	<script type="text/javascript">
		alert("수정 성공");
		location.href="myselect.jsp?seq=<%=seq%>";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("수정 실패");
		location.href="myupdate.jsp?seq=<%=seq%>";
	</script>

<%
	}
%>
</body>
</html>