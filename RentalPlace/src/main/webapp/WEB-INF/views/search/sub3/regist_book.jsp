<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라온도서관 > 추천도서 > 추천도서등록</title>
<link rel="stylesheet" href="/resources/css/search/popup.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" 
 integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
 crossorigin="anonymous"></script>
</head>
<body>

    <div class="container">

        <div class="section">
            <div class="doc">
                <!-- 본문 -->
                <div class="content">
                    <div class="doc">
                        <div class="wrapper-bbs">

                            <!--검색-->
                            <div class="search">
                                <form action="" method="get">
                                    <input type="hidden" name="amount" value="${cri.amount }">
                                    <input type="hidden" name="page" value="1">

                                    <select name="type">
                                        <option value="Title" selected="selected">도서명</option>
                                    </select>

                                    <input type="text" style="height: 34px;" name="keyword" value="${cri.keyword }"
                                        autocomplete="off">

                                    <button id="search_btn" class="btn">검색</button>
                                </form>
                            </div>

                            <!-- 테이블 -->
                            <div class="table-wrap">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 135px;">이미지</th>
                                            <th>도서 정보</th>
                                            <th style="width: 50px;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="list" items="${list}">
                                            <tr>
                                                <td class="">
                                                    <img src="${list.book_cover }" style="width: 135px;">
                                                </td>
                                                <td style="text-align: left">
                                                    <p><b>도서명</b> : ${list.book_title}</p>
                                                    <p><b>저자명</b> : ${list.book_author }</p>
                                                    <p><b>출판사</b> : ${list.book_publisher }</p>
                                                    <p><b>출간일</b> : ${list.book_pubDate }</p>
                                                </td>
                                                <td>
                                                    <form id="regist" action="/search/regist" method="post">
                                                        <input type="hidden" name="book_title"
                                                            value="${list.book_title}">
                                                        <input type="hidden" name="book_author"
                                                            value="${list.book_author}">
                                                        <input type="hidden" name="book_isbn" value="${list.book_isbn}">
                                                        <input type="hidden" name="book_cover"
                                                            value="${list.book_cover}">
                                                        <input type="hidden" name="book_pubDate"
                                                            value="${list.book_pubDate}">
                                                        <input type="hidden" name="book_publisher"
                                                            value="${list.book_publisher}">
                                                        <button class="btn add_btn">추가</button>
                                                    </form>

                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <div class="pageInfo" style="text-align: center">

                            <c:if test="${pageMaker.prev }">
                                <a class="not" href="${pageMaker.startPage - 1}">이전</a>
                            </c:if>

                            <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">

                                <c:if test="${num != 0}">
                                    <a class="${pageMaker.cri.page == num ? " current":"not" }" href="${num }">${num
                                        }</a>
                                </c:if>

                            </c:forEach>

                            <c:if test="${pageMaker.next }">
                                <a class="not" href="${pageMaker.endPage + 1}">다음</a>
                            </c:if>
                        </div>
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

    <script>
        $(function () {
            let moveForm = $(".moveForm");

            $(".pageInfo a").on("click", function (e) {
                e.preventDefault();
                moveForm.find("input[name = 'page']").val($(this).attr("href"));
                moveForm.submit();

            });
            $(".add_btn").on("click", function () {
                if (confirm("추천 도서를 등록하시겠습니까?")) {
                    alert("추천도서가 등록되었습니다.")
                    $(this).attr("onsubmit", "return true");
                    $(this).submit();
                } else {
                    alert("등록이 취소되었습니다.")
                }

            });

        });

    </script>

</body>
</html>