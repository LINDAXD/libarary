<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
<html>
<head>
<title>라온도서관 > 자료검색 > 도서검색</title>




</head>
<link rel="stylesheet" href="/resources/css/search/book.css">
<link rel="stylesheet" href="/resources/css/search/book_detail.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
<link rel="stylesheet" href="/resources/css/buy/buy.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"
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
                  <li class="first" style="background-image: none;"><a href="/">
                        <img src="/resources/imges/common/navi_home_icon.gif">
                  </a></li>
                  <li><a href="/search/book">자료검색</a></li>
                  <li><a href="/search/book">도서구매</a></li>
               </ul>

            </div>
         </div>
      </div>
      <div class="section">
         <div class="doc">

            <!-- 왼쪽 사이드바 -->
            <jsp:include page="../search/lnb.jsp"></jsp:include>

            <!-- 본문 -->
            <div class="content">

               <div class="doc">
                  <div class="wrapper-bbs" style="">

                     <!-- 테이블 -->
                     <div class="table-wrap">
                        <h2>상품 확인</h2>
                        <table>
                           <thead>
                              <tr>
                                 <th style="width: 200px;">이미지</th>
                                 <th style="">도서 정보</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td class=""><img src="${book.book_cover}"
                                    style="width: 200px;"></td>
                                 <td class="" style="text-align: left;">
                                    <h2>${book.book_title }</h2>
                                    <p>
                                       <b>저자명</b> : ${book.book_author }
                                    </p>
                                    <p>
                                       <b>출판사</b>: ${book.book_publisher }
                                    </p>
                                    <p>
                                       <b>출간일</b> : ${book.book_pubDate }
                                    </p>
                                    <p>
                                       <b>가격</b> :
                                       <fmt:formatNumber value="${book.priceStandard }"
                                          type="currency" />
                                    </p>
                                 </td>
                              </tr>
                           </tbody>
                        </table>
                     </div>

                     <!-- 구매 -->
                  </div>
                  <div id="join_container">
                     <h3>정보 입력</h3>
                     <p>
                        <strong class="point">*</strong> 는 필수입력 항목을 나타냅니다.
                     </p>
                     <form action="/buy/book_buy_api?detail=not" method="post" id="buy">
                        <fieldset>
                           <legend>정보 입력</legend>
                           
                           <!-- <p class="shipping">
                              <label class="tit"><strong class="point">* </strong>배송방법</label>
                              <input type="radio" name="shipping" id="normal" checked>
                              <label for="normal" class="tit">일반 배송</label> <span><input
                                 type="radio" name="shipping" id="overseas"></span> <label
                                 for="overseas" class="tit">해외 배송</label> <input type="radio"
                                 name="shipping" id="convenience"> <label
                                 for="convenience" class="tit">편의점 배송</label>
                           </p> -->

                           <p>
                              <label class="tit"><strong class="point">*</strong>수령인</label>
                              <input type="text" id="user_id">

                           </p>

                           <p>
                              <label class="tit"><strong class="point">*</strong>우편번호</label>
                              <input type="text" class="form-control m-input"
                                 name="postcode" id="postcode" readonly />
                              <button type="button" id="post"
                                 class="btn btn-info m-btn--air" onclick="execDaumPostcode()">우편번호
                                 찾기</button>
                           </p>
                           <p>
                              <label class="tit"><strong class="point">*</strong>도로명
                                 주소</label> <input type="text"
                                 class="form-control m-input m--margin-top-10" name="address"
                                 id="address" readonly />

                           </p>

                           <p>
                              <label class="tit"><strong class="point">*</strong>상세
                                 주소</label> <input type="text"
                                 class="form-control m-input m--margin-top-10"
                                 name="detailAddress" id="address_d"/>
                           </p>

                           <p>
                              <label class="tit"><strong class="point">*</strong>휴대폰</label>
                              <input type="tel" id="tel1" title="지역번호 입력" maxlength='3'> - 
                              <input type="tel" id="tel2" title="전화번호 앞자리" maxlength='4'> - 
                              <input type="tel" id="tel3" title="전화번호 뒷자리" maxlength='4'>
                           </p>

                           <p>
                              <label class="tit">일반전화</label>
                              <input type="tel" id="tel1" title="지역번호 입력" maxlength='3'> - 
                              <input type="tel" id="tel2" title="전화번호 앞자리" maxlength='3'> - 
                              <input type="tel" id="tel3"   title="전화번호 뒷자리" maxlength='3'>
                           </p>
                        </fieldset>

                        <fieldset>
                           <legend>결제 방법</legend>
                           <p>
                              <input type="radio" name="kakaopay" id="kakaopay" checked>
                              <label for="kakaopay">카카오페이</label>

                           </p>
                           <hr />
                           <p>
                              <strong class="point">*</strong>선물 포장 <input type="radio" name="present" id="no"> <label
                                 for="no">안함</label> <input type="radio" name="present"
                                 id="yes"> <label for="yes">요청(1,000원)</label>
                           </p>
                           <!-- <p>
                              영수증 <input type="radio" name="receipt" id="chk1"> <label
                                 for="chk1">가격표시</label> <input type="radio" name="receipt"
                                 id="chk2"> <label for="chk2">가격 표시 안함</label>
                           </p> -->
                           <p>택배사 직원에게</p>
                           <input type="text" class="msg" maxlength='30'
                              placeholder="30자 이내로 작성해주세요">
                           <p>받는분께 메시지</p>
                           <input type="text" class="msg" maxlength='30'
                              placeholder="30자 이내로 작성해주세요">
                           <p>
                              최종 결제금액
								<c:forEach var="buy_history" items="${buy_history}">
									<p>수량 : ${buy_history.bookCount}</p>
									<p>가격 : <fmt:formatNumber value="${buy_history.book_price}" type="currency" />원 (배달료 2500원 추가)</p>
								</c:forEach>
                           </p>
                           <span id="agree">주문하실 상품, 가격, 배송정보, 할인정보 등을 확인하였으며, 구매에
                              동의하시겠습니까?</span>
                           <p id="checkbox">
                              <input type="checkbox" id="l_agree"><span>동의합니다.(전자상거래법 제 8조
                                 제 2항)</span>
                           </p>

                        </fieldset>
                        
                        

                        <sec:authorize access="isAuthenticated()">
                           <input type="hidden" class="user_email" name="user_email"
                              value=<sec:authentication property="principal.dto.user_email"/>>
                        </sec:authorize>

                        <sec:authorize access="isAnonymous()">
                           <input type="hidden" class="user_email" name="user_email">
                        </sec:authorize>
                        <input type="hidden" name="book_title"
                           value="${book.book_title}"> <input type="hidden"
                           name="book_author" value="${book.book_author}"> <input
                           type="hidden" class="book_isbn" name="book_isbn"
                           value="${book.book_isbn}"> <input type="hidden"
                           name="book_cover" value="${book.book_cover}"> <input
                           type="hidden" name="book_pubDate" value="${book.book_pubDate}">
                        <input type="hidden" name="book_publisher"
                           value="${book.book_publisher}"> <input type="hidden"
                           name="amount" value="${cri.amount}"> <input
                           type="hidden" name="page" value="${cri.page}"> <input
                           type="hidden" name="type" value="${cri.type}"> <input
                           type="hidden" name="keyword" value="${cri.keyword}"> 
                           
                           <input type="hidden" name="priceStandard" value="${book.priceStandard}">
                           
                           
                           <input
                           type="hidden" name="resultpriceStandard"
                           value="${book.resultpriceStandard}">
                        <button type="button"  id="apibtn">결제
                           하기</button>

                     </form>
                  </div>
                  <!--join_container 전체-->
                  <div>
                     <!-- 구매 -->

                  </div>
               </div>

            </div>

         </div>


      </div>



   </div>



   <!-- footer -->
   <jsp:include page="../footer.jsp"></jsp:include>
   <script>
   $(".packagingprice").hide();
   $(function(){
            let no = false;
            let yes = false;
            let l_agree = false;
            $("#no").change(function(){
               if($("#no").is(":checked")){
                  no = true;
                  $(".packagingprice").hide();
                  $(".resultprice").show();
               }
            });
            $("#yes").change(function(){
               if($("#yes").is(":checked")){
                  yes = true;
                  $(".packagingprice").show();
                  $(".resultprice").hide();
               }
            });
            $("#l_agree").change(function(){
               if($("#l_agree").is(":checked")){
                  l_agree = true;
               }
            });
            $("#apibtn").click(function() {
               
               let email = $('.user_email').val(); 
               let book_isbn = $('.book_isbn').val(); 
               
               if(email == "") {
                  alert("로그인 후 이용해주세요");
                  location.href="/member/login";
               } else if($("#user_id").val() == ""){
                  alert("필수입력 항목을 입력해주세요");
               }  else if($("#postcode").val() == ""){
                  alert("입력 항목을 입력해주세요");
               } else if($("#address").val() == ""){
                  alert("입력 항목을 입력해주세요");
               } else if($("#address_d").val() == ""){
                  alert("입력 항목을 입력해주세요");
               } else if($("#tel1").val() == ""){
                  alert("입력 항목을 입력해주세요");
               } else if($("#tel2").val() == ""){
                  alert("입력 항목을 입력해주세요");
               } else if($("#tel3").val() == ""){
                  alert("입력 항목을 입력해주세요");
               }   else if((no == true || yes == true) && l_agree == true){
                  var data = {
                             book_isbn: book_isbn
                       };
                  
                  if (confirm("결제하시겠습니까?")) {
                  
                       if(no == true){
                          $("#buy").attr("action", "/buy/buybook_not");
                          $("#buy").attr("onsubmit", "return true;");
                          $("#buy").submit();
                       } else if (yes == true){
                          $("#buy").attr("action", "/buy/buybook_not");
                          $("#buy").attr("onsubmit", "return true;");
                          $("#buy").submit();
                       }

                       $.ajax({
                        type: "get",
                        url:'/buy/book_buy_api',
                        dataType:'json',
                        success:function(data){
                                $("#buy").attr("onsubmit", "return true;");
                                $("#buy").submit();
                                
                           var box = data.next_redirect_pc_url;
                           var name = "credit"
                           var option = "width = 500, height = 650, top = 100, left = 200,location = no"; // 팝업창 크기
                           window.open(box,name,option);
                        },
                        error:function(error){
                           alert(error);
                        }
                     });
                      
                      
                  
                  } else {
                     alert("결제를 취소했습니다.");
                  }
                  
                  
               } else {
                  alert("필수입력 항목을 입력해주세요");
               }
                  
            });
   });
         
   </script>
   <script   src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
   <script>
      /** 우편번호 찾기 */
      function execDaumPostcode() {
          daum.postcode.load(function(){
              new daum.Postcode({
                  oncomplete: function(data) {
                    $("#postcode").val(data.zonecode);
                    $("#address").val(data.roadAddress);
                  }
              }).open();
          });
      }
   </script>
</body>
</html>