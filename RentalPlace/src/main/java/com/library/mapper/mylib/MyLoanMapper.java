package com.library.mapper.mylib;

import java.util.List;

import com.library.model.search.BookDTO;

public interface MyLoanMapper {

	// 대출 중 이력 조회
	public List<BookDTO> loan_list_paging(String user_id);

	// 대출 수 출력
	public int get_total(String user_id);
	
	// 대출 정지 일
	public int get_overdue_date(String user_id);
	
	// 연체 도서 수
	public int get_overdue(String user_id);

}
