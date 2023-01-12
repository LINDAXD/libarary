<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라온도서관 > 관리자 > 도서관련 > 대출내역</title>
<link rel="stylesheet" href="/resources/css/admin/member_list.css">
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
                    <h3>대출내역</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li class="first" style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/admin/member-list">관리자</a>
                        </li>
                        <li>
                            <a href="/admin/loan-history">도서관련</a>
                        </li>
                        <li>
                            <a href="/admin/loan-history">대출내역</a>
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

                            <!--검색-->
                            <div class="search">
                                <form action="" onsubmit="return false;">
                                    <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                                    <input type="hidden" name="page" value="1">
                                    <select name="type">

                                        <c:if test="${empty pageMaker.cri.type}">
                                            <option value="user_id" selected="selected">아이디</option>
                                            <option value="book_title">도서명</option>
                                            <option value="book_isbn">ISBN</option>
                                            <option value="loan_date">대출날짜</option>
                                        </c:if>


                                        <c:if test="${pageMaker.cri.type == 'user_id'}">
                                            <option value="user_id" selected="selected">아이디</option>
                                            <option value="book_title">도서명</option>
                                            <option value="book_isbn">ISBN</option>
                                            <option value="loan_date">대출날짜</option>
                                        </c:if>

                                        <c:if test="${pageMaker.cri.type == 'book_title' }">
                                            <option value="user_id">아이디</option>
                                            <option value="book_title" selected="selected">도서명</option>
                                            <option value="book_isbn">ISBN</option>
                                            <option value="loan_date">대출날짜</option>
                                        </c:if>

                                        <c:if test="${pageMaker.cri.type == 'book_isbn' }">
                                            <option value="user_id">아이디</option>
                                            <option value="book_title">도서명</option>
                                            <option value="book_isbn" selected="selected">ISBN</option>
                                            <option value="loan_date">대출날짜</option>
                                        </c:if>
                                        
                                        <c:if test="${pageMaker.cri.type == 'loan_date' }">
                                            <option value="user_id">아이디</option>
                                            <option value="book_title">도서명</option>
                                            <option value="book_isbn">ISBN</option>
                                            <option value="loan_date" selected="selected">대출날짜</option>
                                        </c:if>

                                    </select>
                                    <input type="text" name="keyword" value="${pageMaker.cri.keyword }"
                                        autocomplete="off">
                                    <button id="search_btn" class="btn">검색</button>

                                </form>

                            </div>
                            <!-- 대출 도서 수 -->
                            <div class="inline">
                                <form action="">

                                    <span style="margin-right: 10px;"> 대출 중 : <b>${total }</b> 건</span>
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
                                    <input type="hidden" name="type" value="${pageMaker.cri.type }">
                                    <input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
                                    <button id="list_btn" class="btn">이동</button>
                                </form>

                            </div>

                            <!-- 테이블 -->
                            <c:if test="${not empty loan_history }">
                            <div class="table-wrap">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 100px;">아이디</th>
                                            <th style="">도서명</th>
                                            <th style="">ISBN</th>
                                            <th style="width: 90px;">대출날짜</th>
                                            <th style="width: 90px">반납날짜</th>
                                            <th style="width: 90px">반납예정일</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="loan_history" items="${loan_history}">
                                            <tr>
                                                <td>${loan_history.user_id }</td>
                                                <td>${loan_history.book_title }</td>
                                                <td>${loan_history.book_isbn}</td>
                                                <td>${loan_history.loan_date }</td>
                                                <c:if test="${empty loan_history.return_date }">
												<td><span style="color:red; font-weight: bold">미반납</span></td>
												</c:if>
												
												<c:if test="${not empty loan_history.return_date }">
												<td>${loan_history.return_date }</td>
												</c:if>
                                                <td>${loan_history.return_period }</td>
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
                                        <a class="${pageMaker.cri.page == num ? " current":"not" }"
                                            href="${num }"><span>${num }</span></a>
                                    </c:forEach>

                                    <c:if test="${pageMaker.next }">
                                        <a class="not" href="${pageMaker.endPage + 1}">다음</a>
                                    </c:if>
                                </div>
                            </div>
                            </c:if>

                        </div>
                        <c:if test="${empty loan_history }">
                        <br>
                        <h2>조회 된 도서가 없습니다.</h2>
                        </c:if>

                    </div>

                </div>

            </div>
        </div>
    </div>

    <form method="get" class="moveForm">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
        <input type="hidden" name="page" value="${pageMaker.cri.page }">
        <input type="hidden" name="type" value="${pageMaker.cri.type }">
        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
    </form>

    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>


    <script>

        $(function () {
            $(".sub2").addClass("active");
            $(".submenu1").addClass("active");

            let moveForm = $(".moveForm");

            //pageInfo의 a태그를 누르면 a태그의 href 속성을 가져와서 moveForm의 page에 넣고 moveForm이 submit됨
            $(".pageInfo a").on("click", function (e) {
                e.preventDefault();
                moveForm.find("input[name = 'page']").val($(this).attr("href"));
                moveForm.submit();
            });

            // 검색 버튼을 누를 시 공백이면 alert
            $("#search_btn").on("click", function (e) {
                let keyword = $("input[name='keyword']").val();
                if (keyword == "") {
                    alert("검색어를 입력해주세요.");
                } else {
                    $("form").attr("onsubmit", "return true");
                    $("form").submit();
                }

            });

        });

    </script>

</body>
</html>