<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠북컴퍼니 > 회사소개 > 찾아오시는길</title>
<link rel="stylesheet" href="/resources/css/intro/road.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="header">
		<jsp:include page="../../header.jsp"></jsp:include>
	</div>

	<div class="container">
		<div class="sub_title">
			<div class="doc-info">
				<!-- doc title -->
				<div class="doc-title">
					<h3>찾아오시는길</h3>
					<ul>
						<!-- 홈 btn img -->
						<li class="first" style="background-image: none;"><a href="/">
								<img src="/resources/imges/common/navi_home_icon.gif">
						</a></li>
						<li><a href="/intro/greeting">회사소개</a></li>
						<li><a href="/intro/road">찾아오시는길</a></li>
					</ul>

				</div>
			</div>
		</div>
		<div class="section">
			<div class="doc">

				<!-- 왼쪽 사이드바 -->
				<jsp:include page="../lnb.jsp"></jsp:include>

				<!-- 본문 -->
				<div class="content" style="">
					<div class="doc">
						<div class="wrapper-bbs" style="">
							<div class="search">
								<iframe
									src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3173.4442582092797!2d126.84881051530415!3d37.308303079846986!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b6f81ed7be66b%3A0x4b5f72df9e8f8dd9!2z7JWI7IKwIOydtOygoOyVhOy5tOuNsOuvuOy7tO2TqO2EsO2VmeybkA!5e0!3m2!1sko!2skr!4v1674709505140!5m2!1sko!2skr"
									width="850" height="450" style="border: 0;" allowfullscreen=""
									loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
							</div>
						<div class="address_wrap">
							<dl class="address">
									<dt>주소</dt>
									<dd class="MB20">경기 안산시 상록구 광덕1로 375 강우프라자 5층</dd>
							</dl>
							<dl class="loctaion">
								<dt>위치</dt>
								<dd>4호선 한대앞역 2번 출구 > 도보 200M 롯데리아 옆 강우빌딩</dd>
							</dl>
							<dl class="tel-fax">
								<dt class="sr-only">전화 및 팩스</dt>
								<dd>
									<span>031)502-2631<br></span>
									<span>031)502-2634</span>
								</dd>
							</dl>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	<script>
		$(function() {
			$(".sub3").addClass("active");
		});
	</script>

</body>
</html>