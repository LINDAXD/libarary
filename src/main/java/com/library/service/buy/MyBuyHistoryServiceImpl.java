package com.library.service.buy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.buy.MyBuyHistoryMapper;
import com.library.model.search.BookDTO;
import com.library.page.Criteria;

@Service
public class MyBuyHistoryServiceImpl implements MyBuyHistoryService{
	
	@Autowired
	private MyBuyHistoryMapper mapper;

	@Override
	public List<BookDTO> buy_history(String user_id, Criteria cri) {
		return mapper.buy_history(user_id,cri);
	}

	@Override
	public int get_total_buy(String user_id) {
		return mapper.get_total_buy(user_id);
	}
	
}
