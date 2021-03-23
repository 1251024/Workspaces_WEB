<%@page import="java.util.List"%>
<%@page import="com.login.dto.MYMemberDto"%>
<%@page import="com.login.biz.MYMemberBiz"%>
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
	//index에서 값 3개 command 로그인, myid, mypassword
	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	
	MYMemberBiz biz = new MYMemberBiz();
	
	if(command.equals("login")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		
		MYMemberDto dto = biz.login(myid, mypw);
		
		if(dto != null){
			//session scope에 객체 담기
			session.setAttribute("dto", dto);
			//만료되는 시간 설정 (default:30분)
			//서버가 클라이언트 하나에 대해서 관리함
			session.setMaxInactiveInterval(10 * 60);
			//시간안정해두면 디폴트30분 시간안에서 값을 전달함
			
			//세션은 브라우저 정보를 담고 있음
			//세션은 만료되기 전까지 프로젝트 어디에서든 사용 할 수 있음
			//세션과 쿠키의 차이 공부하기
			
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
				
			}else if (dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
			
		}else{
	
%>
		<script type="text/javascript">
			alert("로그인 실패");
			location.href="index.html";
		</script>
<%			
			
		}
	}else if(command.equals("logout")){
		//session scope에서 값 삭제 (만료)-세션에 넣어둔 값이 없어지게 함
		session.invalidate();
		response.sendRedirect("index.html");
		//로그아웃되있는데, 세션값은 날라갔는데 캐시엔 남아있어서 
		
		
	}else if(command.equals("listall")){
		//1. 보내준 값이 있으면 받는다.
		
		//2. db에 값전달 및 리턴
		List<MYMemberDto> list = biz.selectAllUser();

		//3. 화면에 전달할 값, request에 담기
		request.setAttribute("list", list);
		
		//4.보낸다
		pageContext.forward("userlistall.jsp");
		
	}else if(command.equals("listenabled")){
		//1.
		
		//2.호출해서 리턴받을 값
		List<MYMemberDto>list= biz.selectEnabledUser();
		
		//3.
		request.setAttribute("list",list);
		
		//4.
		pageContext.forward("userlistenabled.jsp");
		
	}else if (command.equals("updateroleform")){
		//1.myno보냄
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		//2.리턴받자
		MYMemberDto dto = biz.selectUser(myno);
		
		//3.
		request.setAttribute("dto", dto);		
		
		//4.
		pageContext.forward("updaterole.jsp");
		
	}else if(command.equals("updaterole")){
		int myno =  Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		int res = biz.updateRole(myno, myrole);
		if(res>0){
%>
		<script type="text/javascript">
			alert ("등급 변경 성공");
			location.href="logincontroller.jsp?command=listenabled";
		</script>
<%		
		
		}else{
		
%>
		<script type="text/javascript">
			alert("등급 변경 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno %>";
		</script>

<%		
		}
	}else if(command.equals("registform")){
		response.sendRedirect("regist.jsp");
		
	}else if(command.equals("idchk")){
		String myid = request.getParameter("myid");
		//db에 전달 =id중복체크 해당 값이 있는지 없는지 체크할 것
		MYMemberDto dto = biz.idCheck(myid);
		boolean idnotused = true;
		
		if(dto.getMyid() != null){
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
		
	}else if(command.equals("insertuser")){
		
		//값 받아오기
		//타입 변수 =값;
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		
		//값 저장
		//객체생성 참조타입 변수 = new 참조타입
		MYMemberDto dto = new MYMemberDto();
		
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);

		int res = biz.insertUser(dto);//정상적으로 들어갔으면 1이상이 됨
		
		if(res>0){
			
%>
		<script type="text/javascript">
			alert("가입 성공");
			location.href="index.html";
		</script>
<%			
		} else {

%>
		<script type="text/javascript">
			alert("가입 실패");
			location.href="regist.jsp";
		</script>
			
<%				
		}

	}else if(command.equals("updateuser")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		//request.getParameter
		//form의 name이 지정된 value값, href 주소 뒤에 따라오는 변수들을 가져올 수 있다.

		MYMemberDto dto = biz.selectUser(myno);
		
		request.setAttribute("dto", dto);

		
		pageContext.forward("updateuserform.jsp");
		//sendRedirect는 session으로 값을 가져온다
		
	}else if(command.equals("updateuserform")){
		
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MYMemberDto dto = new MYMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		dto.setMyno(myno);
		
		int res = biz.updateUser(dto);
		
		if(res>0){
%>
			<script type="text/javascript">
				alert("수정 성공");
				location.href="index.html";
			</script>
<%			
			} else {

%>
			<script type="text/javascript">
				alert("수정 실패");
				location.href="usermain.jsp";
			</script>
				
<%			

			}
	}

%>




<h1 style ="color:red;">잘못왔다...</h1>

</body>
</html>