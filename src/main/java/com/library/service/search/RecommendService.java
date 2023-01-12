package com.library.service.search;

import java.util.List;

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;

public interface RecommendService {

	// 추천 도서 리스트
	public List<BookDTO> recommend_list(Criteria cri, DateDTO date);

	// 추천 도서 수
	public int get_total(DateDTO date);

	// 추천 도서 등록
	public void regist_book(String user_id, BookDTO book);

	// 추천 도서 삭제
	public void delete_book(String book_isbn, DateDTO date);
}
