<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:catch var="err">	
	<c:set var="weatherURL"	value="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=${code}" />
	<c:import var="weather" url="${weatherURL}" />
	<x:parse var="wrss" xml="${weather}" />
	
{"pubDate":"<x:out select="$wrss/rss/channel/pubDate" />",
"temp":"<x:out select="$wrss/rss/channel/item/description/body/data/temp" />",
"reh":"<x:out select="$wrss/rss/channel/item/description/body/data/reh" />",
"pop":"<x:out select="$wrss/rss/channel/item/description/body/data/pop" />",
"x":"<x:out select="$wrss/rss/channel/item/description/header/x" />",
"y":"<x:out select="$wrss/rss/channel/item/description/header/y" />",
"wfKor":"<x:out
		select="$wrss/rss/channel/item/description/body/data/wfKor" />"}
</c:catch>

<c:if test="${err!=null}">
	${err}
</c:if>


	
<% 
//taglib x는 xalan 사용하겠다 : xml 파싱
//톰캣 ->taglib ->Binary README-> The XML tag library requires Apache Xalan 2.7.1 or later
//apache software foundation->xalan->http://xalan.apache.org/old/xalan-j/index.html
//http://www.apache.org/dyn/closer.cgi/xalan/xalan-j ->bin, binaries- > zip

//jstl : jsp standard tag library -> 표준태그 모음집

//C:~ -> core library
//x:~ -> xml library => 사용하기위해서 Apache Xalan.jar이 필요함

//c:catch 분문에서 발생하는 예외 발생시 err라고 한다/트라이캐치의 캐치와같음 
//c:set 평가중인 표현식의 결과를 scope 변수에 설정 / 변수선언해서 값을 저장해주는 애
//c:import는 url 요청하는것, doc이 응답되는데 xml 형태의 문자열이 응답됨
//				응답된 문자열을 weather라는 이름의 변수에 저장			
//x:parse :source 속성 또는 body대신 xml(컨텐츠) 객체로 바꿈 /문자열이면 원하는 태그를 가져올 수 없음 
//			xml객체(xml parse tree)로 바꿔서 wrss라는 이름의 변수에 저장. 
//x:out; <%= ...>과 같은것
//		태그안에 태그, 태그찾아서 태그가 가지고 있는 값을 출력하자
//{k:v} json 형태의 문자열로 만들어준것

//xml파일 body안에 <data seq=''0"> 이런식으로 20까지 있던데 여기서 어떤걸 가져오는걸까???
//DataType이 text이므로, jstl태그는 가져오지않고 String으로 작성된 태그만 그대로!
%>
