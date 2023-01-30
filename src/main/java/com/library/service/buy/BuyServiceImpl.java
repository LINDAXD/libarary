package com.library.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.buy.BuyMapper;
import com.library.model.search.BookDTO;

@Service
public class BuyServiceImpl implements BuyService{
   
   @Autowired
   private BuyMapper mapper;

   // 책 구매
   @Override
   public void buy(BookDTO dto) {
      mapper.buy(dto);
      
   }
   
   // 책 구매(포장)
   @Override
   public void pre_buy(BookDTO dto) {
      mapper.pre_buy(dto);
      
   }
   
   // 구매 삭제
   @Override
   public void buy_not(BookDTO dto) {
	   mapper.buy_not(dto);
	   
   }
   
   // 장바구니에서 구매하기
   @Override
   public void cart_buy(BookDTO dto) {
      mapper.cart_buy(dto);
      
   }
   
   // 장바구니 수량 불러오기
   @Override
   public void count_buy(BookDTO dto) {
      mapper.count_buy(dto);
      
   }
   
   // 구매 내역 수량 업데이트
   @Override
   public void count_buy_update(BookDTO dto) {
      mapper.count_buy_update(dto);
      
   }

   
}