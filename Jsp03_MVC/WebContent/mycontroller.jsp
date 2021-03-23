<%@page import="com.mvc.dao.MVCBoardDaoImpl"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBoardBizImpl"%>
<%@page import="com.mvc.biz.MVCBoardBiz"%>
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
	String command = request.getParameter("command");//index의 값을 받음
	System.out.printf("[%s]\n", command);

	MVCBoardBiz biz= new MVCBoardBizImpl();
	
	//요청한 명령을 확인한다. (앞으로 컨트롤러에서 해야하는 것)
	if (command.equals("list")){
		//1. 보내준 값이 있으면, 받는다. 
		//	index에서 보낸값이 없어서 1번은 없다
		
		
		//2. db에 전달할 값이 있으면 전달하고, 
		//	없으면 없는대로 호출해서 리턴 받는다.
		List<MVCBoardDto> list = biz.selectList();
		
		
		//3. 화면에 전달할 값이 있으면, request 객체에 담아준다.
		request.setAttribute("list", list);//변수명, 들어갈 값
		// 위의 setCharacter request랑 같은애 임
		// request 는 값 저장
		
		
		//4. 보낸다.
		pageContext.forward("mylist.jsp");		//보내야할 값이 있을때만 씀
		//forward가 mycontroller에서 view를 띄움
		//request 객체에 값을 넣어놨기 때문에
		//controller를 요청했으면 controller가 응답되야하는데 forward 해서 insert 객체가 대신 응답된다

		
	} else if (command.equals("insertform")){
		//1.
		//2.
		//3. sendRedirect 는 값을 담을 수 없다
		//	requset받아서 reponse 응답하다가 myinsert로 다시 보내기때문에 
		//	requet, response 객체가 다른거기때문에 값을 전달 할 수 없다.
		//4.
		response.sendRedirect("myinsert.jsp");//sendRedirect는 화면전환//다시보낸다//새로운 request, 새로운 response가 됨
		
		/*
			pageContext.forward():페이지 위임(request, response 객체가 그대로 전달) -리퀘스트에 뭐 담았을때 포워드하기
			response.sendRedirect():페이지 이동 (새로운 request, response 객체)
		*/
		
	}else if(command.equals("insertres")){
		//1.
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//2.
		MVCBoardDto dto = new MVCBoardDto(0, writer, title, content, null);
		int res = biz.insert(dto);
		
		//3.
		
		//4.
		if(res>0){
%>
		<script type="text/javascript">
			alert("글 작성 성공");
			location.href='mycontroller.jsp?command=list';
		</script>
<%

		}else{
			
%>			
		<script type="text/javascript">
			alert("글 작성 실패");
			location.href='mycontroller.jsp?command=insertform';
		</script>
<%

		}
	}else if (command.equals("select")){

		//1.보내준 값이 있으면 받는다.
		int seq = Integer.parseInt(request.getParameter("seq"));
		//쿼리스트링에서 가져오는 것
		
		//2.db에 전달할 값이 있으면 전달하고 없으면 없는대로 호출해서 리턴
		MVCBoardDto dto = biz.selectOne(seq);
		//변수명에 biz쿼리를 담는다
		

		//3.화면에 전달할 값이 있으면, request객체에 담아준다.
		request.setAttribute("dto", dto);
		//변수명, 값
		
		//4.보낸다.
		pageContext.forward("myselect.jsp");


	}else if(command.equals("updateform")){
		//1.
		int seq = Integer.parseInt(request.getParameter("seq"));

		//2.
		MVCBoardDto dto = biz.selectOne(seq);

		//3.
		request.setAttribute("dto",dto);
		
		//4.
		pageContext.forward("myupdate.jsp");
		
	}else if(command.equals("updateres")){
		//1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//2.
		MVCBoardDto dto = new MVCBoardDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);
		
		//3.
		
		//4.
		if(res>0){
%>
		<script type="text/javascript">
			alert("수정성공");
			location.href="mycontroller.jsp?command=select&seq=<%=seq%>";
		</script>
<%
			
			
		}else{
%>
		<script type="text/javascript">
			alert("수정실패");
			history.back();//뒤로가기
		</script>
<%	
		}
		
		
	}else if(command.equals("delete")){
		//1. 값 받기
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		//2. 
		int res = biz.delete(seq);
		//3.
		
		//4.
		if (res>0){
		
%>
		<script type="text/javascript">
			alert("글 삭제 성공");
			location.href='mycontroller.jsp?command=list';
		</script>
<%

		}else{
			
%>			
		<script type="text/javascript">
			alert("글 삭제 실패");
			location.href='mycontroller.jsp?command=select&seq=<%=seq%>';
		</script>
<%			
		
		}
		
	}
%>

</body>
</html>