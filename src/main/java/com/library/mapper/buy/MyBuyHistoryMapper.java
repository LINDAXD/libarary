package com.library.mapper.buy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface MyBuyHistoryMapper {

	// 대출 내역 조회
	public List<BookDTO> buy_history(@Param("user_id") String user_id, @Param("cri") Criteria cri);

	// 대출 수 출력
	public int get_total_buy(@Param("user_id") String user_id);
}
