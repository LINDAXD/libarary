package com.library.service.buy;

import com.library.model.search.BookDTO;

public interface BuyService {

   public void buy(BookDTO dto);
   
   public void pre_buy(BookDTO dto);
   
   public void cart_buy(BookDTO dto);
   
   public void count_buy(BookDTO dto);
   
   public void count_buy_update(BookDTO dto);
   
   public int buy_check(String user_id,String book_isbn);
}