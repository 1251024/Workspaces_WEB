package com.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/ScoreController")
public class ScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScoreController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//값 전달됨
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));

		//받은걸 연산함
		int sum = kor + eng + math;
		double avg = (double) sum / 3;
		
		//json :경량데이터 자바스크립트 Notation (자바스크립트 참고, 라이브러리)
		//받은걸 json 객체로 만들어줌
		//json-simple-1.1.1.jar
		//json object를 만들자
		JSONObject obj = new JSONObject();
		//json객체 -객체는 heap영역에 저장됨//object를 리턴하면 주소값이 리턴됨
		//json object타입으로 바꿔서 넣어줌
		
		obj.put("name", name);
		obj.put("sum", sum);
		obj.put("avg", String.format("%.2f", avg));
		// {"name":name, "sum":sum, "avg":avg}<-요 형태가 됨
		//json형태의 문자열로 리턴
		
		//toJSONString():json형식의 문자열로 만들자.
		System.out.println("server에서 보내는 데이터 : "+obj.toJSONString());

		PrintWriter out =response.getWriter();
		out.print(obj.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
