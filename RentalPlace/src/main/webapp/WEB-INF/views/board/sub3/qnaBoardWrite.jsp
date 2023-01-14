<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<title>라온도서관 > 열린공간 > 묻고답하기</title>
	<link rel="stylesheet" href="/resources/css/board/sub3/qna_write_page.css">
	<link rel="stylesheet" href="/resources/css/header.css">
	<link rel="stylesheet" href="/resources/css/footer.css">
</head>
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
                    <h3>묻고답하기</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/board/noticeList">열린공간</a>
                        </li>
                        <li>
                            <a href="/board/qnaBoardList">묻고답하기</a>
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

                            <!-- 테이블 -->
                            <div class="table-wrap">
                                <form action="/board/qnaBoardInsert" method="post">
                                    <input type="hidden" name="writer_name" 
                                    value="<sec:authentication property="principal.dto.user_name"/>">
                                    
                                    <table class="bbs-edit">
                                        <tbody>
                                            <tr>
                                                <th class="first">제목</th>
                                                <td colspan="3">
                                                    <input id="title" type="text" style="width: 80%; height: 27px;"
                                                        placeholder="제목을 작성해 주세요" name="enquiry_title" 
                                                        id="title" autocomplete="off" required="required" maxlength="50">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th class="first">작성자</th>
                                                <td><sec:authentication property="principal.dto.user_name"/></td>
                                                <th class="first">작성일</th>
                                                <td>
                                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" style="padding: 8px 0px;">
                                                    <textarea id="popContent" name="enquiry_content"></textarea>
                                                </td>
                                            </tr>
                                        </tbody>

                                    </table>

                                    <!-- 글쓰기 btn -->
                                    <div class="write">
                                        <button type="submit" class="write_btn">글쓰기</button>
                                        <button class="list_btn" type="button" onclick="location.href='/board/qnaBoardList'">목록으로</button>
                                    </div>

                                </form>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>

    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/ckeditor/ckeditor.js"></script>

<script>

$(function() {
	
	CKEDITOR.replace('popContent' , {
		 filebrowserImageUploadUrl: "/upload?boardName=qna",
		 height: 500                                                  
    });

	
	$('.sub3').addClass("active");
	
	$(".write_btn").on("click", function() {
		
		 var title = $("#title").val();

         if (title == "") {

             $("#title").focus();
             
             return false;
         }
	
		if(confirm('등록하시겠습니까?')) {
			alert("게시글이 등록되었습니다.");
        }else {
        	return false;
        }
	
	});
	
});
</script>



</body>
</html>