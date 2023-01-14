package com.library.service.mylib;

import java.util.List;

import com.library.model.mylib.ReadingRoomDTO;

public interface ReadingRoomService {

	// 모든 열람실 출력
	public List<ReadingRoomDTO> getReadingRoomAll();
	
	// 1열람실 출력
	public List<ReadingRoomDTO> getReadingRoom1SeatsList();

	// 2열람실 출력
	public List<ReadingRoomDTO> getReadingRoom2SeatsList();

	// 노트북실 출력
	public List<ReadingRoomDTO> getNbRoomSeatsList();

	// 열람실 예약
	public void bookingSeat(ReadingRoomDTO dto);

	// 예약 좌석 정보
	public ReadingRoomDTO mySeatInfo(String user_id);

	// 열람실 퇴실
	public void returnSeat(String user_id);

	// 열람실 좌석 시간 연장
	public void extendSeat(String user_id);

	// 좌석 상태 체크
	public int seat_check(String seat_no);

	// 열람실 좌석 insert
	public void insert(int seat_no);

}
