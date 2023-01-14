package com.library.service.mylib;

import java.util.List;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface MyLoanHistoryService {

	// 대출 내역 조회
	public List<BookDTO> loan_history(String user_id, Criteria cri, String start_date, String end_date);

	// 대출 수 출력
	public int get_total(String user_id, String start_date, String end_date);

}
