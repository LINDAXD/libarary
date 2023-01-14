<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>
<html>
<head>
   <title>라온도서관 > 열린공간 > 분실물찾기</title>
</head>
<link rel="stylesheet" href="/resources/css/board/sub4/sb_page.css">
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
                    <h3>분실물찾기</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li class="first" style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/board/noticeList">열린공간</a>
                        </li>
                        <li>
                            <a href="/board/articleList">분실물찾기</a>
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
                                   
                                    <c:if test="${pageMaker.cri.amount == 20 }"> 
                                       <option value="10">10개씩 보기</option>
                                       <option value="20" selected="selected">20개씩 보기</option>
                                       <option value="30">30개씩 보기</option>
                                       <option value="40">40개씩 보기</option>
                                   	</c:if>
                                                                      
                                   	<c:if test="${pageMaker.cri.amount == 30 }"> 
                                       <option value="10">10개씩 보기</option>
                                       <option value="20">20개씩 보기</option>
                                       <option value="30" selected="selected">30개씩 보기</option>
                                       <option value="40">40개씩 보기</option>
                                   	</c:if>
                                                                 
                                 	<c:if test="${pageMaker.cri.amount == 40 }"> 
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
                            	<c:if test="${not empty articleList }">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 12%;">번호</th>
                                            <th>제목</th>
                                            <th style="width: 18%;">작성자</th>
                                            <th style="width: 12%;">작성일</th>
                                            <th style="width: 15%;">조회수</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="articleList" items="${articleList}">
                                        <tr>
                                            <td class="num">${articleList.article_no}</td>
                                            <td class="left articleContent" style="padding-left: 15px;">
                                            	<a href="${articleList.article_no}">${articleList.article_title}</a></td>
                                			<td>관리자</td>
                                            <td>
                                               <fmt:formatDate var="article_reg_date" value="${articleList.article_reg_date}" pattern="yyyy-MM-dd"/>
                                    				${article_reg_date}
                                            </td>
                                          <td>${articleList.article_views}</td>
                                        </tr>
                                    </c:forEach>   
                                    </tbody>
                                </table>
                                </c:if>
                                <c:if test="${empty articleList }">
                                <br>
                                <h2>조회된 게시글이 없습니다.</h2>
								</c:if>
            
                             <!-- 글쓰기 btn --> 
                             <sec:authorize access="hasRole('ROLE_ADMIN')">
                             <button class="write_btn" onclick="location.href='/board/articleInsertForm'" style="cursor: pointer">글쓰기</button>
                             </sec:authorize>
                             
                             <br>
                             
                             <!-- 페이징 -->
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
                            
                            <!-- 검색 -->  
                           	<div class="searchBox">   
                             	<form action="/board/articleList" method="get">
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
									
									
		                              	<c:choose>
			                              <c:when test="${fn:length(pageMaker.cri.keyword) == 0}">
			                              	<input type="text" name="keyword" placeholder="검색어를 입력하세요" autocomplete="off">
			                              </c:when>
			                              	
			                              <c:otherwise>
			                              	<input type="text" name="keyword" value="${pageMaker.cri.keyword}" autocomplete="off">
			                              </c:otherwise>                             
		                              </c:choose>
	                              
	                              <input class="search_btn" type="submit" value="검색">
                              	</form>
                             </div>       
                                           
                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>

	<form action="/board/articleList" method="get" class="moveForm">
      <input type="hidden" name="page" value="${pageMaker.cri.page}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
      <input type="hidden" name="type" value="${pageMaker.cri.type}">
   </form>
   
   <form action="/board/articleContent" method="get" class="moveForm2">
      <input type="hidden" name="page" value="${pageMaker.cri.page}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
      <input type="hidden" name="type" value="${pageMaker.cri.type}">
      <input type="hidden" name="article_no" value="">
   </form>
   
    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>
   
<script>
   $(function() {
   		$(".sub4").addClass("active");  
      
		var moveForm = $(".moveForm");
		var moveForm2 = $(".moveForm2");
      
      
      	$(".pageInfo a").on("click", function(e) {			
			
      		/*  새로고침(reload) 방지 */
      		e.preventDefault();			
			moveForm.find("input[name = 'page']").val($(this).attr("href"));
			moveForm.submit();			
		})
		
		$(".articleContent a").on("click", function(e) {
	         e.preventDefault();
	         moveForm2.find("input[name = 'article_no']").val($(this).attr("href"));
	         moveForm2.attr("action", "/board/articleContent");
	         moveForm2.submit();
	         
	      }) 
		
		
   })
</script>
</body>
</html>