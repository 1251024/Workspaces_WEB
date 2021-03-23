package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/BikeController")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BikeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		if (command.equals("view")) {
			response.sendRedirect("view.html");	//view.html로 응답한다
		} else if (command.equals("getdata")) {
			BikeDao dao = new BikeDao();

			if (dao.delete()) {						//delete로 테이블 초기화 시킴
				System.out.println("db 초기화 성공");
			} else {
				System.out.println("db 초기화 실패");
			}

			String data = request.getParameter("mydata");		//서버에서 js에서 보내줬던걸 받아준다
			
			//JsonElement: json 형태로 만들어진 객체를 받아줄수있는것(JsonObject, a JsonArray, a JsonPrimitive or a JsonNull 값들을 모두 포함!)
			//넘어오는게 json오브젝트인지 json어레인지 어떤 datatype인지 모르니까 모두 포함할 수 있는 element로 받아줌
			JsonElement element = JsonParser.parseString(data);		//json형태의 문자열을 json 객체로 변환 (string -> json object)
			//JsonObject: key-value pair( {"String":JsonElement}형식),element가 상위개념
			//json 객체,Json에서 객체 유형을 나타내는 클래스 -> {k:v}형태를 말함, json형태의 어떤요소든 들어감 {"":JsonElement}
			JsonObject jsonData = element.getAsJsonObject();	    // element의 json의 오브젝트를 jsonData에 넣는다
			//JsonObject jsonData = JsonParser.parseString(data).getAsJsonObject();
																	// JsonElement는 JsonObject, JsonPrimitive, JsonArray, JsonNull 이 4가지의 부모 클래스로 추상클래스로 정의된다.
			JsonElement records = jsonData.get("records");			//.get은 records로 되어있는 key찾아서 value값을 가져온다.
			JsonArray recordsArray = records.getAsJsonArray();	//records는 json배열로 가져온다.

			List<BikeDto> list = new ArrayList<BikeDto>();
			JsonArray resultArray = new JsonArray();//json배열을 만든다
			

			for (int i = 0; i < recordsArray.size(); i++) {//배열의 사이즈까지 반복
				
				/*
				 * JsonElement tempElement = recordsArray.get(i);
				 * JsonObject tempObject = tempElement.getAsJsonObject();
				 * JsonElement nameElement  = tempObject.get("자전거대여소명");
				 * String name = nameElement.getAsString();
				 */
				String name = recordsArray.get(i).getAsJsonObject().get("자전거대여소명").getAsString();//배열의 i번지값 가져옴

				String addr = null;
				if (recordsArray.get(i).getAsJsonObject().get("소재지도로명주소") != null) {
					addr = recordsArray.get(i).getAsJsonObject().get("소재지도로명주소").getAsString();
				} else {
					addr = recordsArray.get(i).getAsJsonObject().get("소재지지번주소").getAsString();
				}

				double latitude = recordsArray.get(i).getAsJsonObject().get("위도").getAsDouble();
				double longitude = recordsArray.get(i).getAsJsonObject().get("경도").getAsDouble();

				int bike_count = recordsArray.get(i).getAsJsonObject().get("자전거보유대수").getAsInt();

				BikeDto dto = new BikeDto(name, addr, latitude, longitude, bike_count);			//dto에 담아준다
				list.add(dto);			//list에 추가해준다

				
				//json.simple: json텍스트를 파싱하는거
				//gson : JAVA객체를 json객체로 바꿔주기도하고 json을 JAVA로 바꿔주기도 하는 구글이 만든 라이브러리
				//gson api:gson 구글 검색->gson github-> API javadoc
				Gson gson = new Gson();													//gson객체를 만들거다
				//bikedto java객체를 json string으로 바꿔준것
				String jsonString = gson.toJson(dto);									//java object->json string으러 바꿔줌
				//json 형태의 문자열을 json객체로 만들어서 json배열로 저장
				resultArray.add(JsonParser.parseString(jsonString));					//json string->json object로 담았다
			
			}
			if (dao.insert(list)) { 													//리스트에 전달
				System.out.println("db 저장 성공");
			} else {
				System.out.println("db 저장 실패");
			}

			JsonObject result = new JsonObject();										//result라는 이름의 제이슨 객체를 만들어서	
			result.add("result", resultArray);											// k:v형태로 만들어줌

			response.getWriter().append(result + "");									//html로 js를 보낸다
		}

	}

}
