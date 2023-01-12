<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<link rel="stylesheet" href="/resources/css/error/error.css">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
</head>
<body>
	<div style="text-align: center;">
		<img src="/resources/imges/common/error.jpg" style="width: 200px;">
		<h1 style="font-size: 50px;">500</h1>
		<span style="font-size: 20px;">
		죄송합니다.<br>
		홈페이지 내부에서 에러가 발생하였습니다.
		</span>
		<br><br>
		
		<span style="font-size: 15px; color: #888;">
		접근하신 방식이 올바르지 않거나,<br> 
		현재 홈페이지 내부에 에러가 있습니다.<br><br>
		잠시 후 다시 한번 시도해 주시기 바랍니다.<br> 
		</span><br>
		<input class="btn" type="submit" value="홈으로가기">
	</div>
	
<script>
	$(function(){
		$(".btn").on("click", function(){
			window.location.href="/";
		})
	})
</script>
</body>
</html>