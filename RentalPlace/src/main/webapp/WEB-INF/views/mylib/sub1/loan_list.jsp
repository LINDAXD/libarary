<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라온도서관 > 나의도서관 > 도서관련 > 대출중도서조회</title>
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
                    <h3>대출중도서조회</h3>
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
                        <li>
                            <a href="/mylib/loan-history">도서관련</a>
                        </li>
                        <li>
                            <a href="/mylib/loan-list">대출중도서조회</a>
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

                            <!-- 회원 수 -->
                            <!-- <div class="inline">
                            	<span style="margin-right: 10px;"> 건</span>
                            </div> -->
                            
                            <div class="loan_box">
                            	<ul>
                            		<li>
                            			<img src="/resources/imges/mylib/loan_icon01.png">
                            			<h4 style="margin: 0">대출 중 권수</h4> 
                            			<span>${total }</span>
                            		</li>
                            		
                            		<li>
                            			<img src="/resources/imges/mylib/loan_icon02.png">
                            			<h4 style="margin: 0">대출 연체 권수</h4> 
                            			<span>${overdue_count }</span>
                            		</li>
                            		
                            		<li>
                            			<img src="/resources/imges/mylib/loan_icon03.png">
                            			<h4 style="margin: 0">대출 정지 만기일</h4>
                            			<span>${overdue_date }</span>
                            		</li>
                            	</ul>
                            </div>

                            <!-- 테이블 -->
                            <c:if test="${not empty loan_list }">
                            <div class="table-wrap">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="">도서명</th>
                                            <th style="">저자</th>
                                            <th style="width: 90px;">대출날짜</th>
                                            <th style="width: 90px">반납예정일</th>
                                            <th>상태</th>
	                                </tr>
                                    </thead>
                                    <tbody>
                                    
	                                    <c:forEach var="loan_list" items="${loan_list}">
										<tr>
											<td>${loan_list.book_title }</td>
											<td>${loan_list.book_author}</td>
											<td>${loan_list.loan_date }</td>
											<td>${loan_list.return_period }</td>
											<c:if test="${loan_list.overdue_date < 0 }">
												<td><span style="color:red; font-weight: bold">연체</span></td>
											</c:if>
											
											<c:if test="${loan_list.overdue_date >= 0 }">
												<td><span>정상</span></td>
											</c:if>
										</tr>
										</c:forEach>
	                                        
                                    </tbody>
                                </table>
                            </div>
                            </c:if>
                            
                            <c:if test="${empty loan_list }">
							<h2>현재 대출 중인 도서가 없습니다.</h2>
							</c:if>

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
			$(".sub1").addClass("active");
			$(".submenu2").addClass("active");
		});
		
	</script>	


</body>
</html>