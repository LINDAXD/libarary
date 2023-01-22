package com.library.service.search;

import java.util.List;

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;

public interface BookService {

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
	public int loan_check(String user_id, String book_isbn);
	
	
	//////////////////////////찜하기 추가 //////////////////////
	// 찜하기한건지 체크 (회원이 이미 찜한 도서인지 체크)
	public int like_check(String user_id, String book_isbn);
	
	// 찜 등록 하기 
	public void like(BookDTO dto);
	
	// 찜한 도서 삭제
	public void delete_like_book(String book_isbn);
	
	/*-------------------------------- 장바구니 구현------------------ */
	// 장바구니 등록 하기 
	public void cart(BookDTO dto);
		
	// 장바구니 안에 제품이 있는지 체크 (회원이 이미 장바구니에 넣은 도서인지 체크)
	public int cart_check(String user_id, String book_isbn);
	
	public int modifyCount(String book_title, int bookCount);
	
	public int deleteCart(int cart_id);

}
