<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		계획 : 
		
		- pop이라는 쿠키가 있는지 검사
		- 쿠키가 없을
	
	 --%>

	<c:if test="${empty cookie.pop}">
		<script>
			window.open("http:www.naver.com", "naver", "width=400", "height=600")
		</script>
	</c:if>
</body>
</html>