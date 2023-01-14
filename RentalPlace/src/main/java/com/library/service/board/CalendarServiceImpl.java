package com.library.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.board.CalendarMapper;
import com.library.model.board.CalendarDTO;
import com.library.model.search.DateDTO;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	public CalendarMapper calMapper;

	// 일정 모두 출력
	@Override
	public List<CalendarDTO> list_all() {
		return calMapper.list_all();
	}

	// 일정 조건 검색
	@Override
	public List<CalendarDTO> list_ym(DateDTO date) {
		return calMapper.list_ym(date);
	}

	// 일정 추가
	@Override
	public void insert_cal(CalendarDTO cal) {
		calMapper.insert_cal(cal);
	}

	// 일정 삭제
	@Override
	public void delete_cal(int cal_no) {
		calMapper.delete_cal(cal_no);
	}

	// 휴관일 검색
	@Override
	public List<CalendarDTO> search_closed(DateDTO date) {
		return calMapper.search_closed(date);
	}

}
