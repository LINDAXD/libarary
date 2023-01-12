package com.library.mapper.admin;

import java.util.List;

import com.library.model.search.BookDTO;

public interface OverdueMapper {

	// 대출 중 이력 조회
	public List<BookDTO> overdue_list();

	// 대출 수 출력
	public int get_total();
}
