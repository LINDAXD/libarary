package com.library.mapper.mylib;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface MyLoanHistoryMapper {

	// 대출 내역 조회
	public List<BookDTO> loan_history(@Param("user_id") String user_id, @Param("cri") Criteria cri,
			@Param("start_date") String start_date, @Param("end_date") String end_date);

	// 대출 수 출력
	public int get_total(@Param("user_id") String user_id, @Param("start_date") String start_date,
			@Param("end_date") String end_date);

}
