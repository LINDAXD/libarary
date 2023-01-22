package com.library.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.buy.BuyMapper;
import com.library.model.search.BookDTO;

@Service
public class BuyServiceImpl implements BuyService{
	
	@Autowired
	private BuyMapper mapper;

	@Override
	public void buy(BookDTO dto) {
		mapper.buy(dto);
		
	}

	@Override
	public int buy_check(String user_id, String book_isbn) {
		return mapper.buy_check(user_id, book_isbn);
	}

	
	
}
