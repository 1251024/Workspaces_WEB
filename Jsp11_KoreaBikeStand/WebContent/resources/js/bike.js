//제이쿼리 함수 사용할 건데 getJsonDate이름의 함수를 호출하겠다.
//시작하자마자 onload형식으로 바로 실행하겟다
//view.html 상단에 불러와져야 $가 사용 가능

$(function(){
	getJsonDate();
});


function getJsonDate(){		//getJsonDate 함수는 다음과 같은 명령을 수행한다.
	//JSON으로 되어있는 데이터를 get방식으로 요청해서 서버에서 가져올거야 : 원래는 json으로 응답된거 가져오는 ajax 함수, 데이터는 생략된것
	//mydata는 해당 url에 요청해서 응답된 데이터를 받아준다
	$.getJSON("resources/json/bike.json", function(mydata){		//통신이 완료되면 json객체를 가져올 겁니다.				
		$.ajax({																
			//데이터를 서버에 또 보낸다	//다시 비동기 요청
			url: "bike.do",														
			method: "post",
			//보내는 값 리퀘스트에 담겨 같이 가는 값 //text(string)->k:v형태
			data: {"command": "getdata", "mydata":JSON.stringify(mydata)},		//json 객체를 JSON.stringify()로 메서드는 json 값이나 객체를 JSON 문자열로 변환합니다 
			//값을 받는 형식//response 응답할때 text(string)형태로 응답하는데  json 타입으로 바꾼다.
			dataType:"json",
			//성공해서 응답되는 값이 json객체
			success: function(msg){		//msg= json형태의 object 값 //json객체로 바꼈다
				var $tbody = $("tbody");
				console.log(msg);
				
				var list = msg.result;	//key를 통해서 value값을 꺼내온것
				for(var i = 0; i<list.length; i++){//for문을 통해 테이블 row를 추가한다//리스트의 크기만큼 봔복
					var $tr = $("<tr>");			
					
					$tr.append($("<td>").append(list[i].name));
					$tr.append($("<td>").append(list[i].addr));
					$tr.append($("<td>").append(list[i].latitude));
					$tr.append($("<td>").append(list[i].longitude));
					$tr.append($("<td>").append(list[i].bike_count));
					
					$tbody.append($tr);		//tbody에 추가해준다!
				}
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}