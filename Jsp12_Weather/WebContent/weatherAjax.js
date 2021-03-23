$(function() {
	$("#weaView").click(
			function() {
				//servlet ->weatherOpen
				var url = "weatherOpen";
				//id가 adress인 태그의 하위태그 중 option에서 선택한 것들의 value(값)을 code에 담기
				var code = $("#address option:selected").val();//.val()게터
				$.ajax({
					//type:http요청방식의 겟/포스트
					type : "GET",
					//통신할 서버 (페이지, 파일) 주소 //서블릿 뒤에 코드를 붙여준다-그래서 xml페이지로 이동한다
					//url -서블릿. code-지역 선택한 지역코드/ ?zone= 을 code라는 이름으로 보냄
					url : url + "?code=" + code,
					//값을 받을때 타입, default가 텍스트
					dataType : "text",
					success : function(data) {
						//공백제거 되기 전의 data보기 -> alert, console.log
						//alert(data);
						//console.log("data:"+data);
						//trim() 앞뒤 공백제거
						var temp = $.trim(data);
						//공백제거 후 data를 담아준 temp 변수
						//console.log("temp:"+temp);
						//json문자열을 json 객체로 형변환
						var obj = JSON.parse(temp);
						alert(obj);

						//val()->()안에 값이 들어가면 넣어주는 것
						//obj.key - 객체.k로 밸류값으로 넣어주는 것
						$("#pubDate").val(obj.pubDate);
						$("#temp").val(obj.temp);
						$("#x").val(obj.x);
						$("#y").val(obj.y);
						$("#reh").val(obj.reh);
						$("#pop").val(obj.pop);
						$("#wfKor").val(obj.wfKor);

						var weather_condition = obj.wfKor;
						if (weather_condition == "맑음"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/sun.png");
						}else if (weather_condition == "비"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/rain.png");
						}else if (weather_condition == "눈"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/snow.png");
						}else if (weather_condition == "흐림"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/cloud.png");
						}else if (weather_condition == "구름 조금"){
							$("#weather_img").attr("src","/Jsp12_Weather/image/cloud_sun.png");
						}else{
							$("#weather_img").attr("src","/Jsp12_Weather/image/etc.png");
						}
					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			});
});
