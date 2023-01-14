package com.library.mapper.admin;

import java.util.List;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface LoanHistoryMapper {

	// 대출 내역 조회
	public List<BookDTO> loan_history(Criteria cri);

	// 대출 수 출력
	public int get_total(Criteria cri);
	
}
