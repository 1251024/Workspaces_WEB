package com.answer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;


@WebServlet("/AnswerController")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AnswerBiz biz = new AnswerBizImpl();
		
		String command =request.getParameter("command");
		if(command.equals("list")) {
			//1.
			//2.
			List<AnswerDto> list = biz.selectList();
						
			//3.
			request.setAttribute("list", list);
			//4.
			dispatch(request, response, "boardlist.jsp");
			
		}else if(command.equals("detail")) {
			//1.
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			//2.
			AnswerDto dto = biz.selectOne(boardno);
			
			//3.
			request.setAttribute("dto", dto);
			//4.
			dispatch(request, response, "boardselect.jsp");
			
		}else if(command.equals("answerform")) {
			//1.
			int boardno = Integer.parseInt(request.getParameter("boardno"));//글번호 받아서 
			//2.
			AnswerDto dto = biz.selectOne(boardno);
			//3.
			request.setAttribute("dto", dto);
			
			//4.
			dispatch(request, response, "answerform.jsp");//답변폼에 보내주기
			
		}else if(command.equals("answerwrite")){
			//1.
			int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));//부모글번호받기
			String title = request.getParameter("title");		//부모제목받아서
			String writer = request.getParameter("writer");		//부모작성자받아서
			String content = request.getParameter("content");	//부모내용받아서
			
			//2.
			AnswerDto dto = new AnswerDto();
			dto.setBoardno(parentBoardNo);
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);
			
			int res= biz.answerProc(dto);//answerProc으로 보내주면
			//3.
			
			//4.
			if(res>0) {	//실행해서 결과받았으니까, 받으면 답변성공 없으면 실패
				jsResponse(response, "answer.do?command=list", "답변성공!");
			}else {
				jsResponse(response, "answer.do?command=answerform&boardno="+parentBoardNo, "답변실패!");
			}
		}
		
			
		
		
		
		response.getWriter().append("<a href='index.jsp'><h1>잘못왔다.</h1></a>");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(HttpServletResponse response, String url, String msg) throws IOException {
		String s = "<script type = 'text/javascript'>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		response.getWriter().print(s);
	}
}
