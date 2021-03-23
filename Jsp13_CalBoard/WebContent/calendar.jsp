<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="com.cal.common.Util"%>
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

<style type="text/css">
	#calendar{
		border-collapse: collapse;
		border: 1px solid gray;
	}
	#calendar th{
		width: 80px;
		border: 1px solid gray;
	}
	#calendar td{
		width: 80px;
		height: 100px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	a{
		text-decoration: none;
	}
	
	.list>p{
		font-size: 5px;
		margin: 1px;
		background-color: skyblue;
	}
	
	.preview{
		position: absolute;
		top: -30px;
		left: 10px;
		background-color: skyblue;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}

</style>

<script type="text/javascript" src = "https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
	function isTwo(n){
		return (n.length<2)?"0"+n : n;
	}


	$(function() {
		$(".countview").hover(function() {
			// handle in
			var countView = $(this);
			var year = $(".y").text().trim();//밑에 year 가져올건데 혹시몰라서 trim
			var month = $(".m").text().trim();
			var date = countView.text().trim();
			var yyyyMMdd = year + isTwo(month) + isTwo(date);
			
			$.ajax({
				type: "post",
				url: "count.do?id=kh&yyyyMMdd="+yyyyMMdd,
				dataType: "json",
				async: false,
				success: function(msg) {
					var count = msg.calcount;
					countView.after("<div class='preview'>" + count +"</div>")
				},
				error: function() {
					alert("통신 실패");
					
				}
				
			});
		},
		function() {
			// handle out
			$(".preview").remove();
		
		});
	});
</script>

</head>
<body>
	<%
/*
new()와 getInstance()의 차이

new()            : 객체를 계속계속 만들 수 있다.
getInstance()  : 싱글턴패턴, 하나의 인스턴스만 가지고 공유해서 쓴다.

싱글턴패턴: 생성자를 private로 선언하여 다른클래스에서 해당 클래스의 인스턴스를 
new로 생성하지 못하게 하고, getInstance()함수를 통해서 인스턴스를 갖도록 한다
	
*/
//생성자가 private으로 되있으면 new로 객체를 만들어줄 수 없음
//객체를 만들어주는 인스턴스 getInstance만듦

	//Calendar cannot be resolved: import 안된것, 자동완성 컨트롤 + 스페이스로 하기
	Calendar cal = Calendar.getInstance();	//Calendar함수가 자동으로 sysdate 현재 날짜가 들어간다..
		
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1; //0~11이라서 +1을 해주어야 함

	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");	//버튼을 눌러서 다른 연도 다른 월을 요청했을때 요청값 받는것

	if (paramYear != null) {
		year = Integer.parseInt(paramYear);	//값이 있다면 paramYear값을 덮어씌우겠다.

	}
	if (paramMonth != null) {
		month = Integer.parseInt(paramMonth);//값이 있다면 paramMonth값을 덮어씌우겠다.
	}
	if (month > 12) {	//연도가 12보다 크면(13이되면)
		month = 1;		//월은 1이고
		year++;			//연도가 추가된다.
	}
	if (month < 1) {	//월이 1보다 작으면(0이 되면)
		month = 12;		//월은 12가 되고
		year--;			//연은 -1하게 된다.
	}
	
	cal.set(year, month-1, 1);		//바뀐날짜값을 세팅한다 //해당 년도, 해당월의 첫날기준
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);			//월화수목금 가져오는건데 숫자로 가져옴 1~7/일요일이 1
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);	//해당 달의 가장 맥시멈값=마지막 날
	
	CalDao dao = new CalDao();		//다오 객체 만들어서 메소드 쓰겟다
	String yyyyMM = year + Util.isTwo(String.valueOf(month));	//year와 util의 isTwo메소드를 가져온다,String.valueOf를 써서 숫자를 문자로 바꾼다 
																//year가 int고 넘어온데이터는 문자열이라서 앞에서 String yyyyMM으로 강제로 정해줌
	List<CalDto> list = dao.getCalViewList("kh", yyyyMM);		//제목리스트 값 가져오는 거
	//돌아오는 값 List<데이터타입>
	
	%>

	<table id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◁</a> 
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◀</a> 
			
			<span class="y"><%=year %></span>년 
			<span class="m"><%=month %></span>월 
			
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▶</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▷</a>
		</caption>

		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		
		<tr>
<%
		//dayOfWeek:시작요일의 숫자리턴//예를 들면 시작요일이 3일때 -2만큼 돌고 , 0때 한번 1때 한번 공백추가
		for (int i = 0; i < dayOfWeek-1; i++){
			out.print("<td></td>");				//공백 넣기
		}
		for (int i =1;i <= lastDay; i++){	//마지막날과 같거나 크면
%>		
			<td>
				
				<!-- 해당날짜 누르면 그 일자의 일정 다 보여주기 -->
				<a class="countview" href = "cal.do?command=list&year=<%=year %>&month=<%=month %>&date=<%=i %>" style ="color:<%=Util.fontColor(i, dayOfWeek)%>"><%=i %></a>
									
				<a href = "insert.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastDay=<%=lastDay%>">
					<!-- i개수만큼 펜img 넣어주기 -->
					<img alt="" src="image/pen.png" style="width: 10px; height: 10px;">
				</a>
				<div class="list">
					<%=Util.getCalView(i, list) %>
					<!-- div안에 p태그 추가하고 list로 만들어준다 -->
				</div>
			</td>
<%
			//7로 나눴을때 0이 되는 순간 다음줄로 넘어가게 한다
			//dayOfWeek:시작요일의 숫자리턴 일1 월2 화3 수4 목5 금6 토7
			//시작요일이 월요일이면 2를 리턴하고 1부터 시작해서 -1 +선택된 날짜
			//
			if((dayOfWeek-1+i)%7 ==0){
				out.print("</tr><tr>");
			}
		}
		//7-마지막날짜만큼 공백을 채워준다.  (7-(시작요일-1+마지막날짜)/7)/7 
		//마지막이 토요일인 경우 빈칸이(td가) 7칸이 생겨서 0으로 만들기위헤서 마지막 나누기 7함
		for (int i = 0; i < (7-(dayOfWeek - 1 + lastDay)%7)%7; i++){	//마지막 공백 만드는 for문 //7일인 경우엔 0보다 작게 만들어서 안돌아가게
			out.print("<td></td>");
		}
%>		
		</tr>
		

	</table>

</body>
</html>