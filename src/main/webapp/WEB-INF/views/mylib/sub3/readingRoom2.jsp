<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라온도서관 > 나의도서관 > 좌석예약/조회 > 제2열람실</title>
<link rel="stylesheet" href="/resources/css/mylib/sub3/readingRoom2_sb_page.css">
<link rel="stylesheet" href="/resources/css/mylib/sub3/readingRoom2.css">
<link rel="stylesheet" href="/resources/css/header.css">
<link rel="stylesheet" href="/resources/css/footer.css">
    <style>
        .seat-list-row {
            width: 100%;
            max-width: 100%;
        }

        .line1 {
            margin-bottom: 0px;
        }
    </style>

</head>
<body>
	<input type="hidden" class="reserve_no" value="${room2_info.seat_no}">

	<div class="header">
    <jsp:include page="../../header.jsp"></jsp:include>
    </div>
    
    <div class="container">
        <div class="sub_title">
            <div class="doc-info">
                <!-- doc title -->
                <div class="doc-title">
                    <h3>좌석예약/조회</h3>
                    <ul>
                        <!-- 홈 btn img -->
                        <li style="background-image: none;">
                            <a href="/">
                                <img src="/resources/imges/common/navi_home_icon.gif">
                            </a>
                        </li>
                        <li>
                            <a href="/mylib/loan-history">나의도서관</a>
                        </li>
                        <li>
                            <a href="/mylib/reservationRoomPage">좌석예약/조회</a>
                        </li>
                        <li>
                            <a href="/mylib/readingRoom2">제2열람실</a>
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

                        <div class="room-list">
                            <div class="list-btn">
                                <button onclick="rdRoom();">제 1열람실</button>
                                <button class="active" onclick="rdRoom2();">제 2열람실</button>
                                <button onclick="nbRoom();">노트북실</button>
                            </div>
                        </div>



                        <div class=" seat-chart">
                            <div class="seat-wrapper">
                                <div class="line1">
                                    <div class="seat-list-row">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="0" end="9">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>

                                <div class="line2">
                                    <div class="seat-list" style="height: 120px; overflow: hidden;">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="10" end="13">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>
                                    </div>

                                    <div class="seat-list">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="14" end="19">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>
                                    </div>

                                    <div class="seat-list">
                                    </div>

                                    <div class="seat-list">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="20" end="25">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>

                                    </div>

                                    <div class="seat-list" style="margin-right: 0;">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="26" end="31">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>


                                <div class="line3">
                                    <div class="seat-list-row">
                                        <c:forEach var="readingRoomlist" items="${readingRoomlist}" begin="32" end="41">

                                            <!-- 좌석 예약된 상태 status(0) occupied -->
                                            <c:if test="${readingRoomlist.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <!-- 좌석 예약가능한 상태 status(1) vacant -->
                                            <c:if test="${readingRoomlist.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하면 class를 mine으로 설정 -->
                                            <c:if test="${readingRoomlist.user_id == login_id}">
                                                <button class="mine"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>

                                            <!-- 예약된 좌석의 user_id가 sessionId와 일치하지 않을 경우 -->
                                            <c:if test="${readingRoomlist.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${readingRoomlist.seat_no}">${readingRoomlist.seat_no}</button>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <!-- 열람실 테이블 -->
                        <div class="wrapper-table">
                            <!-- 예약 x 테이블 -->
                            <c:if test="${room2_info == null}">
                                <input type="hidden" name="user_id" value="user_id">
                                <input type="hidden" name="seat_no" value="seat_no" class="input_selected_seat_no">

                                <table class="seat-info">
                                    <tbody>
                                        <tr>
                                            <th>열람실</th>
                                            <td>제 2열람실</td>
                                            <th class="left">좌석 번호</th>
                                            <td class="selected_seat_no"></td>
                                        </tr>
                                    </tbody>

                                </table>
								<form action="/mylib/bookingSeat" method="post" onsubmit="return false;">
									<input id="seat_no" type="hidden" name="seat_no">
									<button class="reserve_btn booking_btn">예약하기</button>
								</form>
                            </c:if>

                            <!-- 예약 o 테이블 -->
                            <c:if test="${room2_info != null}">

                                <fmt:formatDate var="checkin_time" value="${room2_info.checkin_time}"
                                    pattern="HH:mm:ss" />
                                <fmt:formatDate var="checkout_time" value="${room2_info.checkout_time}"
                                    pattern="HH:mm:ss" />

                                <fmt:parseNumber var="diff_hour" value="${room2_info.diff_time/(1000*60*60)}"
                                    integerOnly="true" />
                                <fmt:parseNumber var="diff_min" value="${room2_info.diff_time/(1000*60) - diff_hour*60}"
                                    integerOnly="true" />
                                <fmt:parseNumber var="diff_sec"
                                    value="${room2_info.diff_time/1000 - diff_hour*60*60 - diff_min*60}"
                                    integerOnly="true" />

                                <c:if test="${diff_min < 10}">
                                    <c:set var="diff_min" value="0${diff_min}"></c:set>
                                </c:if>

                                <c:if test="${diff_sec < 10}">
                                    <c:set var="diff_sec" value="0${diff_sec}"></c:set>
                                </c:if>

                                <c:set var="diff_time" value="${diff_hour}:${diff_min}:${diff_sec}"></c:set>

								<input type="hidden" id="diff_hour" value="${diff_hour }">
								<input type="hidden" id="diff_min" value="${diff_min }">
								<input type="hidden" id="diff_sec" value="${diff_sec }">
                                <!-- 제2열람실 좌석 예약정보 -->
                                <table class="reserve-info">
                                    <tbody>
                                        <tr>
                                            <th>열람실</th>
                                            <td colspan="2">제 2열람실</td>
                                            <th class="left">좌석 번호</th>

                                            <td colspan="2">
                                                ${room2_info.seat_no}
                                                <input type="hidden" class="my_seat_no" value="${room2_info.seat_no}">
                                            </td>
                                        </tr>

                                        <tr>
                                            <th>예약 시간</th>
                                            <td class="reg_time">${checkin_time}</td>
                                            <th class="left">반납 시간</th>
                                            <td class="return_time">${checkout_time}</td>
                                            <th class="left">잔여 시간</th>
                                            <td id="time" style="color: blue; font-weight: bold;">${diff_time}</td>
													
                                        </tr>
                                    </tbody>

                                </table>

                                <div class="reserve-info-btn">
                                	<div style="float: left;">
                                		<form id="return_form" action="/mylib/returnSeat" method="post" onsubmit="return false;">
                                        	<button class="chk_out_btn return_btn">퇴실</button>
                                    	</form>
                                	</div>

									<div style="float: right; margin-left: 10px;">
										<form id="extend_form" action="/mylib/extendSeat" method="post" onsubmit="return false;">
											<button class="renew_btn extend_btn">연장</button>
										</form>
									</div>
									
									<form id="move" action="/mylib/moveSeat" method="post" onsubmit="return false;">
										<input id="new_no" type="hidden" name="seat_no">
									</form>
                                    
                                </div>

                            </c:if>

                        </div>


                    </div>

                </div>

            </div>

        </div>

    </div>
    
    <!-- footer -->
    <jsp:include page="../../footer.jsp"></jsp:include>

    <script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script>
        $(function () {

			
            $(".sub3").addClass("active");
            $(".submenu11").addClass("active");
			
         // 예약된 좌석이 있으면 남은 시간 timer start
           	if($(".reserve_no").val() != ""){
               var hours =  $("#diff_hour").val();
               var minutes = $("#diff_min").val();
               var seconds = $("#diff_sec").val();
               var totalSeconds = (60 * 60 * hours) + (60 * minutes) + parseInt(seconds, 10) - 1;
               var display = document.querySelector('#time');
               startTimer(totalSeconds, display);
            }
            /*좌석번호 클릭하기 전까지 나타지않도록 */
            $(".booking_btn").hide();

            /* class가 occupied인 button은 disabled 속성 사용해 버튼 비활성화 */
            $(".occupied").prop("disabled", true);

		
        	var today = new Date();
        	var hours = parseInt(today.getHours());
 
            $(".vacant").on("click", function (e) {

                e.preventDefault();
                
                if(hours >= 18 || hours < 9){
                	alert("현재는 사용 불가한 시간입니다.");
                	return;
                	
                }

                var seat_no = $(this).attr("id");/* a태그 vacant클래스의 아이디값, this는 $(".vacant")의미함 */

                if ($(".reserve_no").val() != '') {

                    if (confirm("제2열람실 " + seat_no + "번 좌석으로 이동하시겠습니까?")) {
						
                        var data = {
           		            seat_no: seat_no            
           		        };
                       	
                       	$.ajax({
       		                type: "post",
       		                url: "/mylib/seat_check",
       		                data: data,
       		                success: function(result) {
       		                   
       		                   if (result == "success") {
       		                      alert("좌석 이동이 완료되었습니다.");
       		                      $("#move #new_no").val(seat_no);
       		                      $("#move").attr("onsubmit", "return true;");
       		                      $("#move").submit();
       		                      
       		                   }else {
       		                      alert("이미 예약된 좌석입니다.");
       		                      location.reload();
       		                   }
       		                }
       		             });
                    }


                } else {

                    $(".vacant").css("background-color", "#284d70");
                    $(".vacant").css("border", "#4d445b");

                    $(this).css("background-color", "#ff6600");
                    $(this).css("border", "#ff6600");


                    $(".selected_seat_no").html(seat_no); /* 좌석 번호의 값이 내가 선택한(vacant) 좌석의 아이디값이 .selected_seat_no클래스 입력되게 된다.*/
                    $(".input_selected_seat_no").val(seat_no);

                    $(".booking_btn").show();

                }

            })

            /* 좌석예약  */
            $(".booking_btn").on("click", function (e) {

                e.preventDefault();

                /* <input type="hidden" name="seat_no" value="seat_no" class="input_selected_seat_no"> 값을 가져온다 */
                var seat_no = $(".input_selected_seat_no").val();

                if (confirm("제2열람실 " + seat_no + "번 좌석을 예약하시겠습니까?")) {

                	var data = {
       		            seat_no: seat_no            
       		         };
                	
                	$.ajax({
		                type: "post",
		                url: "/mylib/seat_check",
		                data: data,
		                success: function(result) {
		                   
		                   if (result == "success") {
		                      alert("예약이 완료되었습니다.");
		                      $("#seat_no").val(seat_no);
		                      $("form").attr("onsubmit", "return true;");
		                      $("form").submit();
		                      
		                   }else {
		                      alert("이미 예약된 좌석입니다.");
		                      location.reload();
		                   }
		                }
		             });

                }

            });

            $(".mine").on("click", function () {
                alert("이미 예약 중인 좌석입니다.");
            })

            $(".return_btn").on("click", function () {

                if (confirm("좌석을 반납하시겠습니까?")) {
                    alert("좌석 반납을 완료하였습니다.");
                    $("form").attr("onsubmit", "return true;");
                    $("form").submit();
                }
            })
			
            /* 좌석 연장 */
            $(".extend_btn").on("click", function (e) {
                e.preventDefault();
                var diff_hour = $("#diff_hour").val();
                var diff_min = $("#diff_min").val(); /* 30분 이하로만 자리 연장 가능 */
                var result = diff_hour < 1 && diff_min < 30;
                var checkout_time = "<c:out value='${room2_info.checkout_time}'/>";

                if (result == false || checkout_time.includes("18:00:00")) {
                	// checkout_time에 '18:00:00'이라는 문자열이 포함되어 있으면, 좌석 연장 여부 묻지 않고 연장 불가 메시지 바로 뜨게 함.
                    alert("연장 가능한 시간이 아닙니다.");
          
                	}else{
                		if (confirm("좌석을 연장하시겠습니까?")) {
                            alert("좌석 시간이 연장되었습니다.");
                            $("#extend_form").attr("onsubmit", "return true;");
                            $("#extend_form").submit();

                        }
                		
                	}
                    
                

            })

        });

        /* 열람실 이동 함수 추가 */

        function rdRoom() {
            location.href = "/mylib/readingRoom";
        }

        function rdRoom2() {
            location.href = "/mylib/readingRoom2";
        }

        function nbRoom() {
            location.href = "/mylib/notebookRoom";
        }
        
        /* 열람실 잔여시간 실시간 타이머 */
        function startTimer(totalSeconds, display) {
            
            var timer = totalSeconds;
            var hours;
            var minutes;
            var seconds;
            var interval = setInterval(function () {
             if(timer <= 0) {
                clearInterval(interval);
                alert("퇴실되었습니다.");
                location.reload();
            }
             
              hours = parseInt(timer / 60 / 60, 10);
              minutes = parseInt(timer / 60 - (hours * 60), 10);
              seconds = parseInt(timer % 60, 10);

              minutes = minutes < 10 ? "0" + minutes : minutes;
              seconds = seconds < 10 ? "0" + seconds : seconds;
              
         if(hours < 1 && minutes < 30) {
            display.style.color = "red";   
         }
         
            display.textContent = hours + ":" + minutes + ":" + seconds;
         
            timer--;
            
            }, 1000);
        }

    </script>

</body>
</html>