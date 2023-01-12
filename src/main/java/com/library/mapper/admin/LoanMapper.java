package com.library.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;

public interface LoanMapper {

	// 대출 중 이력 조회
	public List<BookDTO> loan_list_paging(Criteria cri);

	// 대출 수 출력
	public int get_total(Criteria cri);
	
	// 도서 반납
	public int return_book(BookDTO book);
	
	// 회원 대출 중 도서 수 감소
	public void decrease_count(BookDTO book);
	
	// 연체 도서일 수 검색
	public int search_overdue(int loan_no);
	
	// 연체 일 만큼 대출 불가 일 수 추가
	public void update_user_overdue(@Param("user_id") String user_id, @Param("date") int date);
}
