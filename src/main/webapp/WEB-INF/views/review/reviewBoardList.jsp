<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>
<html>
<head>
	<title>라온도서관 > 열린공간 > 묻고답하기</title>
</head>
	<link rel="stylesheet" href="/resources/css/board/sub3/qna_page.css">
	<link rel="stylesheet" href="/resources/css/header.css">
	<link rel="stylesheet" href="/resources/css/footer.css">
	<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" 
  crossorigin="anonymous"></script>
<body>

	<div class="header">
    <jsp:include page="../header.jsp"></jsp:include>
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
                <jsp:include page="lnb.jsp"></jsp:include>

                <!-- 본문 -->
                <div class="content">
                    <div class="doc">
                        <div class="wrapper-bbs" style="">

                            <!-- 테이블 -->
                            <div class="table-wrap">
                                <h2>도서의 상세 내용을 확인하세요</h2>
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 200px;">이미지</th>
                                            <th style="">도서 정보</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="">
                                                <img src="${book.book_cover }" style="width: 200px;">
                                            </td>
                                            <td class="" style="text-align: left;">
                                                <h2>${book.book_title }</h2>
                                                <p><b>저자명</b> : ${book.book_author }</p>
                                                <p><b>출판사</b>: ${book.book_publisher }</p>
                                                <p><b>출간일</b> : ${book.book_pubDate }</p>
                                                <p>
                                                <b>가격</b>: 
                                                <fmt:formatNumber value="${book.priceStandard }" type="currency"/>
                                                </p>
                                            </td>

                                        </tr>
                                    
                                    </tbody>
                               
                                </table>

                            </div>

                        </div>

                    </div>
                                        <div style="display: flex; justify-content: center; align-items: center;">
                    
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
                            <button id="loan_btn" class=" btn2">대출 (${count }  / 2)</button>
                        </form>

                        <span>　</span>

                        <form action="/search/book">
                        	<input type="hidden" name="amount" value="${cri.amount }">
							<input type="hidden" name="page" value="${cri.page }">
							<input type="hidden" name="type" value="${cri.type }">
							<input type="hidden" name="keyword" value="${cri.keyword }">
                            <button class="btn3" style="display: flex; justify-content: center; align-items: center;">
                                <img src="/resources/imges/search/book_icon.png" style="width: 30px;">　목록
                            </button>
                        </form>

                    </div>

                    <h3>책 소개</h3>
                    <div style="border: 1px solid #ccc; height: 500px;">
                        상품 설명
                        ${book.description}

                    </div>
                <div class="review_title">
                <h3>REVIEW</h3><button class="write_btn" onclick="location.href='/board/qnaBoardWrite'" style="cursor: pointer">리뷰작성하기</button>
                <form id="review_form" method="POST" onsubmit="return false;">
                <textarea type="text" class="review_input" name="review_input" placeholder="후기를 작성해주세요." cols="140" rows="10" ></textarea>
                </form>
                </div>
                </div>

            </div>
        </div>
    </div>
        
        <div class="section">
            <div class="doc">

                <!-- 왼쪽 사이드바 -->
                <jsp:include page="lnb.jsp"></jsp:include>

                <!-- 본문 -->
                <div class="content">
                    <div class="doc"> 
                        <div class="wrapper-bbs">
                            <!-- 게시물 수 -->
                            <div class="inline">
                            	<form action="">
                            		<input type="hidden" name="type" value="${pageMaker.cri.type}">
                                	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                            	
	                                <span style="margin-right: 10px;"> 총 게시물 : <b>${total}</b> 건</span>
	                                <select name="amount">
	                                
	                                <c:if test="${pageMaker.cri.amount == 10}">
	                                    <option value="10" selected="selected">10개씩 보기</option>
	                                    <option value="20">20개씩 보기</option>
	                                    <option value="30">30개씩 보기</option>
	                                    <option value="40">40개씩 보기</option>
	                                </c:if>
	                                
	                                <c:if test="${pageMaker.cri.amount == 20}"> 
	                                    <option value="10">10개씩 보기</option>
	                                    <option value="20" selected="selected">20개씩 보기</option>
	                                    <option value="30">30개씩 보기</option>
	                                    <option value="40">40개씩 보기</option>
	                                </c:if>
	                                
	                                <c:if test="${pageMaker.cri.amount == 30}"> 
	                                    <option value="10">10개씩 보기</option>
	                                    <option value="20">20개씩 보기</option>
	                                    <option value="30" selected="selected">30개씩 보기</option>
	                                    <option value="40">40개씩 보기</option>
	                                </c:if>
	                                
	                                <c:if test="${pageMaker.cri.amount == 40}"> 
	                                    <option value="10">10개씩 보기</option>
	                                    <option value="20">20개씩 보기</option>
	                                    <option value="30">30개씩 보기</option>
	                                    <option value="40" selected="selected">40개씩 보기</option>
	                                </c:if>
	                                
	                                </select>
	                                
	                                <input type="hidden" name="page" value="1">
	                                <button class="btn">이동</button>
                                </form>

                            </div>

                            <!-- 테이블 -->
                            <div class="table-wrap">
                            	<c:if test="${not empty qnaBoardList }">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 12%;">번호</th>
                                            <th style="width: 18%;">작성자</th>
                                            <th style="width: 12%;">작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="eBod" items="${reviewBoardList}">
                                    
                                        <tr>
                                            <td class="num">${eBod.review_no}</td>
                                            <td class="left contentGo" style="padding-left: 15px;">
 												<a href="${eBod.review_no}">${eBod.review_title}</a>
                                            </td>
                                            <td>${eBod.writer_name}</td>
                                            <td>
                                            	<fmt:formatDate var="review_reg_date" value="${eBod.review_reg_date}" pattern="yyyy-MM-dd"/>
												${review_reg_date}
                                            </td>
                                            <td>${eBod.review_hits}</td>
                                        </tr>
                                        
                                        <c:if test="${eBod.answerList.answer_title != null}">
                                        <tr>
                                            <td class="num"><input type="hidden" value="${eBod.reviewanswerList.reviewanswer_no}"></td>
                                            <td class="left reviewanswerContentGo" style="padding-left: 15px;">
 												<a href="${eBod.reviewanswerList.reviewanswer_no}">
 												<img src=/resources/imges/common/icon_reply.gif alt="답변 아이콘 이미지">
 													${eBod.reviewanswerList.reviewanswer_title}
 												</a>
                                            </td>
                                            <td>관리자</td>
                                            <td>
                                            	<fmt:formatDate var="reviewanswer_reg_date" value="${eBod.reviewanswerList.answer_reg_date}" pattern="yyyy-MM-dd"/>
												${answer_reg_date}
                                            </td>
                                            <td>${eBod.reviewanswerList.reviewanswer_hits}</td>
                                        </tr>
                                        </c:if>
                                    </c:forEach>   
                                    </tbody>
                                </table>
                                
                                </c:if>
                                <c:if test="${empty reviewBoardList }">
                                <br>
                                <h2>조회된 게시글이 없습니다.</h2>
								</c:if>
								
								
                                <!-- 글쓰기 btn -->
                                <button class="write_btn" onclick="location.href='/review/reviewBoardWrite'" style="cursor: pointer">글쓰기</button>
                                
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
								
                                <br>
                                
                                <div class="searchBox">
                                	<form action="/review/reviewBoardList" method="get">
                                		<input type="hidden" name="page" value="1">
                                		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                                		
                                		<select name="type">
                                		<c:choose>
                                			<c:when test="${pageMaker.cri.type eq 'title'}">
                                				<option value="TC">제목+내용</option>
		                                		<option value="title" selected="selected">제목</option>
		                                		<option value="content">내용</option>
                                			</c:when>
                                			
                                			<c:when test="${pageMaker.cri.type eq 'content'}">
                                				<option value="TC">제목+내용</option>
		                                		<option value="title">제목</option>
		                                		<option value="content" selected="selected">내용</option>
                                			</c:when>
                                		
                                			<c:otherwise>
                                				<option value="TC" selected="selected">제목+내용</option>
		                                		<option value="title">제목</option>
		                                		<option value="content">내용</option>
                                			</c:otherwise>
                                		</c:choose>
                                		</select>
                                	</form>
                                </div>
                                
                                                 
                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div> 
    
    <form action="/review/reviewBoardList" method="get" class="moveForm">
		<input type="hidden" name="page" value="${pageMaker.cri.page}">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
	</form>
	
	<form action="/review/reviewBoardContent" method="get" class="moveForm2">
		<input type="hidden" name="page" value="${pageMaker.cri.page}">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="type" value="${pageMaker.cri.type}">
		<input type="hidden" name="review_no" value="">
		<input type="hidden" name="reviewanswer_no" value="">
	</form>


    <!-- footer -->
    <jsp:include page="../footer.jsp"></jsp:include>

	
<script>

	$(function() {
		$(".sub3").addClass("active");
		var moveForm = $(".moveForm");
		var moveForm2 = $(".moveForm2");

		$(".pageInfo a").on("click", function(e) {
			e.preventDefault();
			moveForm.find("input[name = 'page']").val($(this).attr("href"));
			moveForm.submit();
		})
		
		$(".answerContentGo a").on("click", function(e) {
			e.preventDefault();
			moveForm2.find("input[name = 'reviewanswer_no']").val($(this).attr("href"));
			moveForm2.attr("action", "/review/reviewanswerBoardContent");
			moveForm2.submit();
			
		}); 
		
		$(".contentGo a").on("click", function(e) {
			e.preventDefault();
			moveForm2.find("input[name = 'review_no']").val($(this).attr("href"));
			moveForm2.attr("action", "/review/reviewBoardContent");
			moveForm2.submit();
			
		}); 
		
		
		
		
		
	});

	
</script>
</body>
</html>
