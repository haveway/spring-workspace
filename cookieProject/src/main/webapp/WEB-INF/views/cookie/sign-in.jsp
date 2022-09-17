<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 확인</title>
</head>
<body>

<!-- 
	계획 : saveId라는 쿠키가 있다면 아이디를 저장한 것으로 간주
	저장된 value를 불러와서 아이디 입력창에 자동 설정
	아이디 저장하기 체크박스에 체크 수행
 -->
 
 	<form>
 		<c:choose>
 		<c:when test="${not empty cookie.saveId}">
	 		아이디 : <input type="text" value="${cookie.saveId.value}"><br>
	 		저장 : <input type="checkbox" name="saveId" checked><br>
 		</c:when>
 		<c:otherwise>
	 		아이디 : <input type="text"><br>
	 		저장 : <input type="checkbox" name="saveId"><br>
 		</c:otherwise>
 		</c:choose>
 	</form>

</body>
</html>