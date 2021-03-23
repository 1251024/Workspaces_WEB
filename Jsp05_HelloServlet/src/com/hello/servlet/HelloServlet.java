package com.hello.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//web.xml 설정을 annotation으로 간략화 시켜준것
//controller.do하면 HelloServlet이 받아줄거야 / 패턴은 맘대로 쓸수 있음
//@WebServlet클래스이름으로 객체 만들어줌 익명으로 서블릿 네임, 클래스를 불러오는것
//"/controller.do"는 서블릿 매핑을 가져오는 것
//설정잡아준것을 자동으로 객체 생성 , 만들어준 객체에 자동으로 연결
//controller.do 는 url 매핑controller.do 호출하면 아래 객체를 호출
@WebServlet("/controller.do")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	private String contextParam;
	private String initParam;
	
	
	//생성자, 슈퍼 지워도 슈퍼 호출
    public HelloServlet() {
    	System.out.println("servlet constructor");
    }
    
    
    //컨트롤+스페이스 init파라미터 있는 메소드 호출해서 오버라이드한다
    @Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet init");
		
		contextParam = config.getServletContext().getInitParameter("name");
		initParam = config.getInitParameter("sports");
		System.out.println("context-param :"+contextParam);
		System.out.println("init-param :"+initParam);
	}
	

    //이전 페이지에서 어떤방식으로 요청했느냐에 따라 청했느냐에 따라 겟이나 포스트로 호출해서 응답해준다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿은 컨트롤러
		//서블릿할때 무조건 해야할 것
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Get 방식으로 들어옴!");
		
		//1.
		String command = request.getParameter("command");
		System.out.println("command:"+command);
		System.out.println("hello.do로 생성된 서블릿 객체");
		
		//2.
		
		//3.
		
		//4.다음페이지 전달 서블릿에서 페이지만들기
		PrintWriter out = response.getWriter();//java.io//응닶하는 객체에 값써줌
		out.print("<h1 style='color:red'>Hello Servlet</h1>");
		out.print("<h2> 계층 구조, lifecycle, url-mapping</h2>");
		out.print("<a href='home.html'>home...</a>");
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿할때 무조건 해야할 것
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Post 방식으로 들어옴!");
		
		//1.
		String command = request.getParameter("command");
		System.out.println("command:"+command);
		System.out.println(" @annotation을 통해 생성된 서블릿 객체");

		//2.
		
		//3.
		
		//4.
		PrintWriter out = response.getWriter();
		out.println("<h1 style='color:blue'>Hello Servlet</h1>");
		out.println("<h2>init - service - doGet/doPost - destroy</h2>");
		out.println("<a href = 'home.html'>home...</a>");
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet destroy");//doget이나 dopost는 컨트롤러
		
	}

}
