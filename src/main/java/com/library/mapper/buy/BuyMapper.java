package com.library.mapper.buy;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;

public interface BuyMapper {
	
	public int buy_check(@Param("user_id")String user_id, @Param("book_isbn")String book_isbn);

	public void buy(BookDTO dto);
	
	
}
