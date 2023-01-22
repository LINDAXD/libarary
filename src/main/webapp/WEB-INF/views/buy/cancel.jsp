<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>Bad</title>
</head>
<link rel="stylesheet" href="/resources/css/buy/cancel.css">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<body>
	<p class="pay">pay</p>
	<h1 class="cancel">결제에 실패했습니다</h1>
	<!-- <form action="/" method="get">
		<button id="main">메인으로 가기</button>
	</form> -->
	<button id="main">메인으로 가기</button>
	
	<script>
	$("#main").click(function(){
		console.log("작");
		self.close();
		window.open("http://localhost:8092/");
	});
</script>
</body>
</html>

