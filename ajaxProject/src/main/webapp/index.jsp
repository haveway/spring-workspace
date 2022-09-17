<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jQuery 라이브러리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

	<h1>Spring에서의 AJAX사용법</h1>
	
	<h3>1. 요청시 값 전달, 응답 결과 받아보기</h3>
	
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="text" id="age"> <br>
	
	<button onclick="test1()">전송</button>
	
	<div id="result1"></div>
	
	<script>
		/*
		function test1(){
			$.ajax({
				url : "ajax1.do", // 필수로 정의해야하는 속성!(컨트롤러의 url매핑값!)
				data : { // 요청시 전달값!
					name : $('#name').val(),
					age : $('#age').val(),
				}, success:function(result){ // 해당 통신에 성공했을 때 어떤걸 실행할건지
					console.log(result);
				}, error:function(){ // 해당 통신에 실패했을 때
					console.log("ajax.do 실패")
				}			
				
			});
		*/	
			
			function test1(){
				$.ajax({
					url : 'ajax1.do', // 필수로 정의해야하는 속성!(컨트롤러의 url매핑값!)
					data : { // 요청시 전달값!
						name : $('#name').val(),
						age : $('#age').val(),
					}, success:function(result){ // 해당 통신에 성공했을 때 어떤걸 실행할건지
						//console.log(result);
					
					/*
					// 응답데이터가 배열의 형태일 경우 =>
					let value = '이름: ' + result[0] + '<br>나이' + result[1];
					$('#result1').html(value);
					*/
					
					// 응답데이터가 객체 형태일 경우 => 속성에 접근가능.속성명
					let value = '이름: ' + result.name + "<br>나이: " + result.age;
					$('#result1').html(value);
					
					}, error:function(){ // 해당 통신에 실패했을 때
						console.log("ajax.do 실패")
					}			
					
				});
		}
	</script>
	
	<h2>2. 조회 요청 후 한 회원 객체를 응답받아서 출력해보기</h2>
	
	조회할 회원 번호 : <input type="number" id="userNo">
	<button id="btn2">조회</button>
	<div id="result2"></div>
	
	<script>
		$(function(){
			
			$('#btn2').click(function(){
				
				$.ajax({
					url:'ajax2.do',
					data:{num:$('#userNo').val()},
					success:function(obj){
						console.log(obj);
						
						let value = '<ul>'
									+'<li>' + obj.userId + '</li>'
									+'<li>' + obj.userName + '</li>'
									+'<li>' + obj.age + '</li>'
									+'<li>' + obj.phone + '</li>'
									+ '</ul>'
						$('#result2').html(value);
						
					}, error:function(){
						console.log("ajax2.do 실패");
					}
				})
			});
		})
	</script>
	
	<h3>3. 조회 처리 후 조회된 회원 리스트 응답 받아서 출력</h3>
	<button onclick="test3()">전체조회</button>
	<br><br>
	
	<table border="1" id="result3">
		<thead>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>전화번호</th>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<script>
		function test3(){
			
			$.ajax({
				url:'ajax3.do',
				success:function(list){
					// console.log(list);
					
					let value = '';
					for(let i in list){
						value += '<tr>'
							   + '<td>' + list[i].userId + '</td>'
							   + '<td>' + list[i].userName + '</td>'
							   + '<td>' + list[i].age + '</td>'
							   + '<td>' + list[i].phone + '</td>'
							   + '</tr>'
					}
					$('#result3 tbody').html(value);
					
				}, error:function(){
					console.log('전체조회 실패');
				}
				
			})
		}
	</script>
	
</body>
</html>