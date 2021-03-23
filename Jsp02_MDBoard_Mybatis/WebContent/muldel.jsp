<%@page import="com.muldel.biz.MDBoardBizImpl"%>
<%@page import="com.muldel.biz.MDBoardBiz"%>
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

	//똑같은 파라미터의 값을 가져올때 여러개 가져오면 그값을 배열에 넣어줄거야
	String[] seqs = request.getParameterValues("chk");

	if(seqs == null || seqs.length == 0){
%>
	<script type="text/javascript">
		alert("삭제할 글을 선택해주세요");
		location.href="boardlist.jsp";
	</script>		
<%
	}else{
		MDBoardBiz biz=new MDBoardBizImpl();
		int res = biz.multiDelete(seqs);
		if(res > 0){
%>
	<script type="text/javascript">
		alert("체크된 글들을 삭제 성공했습니다.");
		location.href="boardlist.jsp";
	</script>
<%
		}else{
%>
	<script type="text/javascript">
		alert("체크된 글들을 삭제 실패했습니다.");
		location.href="boardlist.jsp";
	</script>
<%
		}
	}
		
%>
</body>
</html>