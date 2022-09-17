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
	<h1>인증번호 검사</h1>
	
	<form action="check" method="post">
		<input type="text" name="secret">
		<input type="submit" name="인증하기">
	</form>

</body>
</html>