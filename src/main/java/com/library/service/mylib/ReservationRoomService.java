package com.library.service.mylib;

public interface ReservationRoomService {
	
	/* 잔여 좌석 | 사용 좌석 */

	/* ==== 열람실 1 ==== */
	/* 잔여 좌석 */
	public int rd1Room_usingSeat();
	/* 사용 좌석 */
	public int rd1Room_usedSeat();
	
	/* ==== 열람실 2 ==== */
	/* 잔여 좌석 */
	public int rd2Room_usingSeat();
	/* 사용 좌석 */
	public int rd2Room_usedSeat();
	
	/* ==== 노트북실 ==== */
	/* 잔여 좌석 */
	public int nbRoom_usingSeat();
	/* 사용 좌석 */
	public int nbRoom_usedSeat();

}
