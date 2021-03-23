package com.cal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.common.Util;
import com.cal.dao.CalDao;
import com.cal.dto.CalDto;

@WebServlet("/cal.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.printf("<%s>\n",command);
		
		CalDao dao = new CalDao();
		
		try {
			if(command.equals("calendar")) {
				response.sendRedirect("calendar.jsp");
				
			}else if(command.equals("insert")){
				//년 월 일 시 분 아이디 제목 내용 
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				String hour = request.getParameter("hour");
				String min = request.getParameter("min");
				//날짜데이터 하나로 뭉치기
				String mdate = year + Util.isTwo(month) + Util.isTwo(date) + Util.isTwo(hour) + Util.isTwo(min);
				
				//게시글 작성된 글 받아온것
				String id = request.getParameter("id");
				String title = request.getParameter("title");
				String content =request.getParameter("content");
				
				//달력 일정 id title content mdate(일정날짜) regdate
				//20213101123 - 하나짜리가 되면 두개로 바꿔줘야 함 -> 12자리의 형태로-> class 메소드로 만들자
				//2021311120
				//년 월 일 시 분
				//mdate id title content는 db에 저장
				
				//dto에 값을 설치
				CalDto dto = new CalDto(0, id, title, content, mdate, null);
				
				//dao로 dto 담긴값을 보냄
				int res = dao.insertCalBoard(dto);
				if(res>0) {
					response.sendRedirect("calendar.jsp");
				}else {
					request.setAttribute("msg", "일정 추가 실패");
					dispatch("error.jsp", request, response);
				}
			}else if(command.equals("list")) {
				//캘린더에서 보내준 값 받기
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				
				//날짜 데이터 합치기
				String yyyyMMdd = year + Util.isTwo(month) + Util.isTwo(date);
				
				//리스트 호출해서 kh와 yyyyMMdd string을 넣어줄것임
				List<CalDto> list = dao.getCalList("kh", yyyyMMdd);
				
				//list란 이름으로 list담는다
				request.setAttribute("list", list);
				//list.jsp로 보낸다
				dispatch("list.jsp", request, response);
				
				
			}
			
			
		}catch(Exception e) {
			request.setAttribute("msg", "command 오류");
			dispatch("error.jsp", request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
