<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<html>
<head>
	<title>라온도서관 > 나의도서관 > 좌석예약/조회 > 제1열람실</title>
	<link rel="stylesheet" href="/resources/css/mylib/sub3/readingRoom1_sb_page.css">
	<link rel="stylesheet" href="/resources/css/mylib/sub3/readingRoom1.css">
	<link rel="stylesheet" href="/resources/css/header.css">
	<link rel="stylesheet" href="/resources/css/footer.css">

</head>
<body>

	<input type="hidden" class="reserve_no" value="${mySeatInfo.seat_no}">
	
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
                            <a href="/mylib/readingRoom">제1열람실</a>
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
                                <button class="active" onclick="rdRoom();">제 1열람실</button>
                                <button onclick="rdRoom2();">제 2열람실</button>
                                <button onclick="nbRoom();">노트북실</button>
                            </div>
                        </div>


                        <div class="seat-chart">
                            <div class="seat-wrapper">
                                <div class="line1">
                                    <div class="seat-list">
                                        <c:forEach var="seatsList" items="${seatsList}" begin="0" end="9">

                                            <c:if test="${seatsList.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == login_id}">
                                                <button class="mine"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                            <c:if test="${seatsList.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                        </c:forEach>
                                    </div>

                                    <div class="seat-list">
                                        <c:forEach var="seatsList" items="${seatsList}" begin="10" end="19">

                                            <c:if test="${seatsList.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == login_id}">
                                                <button class="mine"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                            <c:if test="${seatsList.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                        </c:forEach>
                                    </div>

                                    <div class="seat-list" style="height: 180px; overflow: hidden;">
                                        <c:forEach var="seatsList" items="${seatsList}" begin="20" end="27">

                                            <c:if test="${seatsList.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == login_id}">
                                                <button class="mine"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                            <c:if test="${seatsList.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                        </c:forEach>
                                    </div>

                                    <div class="seat-list">
                                        <c:forEach var="seatsList" items="${seatsList}" begin="28" end="37">

                                            <c:if test="${seatsList.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == login_id}">
                                                <button class="mine"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                            <c:if test="${seatsList.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                        </c:forEach>
                                    </div>
                                </div>


                                <div class="line2">
                                    <div class="seat-list-row">
                                        <c:forEach var="seatsList" items="${seatsList}" begin="38" end="53">

                                            <c:if test="${seatsList.user_id != null}">
                                                <c:set var="status" value="occupied" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == null}">
                                                <c:set var="status" value="vacant" />
                                            </c:if>

                                            <c:if test="${seatsList.user_id == login_id}">
                                                <button class="mine"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                            <c:if test="${seatsList.user_id != login_id}">
                                                <button class="${status}"
                                                    id="${seatsList.seat_no}">${seatsList.seat_no}</button>
                                            </c:if>

                                        </c:forEach>
                                    </div>
                                </div>


                            </div>
                        </div>




                        <!-- 열람실 테이블 -->
                        <div class="wrapper-table">
                            <!-- 예약 x 테이블 -->
                            <c:if test="${mySeatInfo == null}">
                                <input type="hidden" name="seat_no" value="seat_no" class="input_selected_seat_no">

                                <table class="seat-info">
                                    <tbody>
                                        <tr>
                                            <th>열람실</th>
                                            <td>제 1열람실</td>
                                            <th class="left">좌석 번호</th>
                                            <td class="selected_seat_no"></td>
                                        </tr>
                                    </tbody>

                                </table>

                                <!-- </form> -->
                                <form id="booking_form" action="/mylib/bookingSeat" method="post" onsubmit="return false;">
                                    <input id="seat_no" type="hidden" name="seat_no">
                                    <button class="reserve_btn booking_btn">예약하기</button>
                                </form>
                            </c:if>


                            <!-- 예약 o 테이블 -->
                            <c:if test="${mySeatInfo != null}">

                                <!-- 열람실 명 -->
                                <c:if test="${mySeatInfo.seat_no < 54}">
                                    <c:set var="room_name" value="제 1열람실" />
                                </c:if>
                                <c:if test="${mySeatInfo.seat_no >= 54 && mySeatInfo.seat_no < 98}">
                                    <c:set var="room_name" value="제 2열람실" />
                                </c:if>
                                <c:if test="${mySeatInfo.seat_no >= 98 }">
                                    <c:set var="room_name" value="노트북실" />
                                </c:if>

                                <!-- 잔여 시간 -->
                                <fmt:formatDate var="checkin_time" value="${mySeatInfo.checkin_time}"
                                    pattern="HH:mm:ss" />
                                <fmt:formatDate var="checkout_time" value="${mySeatInfo.checkout_time}"
                                    pattern="HH:mm:ss" />

                                <fmt:parseNumber var="diff_hour" value="${mySeatInfo.diff_time/(1000*60*60)}"
                                    integerOnly="true" />
                                <fmt:parseNumber var="diff_min" value="${mySeatInfo.diff_time/(1000*60) - diff_hour*60}"
                                    integerOnly="true" />
                                <fmt:parseNumber var="diff_sec"
                                    value="${mySeatInfo.diff_time/1000 - diff_hour*60*60 - diff_min*60}"
                                    integerOnly="true" />

                                <c:if test="${diff_min < 10}">
                                    <c:set var="diff_min" value="0${diff_min}"></c:set>
                                </c:if>

                                <c:if test="${diff_sec < 10}">
                                    <c:set var="diff_sec" value="0${diff_sec}"></c:set>
                                </c:if>

                                <c:set var="diff_time" value="${diff_hour}:${diff_min}:${diff_sec}"></c:set>

                                <input type="hidden" id="diff_hour" value="${diff_hour}">
                                <input type="hidden" id="diff_min" value="${diff_min}">
                                <input type="hidden" id="diff_sec" value="${diff_sec}">
                                
                                

                                <table class="reserve-info">
                                    <tbody>
                                        <tr>
                                            <th>열람실</th>
                                            <td colspan="2">${room_name}</td>
                                            <th class="left">좌석 번호</th>
                                            <td colspan="2">
                                                ${mySeatInfo.seat_no}
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>예약 시간</th>
                                            <td class="checkin_time">${checkin_time}</td>
                                            <th class="left">반납 시간</th>
                                            <td class="checkout_time">${checkout_time}</td>
                                            <th class="left">잔여 시간</th>
                                            <td id="time" style="color: blue; font-weight: bold;">${diff_time}</td>
                                        </tr>
                                    </tbody>

                                </table>
                                <div class="reserve-info-btn">
                                    <div style="float: left;">
                                        <form id="return_form" action="/mylib/returnSeat" method="post"
                                            onsubmit="return false;">
                                            <button class="chk_out_btn return_btn">퇴실</button>
                                        </form>
                                    </div>

                                    <div style="float: right; margin-left: 10px;">
                                        <form id="extend_form" action="/mylib/extendSeat" method="post"
                                            onsubmit="return false;">
                                            <button class="renew_btn extend_btn">연장</button>
                                        </form>
                                    </div>

                                    <form id="move_form" action="/mylib/moveSeat" method="post" onsubmit="return false;">
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
        	
        	// 예약된 좌석이 있으면 남은 시간 timer start
        	if($(".reserve_no").val() != ""){
        		var hours =  $("#diff_hour").val();
                var minutes = $("#diff_min").val();
                var seconds = $("#diff_sec").val();
                var totalSeconds = (60 * 60 * hours) + (60 * minutes) + parseInt(seconds, 10) - 1;
                var display = document.querySelector('#time');
                startTimer(totalSeconds, display);
        	}

            // class가 occupied인 button은 disabled 속성 사용해 버튼 비활성화
            $(".occupied").prop("disabled", true);

            $(".sub3").addClass("active");
            $(".submenu10").addClass("active");

            $(".booking_btn").hide();



            $(".mine").on("click", function (e) {
            	  alert("이미 예약된 좌석입니다.");
            });
            
            
            var today = new Date();
            var hours = parseInt(today.getHours());

            $(".vacant").on("click", function (e) {
                e.preventDefault();
                
                if(hours >= 18 || hours < 9){
                	alert("현재는 사용 불가한 시간입니다.");
                	return;
                }
				
                var seat_no = $(this).attr("id");

                if($(".reserve_no").val() != ""){

                    if (confirm("제 1열람실 " + seat_no + "번 좌석으로 이동하시겠습니까?")) {

                        var data = {
                            seat_no: seat_no
                        };

                        $.ajax({
                            type: "post",
                            url: "/mylib/seat_check",
                            data: data,
                            success: function (result) {

                                if (result == "success") {
                                    alert("좌석 이동이 완료되었습니다.");
                                    $("#move_form #new_no").val(seat_no);
                                    $("#move_form").attr("onsubmit", "return true;");
                                    $("#move_form").submit();

                                } else {
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

                    $(".selected_seat_no").html(seat_no);
                    $(".input_selected_seat_no").val(seat_no);

                    $(".booking_btn").show();

                }

            })


            $(".booking_btn").on("click", function (e) {
                e.preventDefault();
                
                var seat_no = $(".input_selected_seat_no").val();

                if (confirm("제 1열람실 " + seat_no + "번 좌석을 예약하시겠습니까?")) {

                    var data = {
                        seat_no: seat_no
                    };

                    $.ajax({
                        type: "post",
                        url: "/mylib/seat_check",
                        data: data,
                        success: function (result) {

                            if (result == "success") {
                            	alert("예약이 완료되었습니다.");
  		                      	$("#seat_no").val(seat_no);
  		                      	$("#booking_form").attr("onsubmit", "return true;");
  		                      	$("#booking_form").submit();

                            } else {
                                alert("이미 예약된 좌석입니다.");
                                location.reload();
                            }
                        }
                    });


                }


            })

            $(".return_btn").on("click", function () {

                if (confirm("좌석을 반납하시겠습니까?")) {
                	 alert("좌석 반납을 완료하였습니다.");
                     $("#return_form").attr("onsubmit", "return ture;");
                     $("#return_form").submit();
                }
            })

            $(".extend_btn").on("click", function (e) {
                e.preventDefault();

                var diff_hour = $("#diff_hour").val();
                var diff_min = $("#diff_min").val();
                var result = diff_hour < 1 && diff_min < 30;
                var checkout_time = "<c:out value='${mySeatInfo.checkout_time}'/>";
                
                if (result == false || checkout_time.includes("18:00:00")) {
                    alert("연장 가능한 시간이 아닙니다.");

                } else {
                    if (confirm("좌석을 연장하시겠습니까?")) {
                    	 alert("좌석 시간이 연장되었습니다.");
                         $("#extend_form").attr("onsubmit", "return true;");
                         $("#extend_form").submit();
                    }
                }
 
            })
            	
            	
        });

        function rdRoom() {
            location.href = "/mylib/readingRoom";
        }

        function rdRoom2() {
            location.href = "/mylib/readingRoom2";
        }

        function nbRoom() {
            location.href = "/mylib/notebookRoom";
        }
        
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
