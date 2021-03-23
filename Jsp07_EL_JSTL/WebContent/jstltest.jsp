<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL : Jsp Standard Tag Library</h1>

	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<!-- forEach: 기본 반복 태그 -->
		<!-- items: iterate 반복할 수 있는 덩어리를 가져오는 것 -->
		<!-- for(Score score :list){}와 같은 뜻 -->
		<c:forEach items='${list }' var="score">
			<!-- 변수 score는 아래에서 사용 -->
			<tr>
				<td>
					<!-- eq : == / ne: != / empty: null --> <!-- if:조건이 참인지 거짓인지 찾아주는 애 -->
					<c:if test="${score.name eq '이름10' }">
						<!-- 같은 td안에 있어서 -->
						<c:out value="홍길동"></c:out>
					</c:if> <!-- choose :choose안에서 when, otherwise사용할 수 있게 도와주는 애 / when, otherwise외에 choose안에 주석이 들어가면 에러남 -->
					<!-- when:만일 choose안에서 이 조건이 true면 아래 명령 수행 후 다음조건, 거짓이면 다음조건으로 넘어간다-->
					<!-- otherwise: 위의 조건이 거짓이면 아래 명령문을 실행 --> <!-- c:시리즈는 실행 위치는 server -->
					<c:choose>
						<c:when test="${score.name eq '이름20' }">
							<c:out value="${score.name}님!"></c:out>
						</c:when>
						<c:when test="${score.name eq '이름30' }">
							<c:out value="${score.name}"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="누구지?"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${score.kor }</td>
				<td>${score.eng }</td>
				<td>${score.math }</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>
				<td><c:choose>
						<c:when test="${score.grade eq 'A'||score.grade eq 'B' }">
							<c:out value="PASS"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="FAIL"></c:out>
						</c:otherwise>
					</c:choose></td>
			</tr>

		</c:forEach>


	</table>
	<br>
	<br>
	<br>


	<table border="1">
		<tr>
			<th>구구단</th>
			<th colspan="9">값</th>
		</tr>

		<c:forEach var="i" begin="2" end="9" step="1">
	
		<tr>
				<td>[${i } 단]</td>
				<c:forEach var="j" begin="1" end="9" step="1">
					<td> ${i } x ${j } = ${i * j}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<!-- 구구단 출력 -->




</body>
</html>