package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/answer.ho")//매핑주소를 찾아옴
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AnswerBiz biz = new AnswerBizImpl();
		
		String command = request.getParameter("command");//겟파라미터로 담아서 string변수에 담아서
		
		if(command.equals("list")) {//command라는 키값을 가져와서 
			List<AnswerDto> list = biz.selectList(); //biz에서 호출해서 가져온 값을 List(AnswerDto형태)로 받은 것  //제네릭 공부하기
			request.setAttribute("list", list);		//db에서 가져온 애를 list라는 키의 이름으로 저장
			dispatch(request, response, "boardlist.jsp");
			
		}else if(command.equals("select")){
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "boardselect.jsp");
			
		}else if(command.equals("answerform")){
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "answerform.jsp");
			
		}else if(command.equals("answerproc")) {
			int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			AnswerDto dto = new AnswerDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setWriter(writer);
			dto.setBoardno(parentBoardNo);
			int res = biz.answerProc(dto);//비즈로 보내줌
			if(res>0) {
				jsResponse(response, "answer.ho?command=list", "답변성공");
				
			}else {
				jsResponse(response, "answer.ho?command=answerform&boardno="+parentBoardNo, "답변 실패");
			}
			
			
		}else if(command.equals("update")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			//int result = biz.updateDto(boardno);
			
		}
	}
	private void jsResponse(HttpServletResponse response, String url, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		String s = "<script>alert('"+msg+"');location.href='"+url+"';</script>";
		out.println(s);
	}
	
	
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
