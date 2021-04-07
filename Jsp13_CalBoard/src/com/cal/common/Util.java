package com.cal.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

public class Util {

	public String todates;
	
	public String getTodates() {
		return todates;
	}

	public void setTodates(String mdate) {
		// yyyy-MM-dd hh:mm:00 형태로 바꾸자.
		//공백 유의하여 만들기
		String temp = mdate.substring(0, 4)+"-"	//1~4 연도 짜르자
					+ mdate.substring(4, 6)+"-"	//월짜르자
					+ mdate.substring(6, 8)+" "	//일짜르고 공백 넣자
					+ mdate.substring(8, 10)+":"//시간짜르자
					+ mdate.substring(10)+":00";//10부터 마지막 다짜르자
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");//SimpleDateFormat: 날짜<->내가 원하는 형태로 변환해준다
																			//어떤날짜값이 들어오던 "yyyy년MMd일 HH시mm분"형식으로 되돌리겠다.
		Timestamp tm = Timestamp.valueOf(temp);		//String을 Timestamp값으로 변환한다->yyyy-MM-dd hh:mm:00 정해진 형식으로 가져와야함
		todates = sdf.format(tm);					// Timestamp형식을 "yyyy년MMd일 HH시mm분"으로 바꿔주겠다.
		//The method valueOf(String) is undefined for the type Timestamp-> 임포트 잘못한거
	}
	
	//토요일, 일요일, 평일 색상
	public static String fontColor(int date, int dayOfWeek) {
		String color = "";
		
		if((dayOfWeek-1 + date)%7 ==0) {
			color = "blue";
		}else if ((dayOfWeek -1 + date)%7 ==1) {
			color = "red";
		}else {
			color = "black";
		}
		
		return color;
	}
	//일정의 한자리수 -> 두자리수 변환
	public static String isTwo(String msg) {	//문자열 바꾼걸 가져와서
		return (msg.length()<2)? "0"+msg:msg;	//문자열 길이가 돌면서 2보다 작으면 [0+문자열 ex)01] 또는 [문자열 ex)12] //삼항연산자 
	}
	
	//일정 제목이 긴 경우, 뒷 부분을 ...으로
	public static String getCalView(int i, List<CalDto> list) {
		
		String d = isTwo(i+"");	//"4"->"04"  /	""는 string으로 바꿔주는것
		String res = "";
		
		for(CalDto dto : list) {//확장된 포문
			if(dto.getMdate().substring(6, 8).equals(d)) {		//캘린더의 선택한 날의 일자가 d라면
				res += "<p>"
					+ ((dto.getTitle().length() > 6)? dto.getTitle().substring(0, 6)+"...":dto.getTitle())
					//true면 타이틀 길이가 6이면 타이틀을 0~6까지 짜르고 나머지는 ...으로 바꿔주고 //false면 get방식으로 보내줌 //삼항연산자
					+ "</p>";
			}
		}
			
		
		return res;	//string타입으로 return함
	}
}
