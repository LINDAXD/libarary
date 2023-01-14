package com.library.service.mylib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.mylib.ReadingRoomMapper;

@Service
public class ReservationRoomServiceImpl implements ReservationRoomService {

	@Autowired
	private ReadingRoomMapper roomMapper;
	
	/* 잔여 좌석 | 사용 좌석 */
	
	/* ==== 열람실 1 ==== */
	/* 잔여 좌석 */
	@Override
	public int rd1Room_usingSeat() {
		return roomMapper.rd1Room_usingSeat();
	}
	/* 사용 좌석 */
	@Override
	public int rd1Room_usedSeat() {
		return roomMapper.rd1Room_usedSeat();
	}
	
	
	/* ==== 열람실 2 ==== */
	/* 잔여 좌석 */
	@Override
	public int rd2Room_usingSeat() {
		return roomMapper.rd2Room_usingSeat();
	}
	/* 사용 좌석 */
	@Override
	public int rd2Room_usedSeat() {
		return roomMapper.rd2Room_usedSeat();
	}
	
	
	/* ==== 노트북실 ==== */
	/* 잔여 좌석 */
	@Override
	public int nbRoom_usingSeat() {
		return roomMapper.nbRoom_usingSeat();
	}
	/* 사용 좌석 */
	@Override
	public int nbRoom_usedSeat() {
		return roomMapper.nbRoom_usedSeat();
	}
	

}
