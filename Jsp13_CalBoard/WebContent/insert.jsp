<%@page import="java.util.Calendar"%>
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
	//calendar에서 값을 바다옴 //클릭한 시간을 가져옴
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));
	int date = Integer.parseInt(request.getParameter("date"));
	int lastDay = Integer.parseInt(request.getParameter("lastDay"));
	
	//캘린더객체를 만들어서 hour와 min을 가져옴(현재시간)
	Calendar cal = Calendar.getInstance();
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	int min = cal.get(Calendar.MINUTE);
	
%>

	<h1>일정 작성</h1>
	
	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="insert">
		<!-- cal.do서블릿을 호출 -->
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" value="kh" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>일정</th>
				<td>
				<select name="year">
<%
				//선택된 년도 가 트루값이 될때 선택되고 나머지 옵션은 선택되지 않는다
				for (int i = year-5; i<year+6; i++){
%>				
					<option value="<%=i%>" <%=(year==i)?"selected": "" %> ><%=i %></option>
<%
				}
%>				
				</select>년
				<select name="month">
<%
				for (int i = 1; i < 13; i++){

%>				
					<option value="<%=i%>" <%=(month==i)?"selected": "" %> ><%=i %></option>
<%
				}
%>				
				
				</select>월
				<select name="date">
<%
				for (int i = 1; i <= lastDay; i++){
%>				
					<option value="<%=i%>" <%=(date==i)?"selected": "" %> ><%=i %></option>
<%
				}
%>				
				</select>일
				<select name="hour">
<%
				for (int i = 0; i<24; i++){
%>
					<option value="<%=i%>"<%=(hour==i)? "selected":""%>><%=i %></option>
<%
				}
%>				
				</select>
				<select name="min">
<%
				for(int i = 0; i< 60; i++){
%>				
					<option value="<%=i %>"<%=(min ==i)? "selected":"" %>><%=i %></option>
<%
				}
%>				
				</select>분
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="일정작성"/>
					<input type="button" value="취소" onclick=""/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>