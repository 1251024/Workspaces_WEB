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
<%

	MYMemberDto dto = (MYMemberDto)request.getAttribute("dto");

%>
<body>

	<h1>수정</h1>

	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateuserform">
		<input type="hidden" name="myid" value="<%=dto.getMyid() %>">
		<input type="hidden" name="myno" value="<%=dto.getMyno() %>">

		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<%=dto.getMyid() %>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type = "password" name = "mypw" required="required" value = "<%=dto.getMypw() %>">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type = "text" name = "myname" required="required"value = "<%=dto.getMyname() %>">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type = "text" name = "myaddr" required="required" value = "<%=dto.getMyaddr() %>">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type = "text" name = "myphone" required="required"value = "<%=dto.getMyphone() %>">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type = "text" name = "myemail" required="required"value = "<%=dto.getMyemail() %>">
				</td>
			</tr>
			
			<tr>
				<th>사용여부</th>
				<td>
					<%=dto.getMyenabled() %>
				</td>
			</tr>
			
			<tr>
				<th>등급</th>
				<td>
					<%=dto.getMyrole() %>
				</td>
			</tr>
			<tr>
				<td colspan="2" align = "right">
					<input type = "submit" value="수정">
					<input type = "button" value="취소">
				</td>
			</tr>
		</table>

	</form>

</body>
</html>