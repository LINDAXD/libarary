package com.library.service.admin;

import java.util.List;

import com.library.model.search.BookDTO;

public interface OverdueService {

	// 연체 중 이력 조회
	public List<BookDTO> overdue_list();

	// 연체 수 출력
	public int get_total();

}
