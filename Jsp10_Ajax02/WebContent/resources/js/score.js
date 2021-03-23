
function getParameterValues(){
	var name = "name="+encodeURIComponent(document.getElementById("name").value);
	var kor = "kor="+document.getElementById("kor").value;
	var eng = "eng="+document.getElementById("eng").value;
	var math = "math="+document.getElementById("math").value;
	
	return "?"+name+"&"+kor+"&"+eng+"&"+math;
	
	
}

function load(){
	var url = "score.do"+getParameterValues();
	
	httpRequest = new XMLHttpRequest();				// XMLHttpRequest : XHR, server와 통신하는 것을 도와주는 객체
	
	//callback함수: 대기했다가 특정상황 필요할 때 요청하면 응답해주는 함수-call하면 back해주는거
	//readystate가 change되는 on이벤트가 발생할때마다 콜백함수를 호출할거야-아니면 스위치문을 써야하는데 함수가 일해줌
	httpRequest.onreadystatechange=callback;		//처리할 함수->레디스테이트가 변할때마다 콜백함수를 호출할거야
	
	//open은 연결할 준비/겟방식은 url에 쿼리스트링 붙여줘야함 
	httpRequest.open("GET", url, true);				//true:비동기/false:동기
	
	httpRequest.send();								//get: send() / post:send("data")
													//send비동기 통신을 실행하는 것
	
}

function callback(){
	alert("readystate:"+httpRequest.readyState);
	//readyState==4: 통신 완료되었다면
	if(httpRequest.readyState==4){				
		alert("status:"+httpRequest.status);
		//통신이 성공적으로 완료되었다면
		if(httpRequest.status==200){ //200일때
			
			//responseText:요청 후 응답받은 문자열
			var obj = JSON.parse(httpRequest.responseText);//string으로 응답받은 것을 json객체로 생성
			document.getElementById("result").innerHTML=decodeURIComponent(obj.name)+"의 총점:"+obj.sum+"\n평균:"+obj.avg;
		}else{
			alert("통신실패");
		}
	}
	
	
}

/*
	XMLHttpRequest : javascript object. http를 통한 데이터 송수신 지원
	
	<onreadystatechange>
	- readystate		클라이언트가 서버로 비동기 요청할 때, xhr응답 진행상황 알려줌
	  0: uninitialized - 실행(load)되지 않음
	  1: loading - 로드 중/보내는중
 	  2: loaded - 로드 됨/보냈음
	  3: interactive - 통신 됨/전달 됨
	  4: complete - 통신 완료/요청후 응답까지 완료됨
	
	- status -상태코드
	  200 : 성공
	  400 : bad request
	  401 : unauthorized
	  403 : forbidden
	  404 : not found -찾을 수 없음 : 경로오류, 잘못된곳에 만들었거나 안만들었거나/ 잘못보낸것
	  415 : unsupported media type
	  500 : internal server error- 서버 내부에서 에러가 났다 null, 넘버포맷 익셉션이등 서버 내부에러시.../ 잘못응답된거

	**
	encodeURIComponent	: 모든 문자를 인코딩 (UTF-8)-한글깨지지말라고
	decodeURIComponent	: UTF-8에서 다시 원래 글자로
	encodeURI			: 주소줄에서 사용되는 특수문자는 제외하고 인코딩 - /?&
	
	JSON.parse			: json 형태의 문자열을 json객체로 변환 (string -> json object)
	JSON.stringify		: JavaScript 객체(json 형태로 변환할 수 있는)를 JSON 형태의 문자열로 변환(object->json string)

 */