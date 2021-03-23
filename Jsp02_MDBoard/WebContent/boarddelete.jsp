<%@page import="com.muldel.dao.MDBoardDaoImpl"%>
<%@page import="com.muldel.dao.MDBoardDao"%>
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
	int seq= Integer.parseInt(request.getParameter("seq"));
	MDBoardDao dao = new MDBoardDaoImpl();
	
	int res = dao.delete(seq);
	if(res>0){
%>
	<script type="text/javascript">
		alert("삭제 성공");
		location.href="boardlist.jsp";
	</script>
	
<%
	}else{
%>
	<script type="text/javascript">
		alrert("삭제실패");
		location.href="boradselect.jsp?seq=<%=seq%>";
	</script>

<%
	}
%>
</body>
</html>