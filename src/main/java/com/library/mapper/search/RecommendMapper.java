package com.library.mapper.search;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;

public interface RecommendMapper {

	// 추천 도서 리스트
	public List<BookDTO> recommend_list(@Param("cri") Criteria cri, @Param("date") DateDTO date);

	// 추천 도서 수
	public int get_total(DateDTO date);

	// 추천 도서 등록
	public void regist_book(@Param("user_id") String user_id, @Param("book") BookDTO book);

	public void delete_book(@Param("book_isbn") String book_isbn, @Param("date") DateDTO date);
}
