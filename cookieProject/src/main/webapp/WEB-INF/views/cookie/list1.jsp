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
<%-- EL구문을 이용한 쿠키접근 

${cookie.쿠키명}
--%>

	<div>
		쿠키 : ${cookie.test}
	</div>

	<div>
		쿠키있나요? : ${cookie.test != null}
		쿠키있나요? : ${not empty cookie.test} <!-- cookie.test != null && cookie.test != "" -->
	</div>
	
	<div>
		쿠키 값 : ${cookie.test.value}
	</div>
	
</body>
</html>