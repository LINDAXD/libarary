<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>이젠북컴퍼니 > 자료검색 > 도서검색</title>
</head>
<link rel="stylesheet" href="/resources/css/search/book_detail.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" 
  crossorigin="anonymous"></script>

<body>

	<div class="header">
    <jsp:include page="../../header.jsp"></jsp:include>
    </div>

    <div class="container">
        <div class="sub_title">
            <div class="doc-info">
                <!-- doc title -->
                <div class="doc-title">
                    <h3>도서검색</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li class="first" style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/search/book">자료검색</a>
                        </li>
                        <li>
                            <a href="/search/book">도서검색</a>
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

                            <!-- 테이블 -->
                            <div class="table-wrap">
                                <h2>도서의 상세 내용을 확인하세요</h2>
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 200px;">이미지</th>
                                            <th style="width: 500px;">도서 정보</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="book_img">
                                                <img class="cover" src="${book.book_cover }" style="width: 200px;">
                                             
                                            </td>
                                            <td class="book_detail" style="text-align: left;">
                                                <h2>${book.book_title }</h2>
                                                <p><b>저자명</b> : ${book.book_author }</p>
                                                <p><b>출판사</b>: ${book.book_publisher }</p>
                                                <p><b>출간일</b> : ${book.book_pubDate }</p>
                                                <p>
                                                <b>가격</b>: 
                                                <fmt:formatNumber value="${book.priceStandard }" type="currency"/>
                                                </p>
                                                <div style="display: flex;align-items: flex-end;">
                    
													<!-- 추가 -->
															<c:if test="${not empty book}">
															<c:set var="list" value="${list}"/> 
																<form action="/buy/book_buy" method="get">
																	<input type="hidden" class="book_isbn" name="book_isbn" value="${book.book_isbn }">
																	<input type="hidden" name="book_title" value="${book.book_title }">
																	<input type="hidden" name="book_author" value="${book.book_author }">
																	<input type="hidden" name="book_cover" value="${book.book_cover }">
																	<input type="hidden" name="book_pubDate" value="${book.book_pubDate }">
																	<input type="hidden" name="book_publisher" value="${book.book_publisher }">
																	<input type="hidden" name="amount" value="${cri.amount }">
																	<input type="hidden" name="page" value="${cri.page }">
																	<input type="hidden" name="type" value="${cri.type }">
																	<input type="hidden" name="keyword" value="${cri.keyword }">
																	<input type="hidden" name="priceStandard" value="${book.resultpriceStandard}">
																	<button id="buy" class=" btn2">구매</button>
																</form>
														</c:if>
													<!-- 추가 -->
												
													<form id="loan" onsubmit="return false;" method="post">
													
														<sec:authorize access="isAuthenticated()">
														<input type="hidden" class="user_email" name="user_email" 
															value=<sec:authentication property="principal.dto.user_email"/>>
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
														<button id="loan_btn" class=" btn2">대출 </button>
													</form>
													<form action="/search/book">
														<input type="hidden" name="amount" value="${cri.amount }">
														<input type="hidden" name="page" value="${cri.page }">
														<input type="hidden" name="type" value="${cri.type }">
														<input type="hidden" name="keyword" value="${cri.keyword }">
													</form>
													<!--============ 찜하기 추가 =========== -->
													<form id="like" onsubmit="return false;" method="post">
													
														<sec:authorize access="isAuthenticated()">
														<input type="hidden" class="user_email" name="user_email" 
															value=<sec:authentication property="principal.dto.user_email"/>>
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
														<button id="like_btn" class=" btn2">찜하기</button>
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
														
														<input type="hidden" name="book_title" value="${book.book_title }">
														<input type="hidden" name="book_author" value="${book.book_author }">
														<input type="hidden" class="book_isbn" name="book_isbn" value="${book.book_isbn }">
														<input type="hidden" name="book_cover" value="${book.book_cover }">
														<input type="hidden" name="book_pubDate" value="${book.book_pubDate }">
														<input type="hidden" name="book_publisher" value="${book.book_publisher }">
														<input type="hidden" name="priceStandard" value="${book.priceStandard }">
														
														<input type="hidden" name="amount" value="${cri.amount }">
														<input type="hidden" name="page" value="${cri.page }">
														<input type="hidden" name="type" value="${cri.type }">
														<input type="hidden" name="keyword" value="${cri.keyword }">
														
														<button id="cart_btn" class=" btn2">장바구니 담기</button>
														
													   
													</form>
							
												</div>
                                            </td>
                                        </tr>
                                    
                                    </tbody>
                               
                                </table>

                            </div>

                        </div>

                    </div>
                    

                    <h3>책 소개</h3>
                    <div style="border: 1px solid #ccc; height: 500px;">
                        상품 설명
                        ${book.description}

                    </div>
                    
                    					<!-- 추가 -->
					<div class="container">
						<br> <br>
						<sec:authorize access="isAuthenticated()">
						<div>
							<div>
								<span><strong>Review</strong></span> <span id="cCnt"></span>
							</div>

							<form action="/review/reviewBoardInsert" method="post"
								id="review_form" onsubmit="return fn_review_write();">
								
								<input type="hidden" name="amount" value="${cri.amount }">
								<input type="hidden" name="page" value="${cri.page }"> <input
									type="hidden" name="type" value="${cri.type }"> <input
									type="hidden" name="keyword" value="${cri.keyword }">
								<textarea class="review_input" style="width: 800px" rows="10"
									cols="30" id="review_input" name="review_content"
									placeholder="후기를 작성해주세요." cols="140" rows="10"></textarea>
									<input type="hidden" class="book_isbn" name="book_isbn"
									value="${book.book_isbn }"> <input type="submit"
									class="write_btn" style="cursor: pointer" value="리뷰작성하기">
									
									
							</form>
						</div>
