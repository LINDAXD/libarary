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
		<h1 style="font-size: 50px;">404</h1>
		<span style="font-size: 20px;">
		죄송합니다.<br>
		요청하신 페이지를 찾을 수 없습니다.
		</span>
		<br><br>
		
		<span style="font-size: 15px; color: #888;">
		방문하시려는 페이지의 주소가 잘못 입력되었거나,<br>
		페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br>
		<br>
		입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.<br>
		</span><br>
		<input class="btn" type="submit" value="이전 페이지">
	</div>
	
<script>
	$(function(){
		$(".btn").on("click", function(){
			history.back();
		})
	})
</script>
</body>
</html>