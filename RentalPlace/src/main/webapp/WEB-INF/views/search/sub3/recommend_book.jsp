<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>
<html>
<head>
	<title>라온도서관 > 자료검색 > 추천도서</title>
</head>
<link rel="stylesheet" href="/resources/css/search/recommend_book.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>

<body>
	<sec:authorize access="isAuthenticated()">
	<input type="hidden" class="user_id" value=<sec:authentication property="principal.dto.user_id"/>>
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
	<input type="hidden" class="user_id" value="">
	</sec:authorize>
	
	<div class="header">
    <jsp:include page="../../header.jsp"></jsp:include>
    </div>
    
    <div class="container">
        <div class="sub_title">
            <div class="doc-info">
                <!-- doc title -->
                <div class="doc-title">
                    <h3>추천도서</h3>
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
                            <a href="/search/recommend-book">추천도서</a>
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
                        <div class="wrapper-bbs">
                        	<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div>
								<form>
	        						<input class="btn add_btn" type="submit" value="등록" style="float:left;">
	        					</form>
							</div>
							</sec:authorize>
                            <!-- 도서 수 -->
                            <div class="inline">
                                <form action="">
                                	추천년월:
                                    <select name="year" style="width: 75px;">
                                        <c:forEach var="i" begin="2020" end="2022">
                                    		<option value="${i}" ${i== year ? "selected=selected":'' }>
                                    		${i }년
                                    		</option>
                                    	</c:forEach>
                                    </select>
                                    <select name="month" style="width: 60px;">
                                    	<c:forEach var="i" begin="1" end="12">
                                    		<option value="${i}" ${i== month ? "selected=selected":'' }>
                                    		${i }월
                                    		</option>
                                    	</c:forEach>
                                    </select>
                                    <input type="hidden" name="page" value="1">
                                    <button class="btn">이동</button>
                                </form>

                            </div>

							<c:if test="${not empty list }">
                            <!-- 테이블 -->
                            <div class="recommend_book">
                                <div class="clear_box"></div>
                                <c:forEach var="list" items="${list}">
                                    <div class="row">
                                        <div class="thumb">
                                        	<a href="/search/recommend-book-detail?book_isbn=${list.book_isbn }&year=${year}&month=${month}&page=${pageMaker.cri.page}">
                                            <img src="${list.book_cover }" style="width: 118px;" title="${list.book_title }">
                                            </a>
                                        </div>

                                        <div class="book_info">
                                            <ul>
                                                <li>
                                                	<a href="/search/recommend-book-detail?book_isbn=${list.book_isbn }&year=${year}&month=${month}&page=${pageMaker.cri.page}">
                                                    <h3 class="hide" title="${list.book_title }">${list.book_title } </h3>
                                                    </a>
                                                <li>
                                                <li><b>저자</b> : ${list.book_author }</li>
                                                <li><b>출판사</b> : ${list.book_publisher }</li>
                                                <li><b>출판년도</b> : ${list.book_pubDate }</li>
                                            </ul>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            </c:if>
                            
                        </div>
						<c:if test="${empty list }">
						<br>
                        <h2>등록된 추천 도서가 없습니다.</h2>
                        </c:if>
                    </div>
                </div>
			
            </div>
            
        </div>
        <div class="pageInfo" style="text-align: center; width:970px; margin-left: 600px;">
								
	         <c:if test="${pageMaker.prev }">
	             <a class="not" href="${pageMaker.startPage - 1}">이전</a>
	         </c:if>
	
	         <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
	
	             <c:if test="${num != 0}">
	                 <a class="${pageMaker.cri.page == num ? " current":"not" }" href="${num }">${num }</a>
	             </c:if>
	
	         </c:forEach>
	
	         <c:if test="${pageMaker.next }">
	             <a class="not" href="${pageMaker.endPage + 1}">다음</a>
	         </c:if>
	     </div>  
            
    </div>

    <form method="get" class="moveForm">
        <input type="hidden" name="year" value="${year }">
        <input type="hidden" name="month" value="${month }">
        <input type="hidden" name="page" value="${pageMaker.cri.page }">
    </form>

	<br><br><br>
    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>
    <script>
        $(function () {
            $(".sub3").addClass("active");

            let moveForm = $(".moveForm");

            $(".pageInfo a").on("click", function (e) {
                e.preventDefault();
                moveForm.find("input[name = 'page']").val($(this).attr("href"));
                moveForm.submit();

            });
            
			$('.add_btn').on("click",function(e){
				
				e.preventDefault();
				
				let user_id = $(".user_id").val();
				
				if(user_id == "") {
					alert("로그인 후 이용하세요.");
					window.location.href="/member/login";
				} else {
					let popUrl = "/search/regist-book";
					let popOption = "width = 710px, height=600px, top=300px, scrollbars=no, resizeable=no";
					window.open(popUrl,"도서 등록" ,popOption);
				}
				
			});

        });
    </script>

</body>
</html>
