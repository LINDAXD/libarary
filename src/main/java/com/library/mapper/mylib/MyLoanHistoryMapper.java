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
	
	
	
	//<!--=========== 찜하기 추기 =================-->
	// 찜한 내역 조회
	public List<BookDTO> like_history(@Param("user_id") String user_id, @Param("cri") Criteria cri);

	// 찜한 도서 수 출력
	public int get_total_like(@Param("user_id") String user_id);
	
	
	
	
	/* -----------------------------장바구니 추가--------------------- */
	public List<BookDTO> cart_history(@Param("user_id") String user_id, @Param("cri") Criteria cri);

	// 대출 수 출력
	public int get_total_cart(@Param("user_id") String user_id);
	
	public int get_total_count(@Param("user_id") String user_id);
	
	public List<BookDTO> get_total_pay(@Param("user_id") String user_id, @Param("priceStandard") String priceStandard);
	
	public int get_total_final(@Param("user_id") String user_id, @Param("priceStandard") String priceStandard);
	

}
