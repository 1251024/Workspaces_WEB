package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;

import net.sf.json.JSONObject;

//서블릿 여러개 있어도 되지만, 연결되는 url이 달라야한다
//count ajax 전용 컨트롤러
@WebServlet("/count.do")
public class CalCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String yyyyMMdd = request.getParameter("yyyyMMdd");
		System.out.printf("id: %s / yyyyMMdd : %s \n", id, yyyyMMdd);
		
		CalDao dao = new CalDao();
		int count = dao.getCalViewCount(id, yyyyMMdd);
		System.out.println("count: "+count);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("calcount", count);
		
		//map -> json
		JSONObject obj = JSONObject.fromObject(map);	//map가지고 json객체로 바꿔줌
		
		//response
		PrintWriter out = response.getWriter();
		obj.write(out);			//syso println대진 write를 썻다
		
		
	}

}
