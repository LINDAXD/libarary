package com.library.service.buy;

import java.util.List;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface MyBuyHistoryService {

	// 찜한 내역 조회
		public List<BookDTO> buy_history(String user_id, Criteria cri);

		// 찜한 도서 건수 출력
		public int get_total_buy(String user_id);
}
