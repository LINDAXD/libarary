<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<html>
<head>
	<title>이젠북컴퍼니 > 이용안내 > 회원가입안내</title>
	<link rel="stylesheet" href="/resources/css/info/sub1/signup.css">
	<link rel="stylesheet" href="/resources/css/info/sb_page.css">
	<link rel="stylesheet" href="/resources/css/header.css">
	<link rel="stylesheet" href="/resources/css/footer.css">
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
                    <h3>회원가입안내</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li class="first" style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/info/signup">이용안내</a>
                        </li>
                        <li>
                            <a href="/info/signup">회원가입안내</a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
        <div class="section">
            <div class="doc">

                <!-- 왼쪽 사이드바 -->
                <jsp:include page="../lnb.jsp"></jsp:include>

                <!-- 본문 -->
				<div class="content">
                    <div class="doc">
                <!-- 60행까지는 기존 sub_page와 동일         -->
                        <div class="doc-body con17" id="contentArea">
                            <div class="body">
                                <div class="doc-body">
                                    <div class="roomicon">
                                        <div class="inner icowrap">
                                            <span class="ico ico5"></span>
                                            <strong>회원가입 안내</strong>
                                            <p>
                                                이젠북컴퍼니의 회원이 되시면 <br>
                                                도서 대출 및 구매, 희망 도서 신청 등 다양한 서비스를 
                                                쉽고 편리하게 이용해보실 수 있습니다.
                                            </p>
                                            <!-- 회원가입 화면으로 연결 -->
                                            <p class="basic_btn">
                                                <a href="#a" class="btn_go newWin" target="_blank" tutle="새창으로 열립니다."></a> 
                                        </div>
                                    </div>
                                    <h3>회원가입은 어떻게 할 수 있나요?</h3>
                                        <div class="step_box01">
                                            <ul class="mode">
                                                <li class="step01">
                                                    <div>
                                                        <h4>STEP 01</h4>
                                                        <h5>홈페이지 접속,<br>
                                                        	회원가입 메뉴</h5>
                                                    </div>
                                                </li>
                                                <li class="arr">
                                                    <div>
                                                        <img src="https://library.daegu.go.kr/data/menuResources/h35/17/1602747627060.png">
                                                    </div>
                                                </li>
                                                <li class="step02">
                                                    <div>
                                                        <h4>STEP 02</h4>
                                                        <h5>회원정보 입력,<br>
                                                             이메일 인증</h5>
                                                    </div>
                                                </li>
                                                <li class="arr">
                                                    <div>
                                                        <img src="https://library.daegu.go.kr/data/menuResources/h35/17/1602747627060.png">
                                                    </div>
                                                </li>
                                                <li class="step03">
                                                    <div>
                                                        <h4>STEP 03</h4>
                                                        <h5>회원가입 완료,<br>
                                                             홈페이지 이용</h5>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- 유의사항 -->
                                        <h3>유의사항</h3>
                                        <ul class="con">
                                            <li>가입하셨던 이메일로 재가입은 불가능합니다.</li>
                                        </ul>
                                    </div>
                                    <div id="menuRatingDiv"></div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="../../footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	$(".sub1").addClass("active");
	
</script>
</body>
</html>