</sec:authorize>
					</div>
					<div>
						<span><strong>Review 리스트</strong></span> <span id="cCnt"></span>
					</div>
					<c:if test="${not empty reviewList}">
						<ol>
							<c:forEach var="list" items="${reviewList}">
								<li>
									<div class="review_id">${list.writer_id}
										<sec:authorize access="isAuthenticated()">
										<button type="button" class="delete_btn" style="cursor: pointer" onclick="reviewDelete(${list.review_no},${book.book_isbn })"><img src="/resources/imges/search/delete.jpg" alt="삭제" class="deletepng"><img src="/resources/imges/search/deleteopen.jpg" alt="삭제" class="deletepngopen"></button>
										<a href="http://blog.naver.com/imagine0716" id="mouseover"></a>
									</sec:authorize>
								</div>
									<div class="review_content">${list.review_content }</div>
								</li>
							</c:forEach>

						</ol>
					</c:if>

							<form action="/review/reviewBoardDelete" method="post" id="delete_form">
								<input type="hidden" class="book_isbn" name="book_isbn"	id="book_isbn" value="">
								
							<input type="hidden" name="amount" value="${cri.amount }">
								<input type="hidden" name="page" value="${cri.page }">
								<input type="hidden" name="type" value="${cri.type }">
								<input type="hidden" name="keyword" value="${cri.keyword }">
								<input type="hidden" name="amount" value="${repList.rno}">
								<input type="hidden" name="review_no" id="review_no" value="${list.review_no}">
							</form>


					<input type="hidden" id="b_code" name="b_code"
						value="${result.code }" />
					
					<!-- 추가 -->

                </div>
                
            </div>

        </div>

    </div>
 
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
	<script>
	
		$(function() {
			$(".sub1").addClass("active");
			 
			$("#loan_btn").click(function() {
				
				let email = $('.user_email').val(); 
				let book_isbn = $('.book_isbn').val(); 
				
				if(email == "") {
					alert("로그인 후 이용해주세요");
					location.href="/member/login";
				} else {
					
					if (confirm("도서를 대출하시겠습니까?")) {
					
						let data = {
		           				book_isbn: book_isbn
		           		};
						
						$.ajax({
		           			type: "post",
		           			url: "/search/statusChk",
		           			data: data,
		           			success: function(result) {
		           				
		           				if (result == "success") {
		           					alert("대출이 완료되었습니다.");
		           					$("#loan").attr("action", "/search/loan?detail=not");
		           					$("#loan").attr("onsubmit", "return true;");
		           					$("#loan").submit();
		       						
		           				} else if (result == "loan"){
		           					alert("이미 대출 중인 도서입니다.");
		           				} else {
		           					alert("대출이 불가능한 상태입니다.");
		           				}
		           			}
		           		});
					
					}
					
					
				} 
					
			});
		});
	</script>


