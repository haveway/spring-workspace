<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지켜보고 있다</title>
</head>
<body>
	<jsp:include page="common/header.jsp"/>
	
	<div class="content">
		<br><br>
		
		<div class="innerOuter">
		
			<h4>게시글 Top 5</h4>
			<br>
			<a href="list.bo" class="table table-hover" align="center">더보기</a>
			<br>
			
			<table id="boardList" class="table table-hover" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			
			
			<tbody>
				<!-- 현재 조회수가 가장 높은 상위 5개의 게시글 조회해서 뿌리기(ajax 이용해서)-->
			</tbody>
			</table>
		</div>
	</div>

	<script>
		$(function(){
			topBoardList();

			/*
			$('#boardList>tbody>tr').click(function(){
				location.href = 'detail.bo?bno=' + $(this).children.eq(0).text();
			})
			*/
			// 동적으로 만들어진 요소에 이벤트 부여방법
			$(document).on('click', '#boardList>tbody>tr', function(){
				location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();
			})

			setInterval(topBoardList, 1000);	// 1000이 1초임
		})
		function topBoardList(){
			$.ajax({
				url : 'topList.bo',
				success : function(data){
					//console.log(data);
					
					let value = '';
					for(let i in data){
						value += '<tr>'
							   + '<td>' + data[i].boardNo + '</td>'
							   + '<td>' + data[i].boardTitle + '</td>'
							   + '<td>' + data[i].boardWriter + '</td>'
							   + '<td>' + data[i].count + '</td>'
							   + '<td>' + data[i].createDate + '</td>'
							   + '<th>';
							   	if(data[i].originName != null){
							   		value += '★';
							   	}
							   + '</th></tr>'
					}
					$('#boardList>tbody').html(value);
				}, error : function(){
					console.log('top5 ajax실패여');
	
				}
			})
		}
	</script>

	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>