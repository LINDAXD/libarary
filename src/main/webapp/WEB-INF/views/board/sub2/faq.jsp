<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<html>
<head>
<title>이젠북컴퍼니 > 열린공간 > 자주묻는질문</title>
</head>
<link rel="stylesheet" href="/resources/css/board/sub2/faq.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<body>

	<div class="header">
		<jsp:include page="../../header.jsp"></jsp:include>
	</div>

	<div class="container">
		<div class="sub_title">
			<div class="doc-info">
				<!-- doc title -->
				<div class="doc-title">
					<h3>자주묻는질문</h3>
					<ul>
						<!-- 홈 btn img -->
						<li class="first" style="background-image: none;"><a href="/">
								<img src="/resources/imges/common/navi_home_icon.gif">
						</a></li>
						<li><a href="/board/noticeList">열린공간</a></li>
						<li><a href="/board/faq">자주묻는질문</a></li>
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
					<div class="accordion">
						<input type="radio" name="accordion" id="answer01"> <label
							for="answer01">회원탈퇴는 어떻게 해야 하나요?<em></em></label>
						<div>
							<p>
								- 마이페이지 회원 탈퇴 메뉴에서 회원 탈퇴를 진행하실 수 있습니다.<br> 회원 탈퇴시 연체된 도서가
								있을경우 탈퇴가 불가능합니다.
							</p>
						</div>
						<!-- 기존'의 질문 종료 -->
						<!-- 다음 질문을 추가 -->
						<input type="radio" name="accordion" id="answer02"> <label
							for="answer02">희망도서 신청은 어떻게 하나요?<em></em></label>
						<div>
							<p>
								<strong>- 홈페이지접속 > 로그인 > 마이페이지 > 희망도서신청/조회 신청</strong><br> 신청 확인 후 처리 절차에 따라
								도서가 구비됩니다.
							</p>
						</div>
						<!-- 기존의 질문 종료 -->
						<!-- 다음 질문을 추가 -->
						<input type="radio" name="accordion" id="answer03"> <label
							for="answer03">도서대여 서비스는 어떻게해야 이용가능한가요?<em></em></label>
						<div>
							<p>
								<b>-일반회원가입</b><br> 이젠북컴퍼니 회원으로 가입하시면 도서들을 빌려갈 수 있습니다.<br>
								<br>
								<br> <b>-가입 방법</b><br> 이젠북컴퍼니 홈페이지 접속 -> 이메일 인증 후 홈페이지
								회원가입<br>
								<br>
							</p>
						</div>
						<input type="radio" name="accordion" id="answer04"> <label
							for="answer04"> 주문한 상품이 아직 안 왔어요. <em></em></label>
						<div>
							<p>
								- 주문하신 도서를 아직 못받아 보셨다면 다음 사항을 확인해 주십시오. <br> <br>
								<strong>1. 주문·배송 확인 시 상품의 처리상태가
								포장완료로 되어 있는 경우 </strong>이 상품은 당사에서는 출고처리를 한 것입니다. 당사에서 출고처리가 되었으나 2~3일
								이내에 받아보시지 못한 경우는 다음을 확인하시기 바랍니다. * 배송조회를 확인하셔서 주문하신 상품이 받아보실 주소의
								근처 취급소에 있다고 조회되면, 택배사 직원이 방문했음에도 불구하고 부재중이셔서 배송받지 못하신 경우가 많습니다.
								이 경우 고객센터의 Q&A
								<배송 /수령일 안내> 상담이나 전화(국번없이)1544-1900을 이용하시면 신속히 처리해 드립니다. *
								당일 배송지역의 경우, 배송조회가 되지 않습니다. 주문하신 당일 신속한 배송처리가 되기 때문에 배송조회가 무의미하기
								때문입니다. <br> <strong> 2. 주문·배송 확인 시 상품의 처리상태가 품절/절판이라고 되어 있는 경우</strong>  <br>상품이 품절/절판인 경우에는
								당사에 재고가 없으며, 거래처에서 상품을 더 이상 취급하지 않기 때문에 보내드릴 수 없습니다. 이 경우 메일로 별도
								안내해 드리고 있습니다. 만약 메일을 받아 보지 못하셨다면, 네트워크의 오류나 메일주소의 오기 또는 메일주소가 없기
								때문입니다. [마이룸]의 [회원정보 수정]에서 메일주소의 등록 또는 수정을 하십시오. <strong> <br>3. 상품준비중 상태가 오래
								지속될 경우</strong> 주문/배송 조회시 주문상태가 ‘상품준비중’ 단계로 남아 있다면 상품의 출고 예정일을 확인해 주시기
								바랍니다. 여러권을 함께 주문하셨을 경우, 출고 예정일이 가장 늦은 상품에 맞춰 함께 배송됩니다. (마이룸 >
								주문배송 조회 페이지에서 예약판매 도서를 함께 주문하셨는지 확인해주세요!) 
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="../../footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$(".sub2").addClass("active");
		})
	</script>
</body>
</html>