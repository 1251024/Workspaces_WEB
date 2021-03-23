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
	function idCheckProc(){
		var chk=document.getElementsByName("myid")[0].title;
		if(chk == 'n'){
			alert("id 중복체크를 해주세요!");
			document.getElementsByName("myid")[0].focus();	
			//id 중복체크 먼저하고 적을 수 있게
		}
	}
	
	function idCheck(){
		var myid = document.getElementsByName("myid")[0].value;
		
		if(myid == null|| myid.trim()==""){
			alert("id를 입력해주세요!");
		}else{
			open("logincontroller.jsp?command=idchk&myid="+myid,"","width=300, height=300");
		}
	}
	
</script>

</head>
<body>

	<h1>회원가입</h1>

	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="insertuser">

		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<!-- required 속성 알아보기 -->
					<input type = "text" name = "myid" required="required" title="n">
					<input type = "button" value = "중복 체크" onclick="idCheck();">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type = "password" name = "mypw" required="required" onclick="idCheckProc();">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type = "text" name = "myname" required="required" onclick="idCheckProc();">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type = "text" name = "myaddr" required="required" onclick="idCheckProc();">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type = "text" name = "myphone" required="required" onclick="idCheckProc();">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type = "text" name = "myemail" required="required" onclick="idCheckProc();">
				</td>
			</tr>
			<tr>
				<td colspan="2" align = "right">
					<input type = "submit" value="가입">
					<input type = "button" value="취소">
					<!-- form태그안에서 버튼태그는 submit 이벤트를 기본적으로 만들어놓음 -->
				</td>
			</tr>
		</table>

	</form>

</body>
</html>