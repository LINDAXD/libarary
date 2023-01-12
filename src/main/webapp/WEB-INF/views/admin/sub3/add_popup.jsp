<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라온도서관 > 관리자 > 관리자권한</title>
<link rel="stylesheet" href="/resources/css/admin/popup.css">
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
                                <form action="/master/admin-search">
                                    <select name="type">
                                        <option value="user_id" selected="selected">아이디</option>
                                    </select>
                                    <input type="text" name="user_id" autocomplete="off" value="${result.user_id }">
                                    <button id="search_btn" class="btn">검색</button>

                                </form>

                            </div>

                            <!-- 테이블 -->
                            <div class="table-wrap">
                                <table>
                                    <thead>
                                        <tr>
                                            <th style="width: 100px;">아이디</th>
                                            <th style="width: 100px;">이름</th>
                                            <th style="width: 100px;">전화번호</th>
                                            <th style="width: 235px;">이메일</th>
                                            <th style="width: 90px;">회원가입일</th>
                                            <th style="width: 60px;">권한</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:if test="${not empty result }">
                                    	<tr>
											<td class="">${result.user_id }</td>
											<td class="left" style="padding-left: 15px;">${result.user_name }</td>
											<td>${result.user_tel}</td>
											<td>${result.user_email}</td>
											<td>${result.user_reg_date }</td>
											
											<td>
												<form action="/master/grant" method="post" onsubmit="return false;">
													<input type="hidden" id="user_id" name="user_id" value="${result.user_id }">
													<button class="add_btn btn">추가</button>
												</form>
											
											</td>
										</tr>
										</c:if>
		
                                    </tbody>
                                </table>

                                <br>


                            </div>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>


    <script>
		$(function(){
			$(".add_btn").on("click", function(){
				let id = $('#user_id').val(); 
				
				let data = {
           				user_id: id
           		};
				
				$.ajax({
					type: "post",
           			url: "/master/adminChk",
           			data: data,
					success: function(result) {
           				
           				if (result == "success") {
           					alert("관리자 권한이 부여되었습니다.")
							$("form").attr("onsubmit", "return true");
           					$("form").submit();
           					
           				} else {
           					alert("이미 관리자 권한을 가지고 있습니다.");
           				}
           			}
				});
				
			});
			
		});

    </script>


</body>
</html>