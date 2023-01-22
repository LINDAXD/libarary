<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<meta charset="UTF-8">
<title>Good</title>
</head>
<link rel="stylesheet" href="/resources/css/buy/success.css">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<body>
	<p class="pay">pay</p>
	<h1 class="success">결제에 성공했습니다</h1>
	<!-- <form action="/buy/book_buylist" method="get">
		<button id="main">주문목록으로 가기</button>
	</form> -->
	
	<form onsubmit="return false;" method="post" id="buy">
	   	<sec:authorize access="isAuthenticated()">
			<input type="hidden" class="user_email" name="user_email" value=<sec:authentication property="principal.dto.user_email"/>>
		</sec:authorize>

		<sec:authorize access="isAnonymous()">
			<input type="hidden" class="user_email" name="user_email">
		</sec:authorize>
		<input type="hidden" name="book_title" value="${book.book_title }">
		<input type="hidden" name="book_author" value="${book.book_author }">
		<input type="hidden" class="book_isbn" name="book_isbn" value="${book.book_isbn }">
		<input type="hidden" name="book_cover" value="${book.book_cover }">
		<input type="hidden" name="book_pubDate" value="${book.book_pubDate }">
		<input type="hidden" name="book_publisher" value="${book.book_publisher }">
		<input type="hidden" name="amount" value="${cri.amount }">
		<input type="hidden" name="page" value="${cri.page }">
		<input type="hidden" name="type" value="${cri.type }">
		<input type="hidden" name="keyword" value="${cri.keyword }">
		<button id="main">구매목록으로 가기</button>
    </form>
	
	
	
	
	
	
<script>
	$(function(){
		$("#main").on("click",function(){
			self.close();
			window.open("/buy/book_buylist");
		});
	});
	
</script>
<!-- <script type="text/javascript">
	$(function() {
		$(".sub1").addClass("active"); //왼쪽 카테고리 '도서검색' 활성화
		 
		$("#test").click(function() {
			
			let email = $('.user_email').val(); 
			let book_isbn = $('.book_isbn').val(); 
			
			if(email == "") {
				alert("로그인 후 이용해주세요");
				location.href="/member/login";
			} else {
				
				if (confirm("도서를 구매하시겠습니까?")) {
				
					let data = {
	           				book_isbn: book_isbn
	           		};
					
					$.ajax({
	           			type: "post",
	           			url: "/buy/test",
	           			data: data,
	           			success: function(result) {
	           				
	           				if (result == "success") {
	           					alert("내 찜리스트에 등록되었습니다.");
	           					$("#buy").attr("action", "/buy/buybook?detail=not");
	           					$("#buy").attr("onsubmit", "return true;");
	           					$("#buy").submit();
	       						
	           				} else if (result == "alreadyBuy"){
	           					alert("이미 찜한 도서입니다.");
	           				} 
	           			}
	           		});
				
				}
				
				
			} 
				
		});
	});
	</script>  -->
</body>
</html>

