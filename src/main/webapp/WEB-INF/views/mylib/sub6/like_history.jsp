<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠북컴퍼니 > 나의도서관 > 찜한 도서</title>
<link rel="stylesheet" href="/resources/css/mylib/sub1/loan_history.css">
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
                    <h3>찜한 도서</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li class="first" style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li> 
                        <li>
                            <a href="/mylib/loan-history">나의도서관</a>
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
                <div class="content" style="">
                    <div class="doc">
                        <div class="wrapper-bbs" style="">
                            
                            <!-- 도서 수 -->
                            <div class="inline">
                                <form action="">
                                	
                                    <span style="margin-right: 10px;"> 찜한 도서 : 총 <b>${total }</b> 건</span>
                                    <select name="amount">
                                    	
	                                    <c:if test="${pageMaker.cri.amount == 10 }">
	                                    <option value="10" selected="selected">10건씩 보기</option>
	                                    <option value="20">20건씩 보기</option>
	                                    <option value="30">30건씩 보기</option>
	                                    <option value="40">40건씩 보기</option>
	                                    </c:if>
	                                    
	                                    <c:if test="${pageMaker.cri.amount == 20 }">
	                                    <option value="10">10건씩 보기</option>
	                                    <option value="20" selected="selected">20건씩 보기</option>
	                                    <option value="30">30건씩 보기</option>
	                                    <option value="40">40건씩 보기</option>
	                                    </c:if>
	                                    
	                                    <c:if test="${pageMaker.cri.amount == 30 }">
	                                    <option value="10">10건씩 보기</option>
	                                    <option value="20">20건씩 보기</option>
	                                    <option value="30" selected="selected">30건씩 보기</option>
	                                    <option value="40">40건씩 보기</option>
	                                    </c:if>
	                                    
	                                    <c:if test="${pageMaker.cri.amount == 40 }">
	                                    <option value="10">10건씩 보기</option>
	                                    <option value="20">20건씩 보기</option>
	                                    <option value="30">30건씩 보기</option>
	                                    <option value="40" selected="selected">40건씩 보기</option>
	                                    </c:if>
                                        
                                    </select>
                                    <input type="hidden" name="page" value="1">
                                    <button id="list_btn" class="btn">이동</button>
                                </form>

                            </div>

                            <!-- 테이블 -->
                            <div class="table-wrap">
                            	<c:if test="${not empty like_history }">
                                <table>
                                    <thead>
                                        <tr>
                                        	<th style="">책이미지</th>
                                            <th style="">도서명</th>
                                            <th style="">저자</th>
                                            <th style="width: 90px;">발행일</th>
                                            <th style="width: 90px">출판사</th>
                                            <th style="width: 120px"></th>
	                                </tr>
                                    </thead>
                                    <tbody>
                                    
	                                    <c:forEach var="like_history" items="${like_history}">
										<tr>
											<td class="">
                                                <img src="${like_history.book_cover }" style="width: 200px;">
                                            </td> 
											<td>${like_history.book_title }</td> 
											<td>${like_history.book_author}</td>
											<td>${like_history.book_pubDate }</td>
											<td>${like_history.book_publisher }</td>
											<td>
												<form action="/search/delete-like" method="post" onsubmit="return false;" class="likeForm">
                                                	<input type="hidden" name="book_isbn" value="${like_history.book_isbn }">
						                        	<input type="submit" class="btn deleteBtn" value="삭제" style="margin-bottom:5px;width:80px;">
						                        </form>
												
										<!--------------  장바구니 추가 ------------------>
										 <form id="cart" onsubmit="return false;" method="post">
				                        
											<sec:authorize access="isAuthenticated()">
												<input type="hidden" class="user_email" name="user_email"
													value=<sec:authentication property="principal.dto.user_email"/>>
											</sec:authorize>
											
											<sec:authorize access="isAnonymous()">
											<input type="hidden" class="user_email" name="user_email">
											</sec:authorize>
											
											<input type="hidden" name="book_title" value="${like_history.book_title }">
											<input type="hidden" name="book_author" value="${like_history.book_author }">
											<input type="hidden" class="book_isbn" name="book_isbn" value="${like_history.book_isbn }">
											<input type="hidden" name="book_cover" value="${like_history.book_cover }">
											<input type="hidden" name="book_pubDate" value="${like_history.book_pubDate }">
											<input type="hidden" name="book_publisher" value="${like_history.book_publisher }">
											<input type="hidden" name="priceStandard" value="${like_history.priceStandard }"> 
											
											<%-- <input type="hidden" name="amount" value="${cri.amount }">
											<input type="hidden" name="page" value="${cri.page }">
											<input type="hidden" name="type" value="${cri.type }"> --%>
											<input type="hidden" name="keyword" value="${cri.keyword }"> 
											
				                            <button id="cart_btn" class="btn2 cart_btn btn" style="width:80px;">장바구니</button>
				                           
				                        </form>
											</td>
										</tr>
										</c:forEach>
	                                        
                                    </tbody>
                                </table>
                                
                                <br>
                                
                                <div class="pageInfo" style="">
	
									<c:if test="${pageMaker.prev }">
										<a class="not" href="${pageMaker.startPage - 1}">이전</a>
									</c:if>
									
									<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
										<a class="${pageMaker.cri.page == num ? "current":"not" }" href="${num }"><span>${num }</span></a>
									</c:forEach>
									
									<c:if test="${pageMaker.next }">
										<a class="not" href="${pageMaker.endPage + 1}">다음</a>
									</c:if>
								</div>
								</c:if>
								
                            </div>
                            
                        </div>
						<br>
                        <c:if test="${empty like_history }">
							<h2>찜한 도서가 없습니다.</h2>
						</c:if>
                    </div>

                </div>

            </div>
        </div>
    </div>
    
    <form method="get" class="moveForm"> 
		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		<input type="hidden" name="page" value="${pageMaker.cri.page }">
	</form>
    
    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>


	<script>
		
		$(function() {
			$(".sub6").addClass("active");
			
			let moveForm = $(".moveForm");
			
			//pageInfo의 a태그를 누르면 a태그의 href 속성을 가져와서 moveForm의 page에 넣고 moveForm이 submit됨
			$(".pageInfo a").on("click", function(e) {
				e.preventDefault();
				moveForm.find("input[name = 'page']").val($(this).attr("href"));
				moveForm.submit();
			});
			
			
			/* 삭제하기 추가 */
			
			let likeForm = $(".likeForm");
			
			$(".deleteBtn").click(function(){
				if (confirm("찜한 도서를 삭제하시겠습니까?")){
					alert("삭제되었습니다.");
					likeForm.attr("onsubmit", "return true;");
					likeForm.submit();
				} else {
					alert("삭제가 취소되었습니다.");
				}
			})
		});
		
	</script>
<!-- 장바구니  -->		
<script>
$(function() {
	$(".sub6").addClass("active"); //왼쪽 카테고리 '도서검색' 활성화

	$(".cart_btn").click(function() {
		
		let email = $('.user_email').val(); 
		let book_isbn = $('.book_isbn').val(); 
		
		
		if(email == "") {
			alert("로그인 후 이용해주세요");
			location.href="/member/login";
		} else {
			
			if (confirm("장바구니에 담겠습니까?")) {
			
				let data = {
           				book_isbn: book_isbn
           		};
				
				$.ajax({
           			type: "post",
           			url: "/search/cartChk",
           			data: data,
           			success: function(result) {
           				
           				if (result == "success") {
           					alert("내 장바구니 등록되었습니다.");
           					$("#cart").attr("action", "/search/cart?detail=not");
           					$("#cart").attr("onsubmit", "return true;");
           					$("#cart").submit();
       						
           				} else if (result == "alreadyCart"){
           					alert("이미 담긴 도서입니다.");
           				} 
           			}
           		});
			
			}
			
			
		} 
			
	});
	
	
});


</script>

</body>
</html>