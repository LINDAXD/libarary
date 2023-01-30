package com.library.mapper.buy;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;

public interface BuyMapper {
   
	// 책 구매
	public void buy(BookDTO dto);
	
	// 책 구매(포장)
	public void pre_buy(BookDTO dto);
	
	// 구매 삭제
	public void buy_not(BookDTO dto);
	   
	// 장바구니에서 구매하기
	public void cart_buy(BookDTO dto);
	   
	// 장바구니 수량 불러오기
	public void count_buy(BookDTO dto);
	   
	// 구매 내역 수량 업데이트
	public void count_buy_update(BookDTO dto);
   
   
}