<!-- 찜하기 추가  -->	
<script type="text/javascript">
	$(function() {
		$(".sub1").addClass("active"); //왼쪽 카테고리 '도서검색' 활성화
		 
		$("#like_btn").click(function() {
			
			let email = $('.user_email').val(); 
			let book_isbn = $('.book_isbn').val(); 
			
			if(email == "") {
				alert("로그인 후 이용해주세요");
				location.href="/member/login";
			} else {
				
				if (confirm("도서를 찜하시겠습니까?")) {
				
					let data = {
	           				book_isbn: book_isbn
	           		};
					
					$.ajax({
	           			type: "post",
	           			url: "/search/likeChk",
	           			data: data,
	           			success: function(result) {
	           				
	           				if (result == "success") {
	           					alert("내 찜리스트에 등록되었습니다.");
	           					$("#loan").attr("action", "/search/like?detail=not");
	           					$("#loan").attr("onsubmit", "return true;");
	           					$("#loan").submit();
	       						
	           				} else if (result == "alreadyLike"){
	           					alert("이미 찜한 도서입니다.");
	           				} 
	           			}
	           		});
				
				}
				
				
			} 
				
		});
	});
	</script>	
<script>
$(function() {
	$(".sub1").addClass("active"); //왼쪽 카테고리 '도서검색' 활성화

	$("#cart_btn").click(function() {
		
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

<script type="text/javascript">
/*
 * 댓글 등록하기(Ajax)
 */
 

 function reviewDelete(review_no,book_isbn){
	 $('#review_no').val(review_no);
	 $('#book_isbn').val(book_isbn);


		if(confirm("삭제하시겠습니까?")){
			alert("게시물이 삭제되었습니다.");
		
			$("#delete_form").submit();
		}

}
 
function fn_review_write(){
	var review_input = $('#review_input').val();
	if(review_input){
		alert('리뷰가 등록되었습니다.');
		return true;
	}else{
		alert('리뷰내용을 입력하세요!');
		return false;
	}
}


/*
 * 댓글 등록하기(Ajax)
 */
function fn_review(code){
    
    $.ajax({
        type:'POST',
        url : "<c:url value='/review/reviewBoardInsert'/>",
        data:$("#reviewForm").serialize(),
        success : function(data){
            if(data=="success")
            {
                getreviewList();
                $("#review").val("");
            }
        },
        error:function(request,status,error){
            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
        
    });
}
 
/**
 * 초기 페이지 로딩시 댓글 불러오기
 */
$(function(){
    
    getreviewList();
    
});
 
/**
 * 댓글 불러오기(Ajax)
 */
function getreviewList(){
    
    $.ajax({
        type:'GET',
        url : "<c:url value='/review/reviewBoardInsert'/>",
        dataType : "json",
        data:$("#reviewForm").serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){
            
            var html = "";
            var cCnt = data.length;
            
            if(data.length > 0){
                
                for(i=0; i<data.length; i++){
                    html += "<div>";
                    html += "<div><table class='table'><h6><strong>"+data[i].writer+"</strong></h6>";
                    html += data[i].review + "<tr><td></td></tr>";
                    html += "</table></div>";
                    html += "</div>";
                }
                
            } else {
                
                html += "<div>";
                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div>";
                
            }
            
            $("#cCnt").html(cCnt);
            $("#reviewList").html(html);
            
        },
        error:function(request,status,error){
            
       }
        
    });
}


</script>
</body>
</html>
