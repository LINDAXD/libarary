package com.library.model.mylib;

import java.sql.Timestamp;

import lombok.Data;

@Data // Data = getter + setter + toString
public class ReadingRoomDTO {

	// 좌석 번호
	private int seat_no;
	
	// 예약자 id
	private String user_id;
	
	// 입실 시간
	private Timestamp checkin_time;
	
	// 퇴실 시간
	private Timestamp checkout_time;
	
	// 잔여 시간
	private long diff_time;
}
