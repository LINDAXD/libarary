package com.library.service.mylib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.mylib.ReadingRoomMapper;
import com.library.model.mylib.ReadingRoomDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReadingRoomServiceImpl implements ReadingRoomService {

	@Autowired
	private ReadingRoomMapper mapper;

	@Override 
	public List<ReadingRoomDTO> getReadingRoomAll() {
		return mapper.getReadingRoomAll();
	}
	@Override
	public List<ReadingRoomDTO> getReadingRoom1SeatsList() {
		return mapper.getReadingRoom1SeatsList();
	}
	
	@Override
	public List<ReadingRoomDTO> getReadingRoom2SeatsList() {
		return mapper.getReadingRoom2SeatsList();
	}
	
	@Override
	public List<ReadingRoomDTO> getNbRoomSeatsList() {
		return mapper.getNbRoomSeatsList();
	}

	@Override
	public void bookingSeat(ReadingRoomDTO dto) {
		mapper.bookingSeat(dto);
	}

	@Override
	public ReadingRoomDTO mySeatInfo(String user_id) {
		return mapper.mySeatInfo(user_id);
	}

	@Override
	public void returnSeat(String user_id) {
		mapper.returnSeat(user_id);
	}

	@Override
	public void extendSeat(String user_id) {
		mapper.extendSeat(user_id);
	}

	@Override
	public int seat_check(String seat_no) {
		return mapper.seat_check(seat_no);
	}

	@Override
	public void insert(int seat_no) {
		mapper.insert(seat_no);
	}

}
