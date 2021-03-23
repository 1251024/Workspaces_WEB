package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScopeController() {
        super();
    }

	//get, post든 상관없이 get으로 처리 
    //doget은 컨트롤러 역할
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String requestId = request.getAttribute("requestId")+"";
		
		HttpSession session =request.getSession();
		String sessionId = session.getAttribute("sessionId")+"";
		
		ServletContext application = getServletContext();
		String applicationId = application.getAttribute("applicationId")+"";
		
		System.out.println("requestId:"+requestId);
		System.out.println("sessionId:"+sessionId);
		System.out.println("applicationId :"+applicationId);
		
		String myRequest = request.getParameter("myRequest");
		System.out.println("myRequest:"+myRequest);
		
		PrintWriter out = response.getWriter();
		String html = "<h1>응답</h1>"
					+"<table border = '1'>"
						+"<tr>"
							+"<th>request</th>"
//							+"<td>"+ requestId +"</td>"
							//1. requestId는 왜 null일까? 
							//index.jsp의 setAttrubute 와 controller의 getAttrubute는 서로 다른애다. (인덱스와 컨트롤러의 각각의 리퀘스트 객체에 담아지기 때문)
							//요청하고 응답받는 객체가 다름 - index.jsp객체에서 setAttribute 하고 controller servlet에서 getattribute 해서 값이 없다 null 뜸
							//setAtribute는 값을 담아두는 역할
							+"<td>"+ myRequest +"</td>0"
							//2. myRequest는 왜 나올까?
							//atrribute는 리퀘스트 객체에 값을 담아둘거다 라는 의미이고, Parameter는 값을 전달하는 거다. 그래서 파라미터는 컨트롤러가 값을 가지고 있기 때문에 출력이 가능하다. K = V로 전달.
							//scope.do로 값을 전달하자
							//파라미터는 k=v형태로 바로 전달해줌 -> name 속성에 있는게 k, value속성에 있는게 v
							//--------------
							//파라미터는 외부에서 전달하는 값을 받아줄 변수/아규먼트는 외부에서 전달하는 값
							//form태그 사용해서 scope.do로 전달
							//scope.do라는 목적지로 이동시키라는 명령이 있어서 파라미터로 전달된것
							//---------------값을 담아두는것과 전달 하는 것의 차이
							//setAtribute는 값을 담아두는 역할-객체를 담을 수 있음 , 어떤 타입일진 모를때 Object
							//getAttribute :담아둔거 가져오기 / set해야 get 할 수 있음
							//pageContext, request, session Attribute에 있음 object로 감싸서 넣음
							//----------------
							//*.do?k=v 
							//<form action = "*.do">
							//getParameter:전달된거 받아오기-k=v로 문자열
							//-------------
							//?k=v 로 하든 form으로 하든submit이나 해당 a태그를 클릭하는 순간  
							//->일단 그 값이 같이 다음 페이지로 전달된다 라고 해석하면 될 것 같음 
							//->request.getParameter로 그래서 가져올수 있는거고
						+"</tr>"
						+"<tr>"
							+"<th>session</th>"
							+"<td>"+ sessionId +"</td>"
						+"</tr>"
						+"<tr>"
							+"<th>application</th>"
							+"<td>"+applicationId+"</td>"
						+"</tr>"
					+"</table>";
		out.println(html);
		
		String test = request.getParameter("mytest");
		System.out.println("test:"+test);
		//자바 저장하면 서블릿이 다시 로드되야함-서버껏다키면 자동으로 다시 로드 됨
		//서블릿이 디스트로이 되고 이닛 된것
		
		request.setAttribute("myRequest", "servlet에서 보냄");//forward라서 result에 위임함
		
		//request와 response를 전달
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
		
		//3.RequestDispatcher뭐하는 애인가
		//request와 response를 전달
		//forward로 전달하는 방식과 include로 전달하는 방법이 있음
		
		//4.controller에서 myRequest는 나오는데 result에서 myRequest는 왜 null일까
		//forward(위임)을 했는데 그 전에 값을 담아주지를 않았기 때문에 담겨있지 않은 상태로 스코프컨트롤러에서 리절트로 전달된거다. 만약 스코프컨트롤러에서 setAtrribute로 담아주면 화면에 출력된다.

		//index에서 요청request했으면 응답해야함response
		//요청하는 객체도 request객체고 응답하는 객체도 response객체임 => 객체들 만들어질때는 요청할때 만들어짐
		//forward해서 받는놈은 있는데 주는놈이 없어요

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
