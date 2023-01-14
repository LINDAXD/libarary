package com.library.mapper.board;

import java.util.List;

import com.library.model.board.CalendarDTO;
import com.library.model.search.DateDTO;

public interface CalendarMapper {

	// 일정 모두 출력
	public List<CalendarDTO> list_all();

	// 일정 조건 검색
	public List<CalendarDTO> list_ym(DateDTO date);

	// 일정 추가
	public void insert_cal(CalendarDTO cal);

	// 일정 삭제
	public void delete_cal(int cal_no);

	// 휴관일 검색
	public List<CalendarDTO> search_closed(DateDTO date);
}
