package com.library.mapper.search;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;

public interface BookMapper {

	// 도서 대출
	public void loan(BookDTO dto);

	// 대출 중인 해당 도서 수 카운트
	public int count(String isbn);

	// 대출 베스트 출력
	public List<BookDTO> book_rank(DateDTO date);

	// 대출자 상태 체크
	public int statusCheck(String user_id);

	// 대출자 대출 수 증가
	public void increase_count(String user_id);

	// 회원이 대출 중인 도서인지 체크
	public int loan_check(@Param("user_id")String user_id, @Param("book_isbn")String book_isbn);
	
	
	
	////////////////////////// 찜하기 추가 //////////////////////////////
	// 회원이 이미 찜한 도서인지 체크
	public int like_check(@Param("user_id")String user_id, @Param("book_isbn")String book_isbn);
	
	// 찜하기 등록
	public void like(BookDTO dto);
	
	// 찜한 도서 삭제
	public void delete_like_book(@Param("book_isbn") String book_isbn);
	
	/*------------------------------- 장바구니 구현-------------------------------------- */
	public void cart(BookDTO dto);

	public int cart_check(@Param("user_id") String user_id, @Param("book_isbn") String book_isbn);

	public int modifyCount(
			 @Param("book_title") String book_title, @Param("bookCount")int bookCount  /* BookDTO book */);

	public int deleteCart(@Param("cart_id") int cart_id);
}
