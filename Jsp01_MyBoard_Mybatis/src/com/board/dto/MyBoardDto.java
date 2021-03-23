package com.board.dto;

import java.sql.Date;

//Data Transfer Object :값 전달 객체 (VO-Value Object)
//DB Table의 row 한개 값을 저장할 수 있음.
public class MyBoardDto {

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regdate;	
	
	public MyBoardDto() {
		
	}
	public MyBoardDto(int seq, String writer,String title, String content, Date regdate) {	//파라미터 5개주고 5개 다썻는지 확인하기
		this.seq=seq;
		this.writer=writer;
		this.title=title;
		this.regdate=regdate;
		this.content=content;//Stacktrace:]을(를) 발생시켰습니다. java.sql.SQLException: 부적합한 열 이름
		
	}
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

}
