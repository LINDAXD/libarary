package com.library.service.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.search.RecommendMapper;
import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	private RecommendMapper recommendMapper;

	// 추천 도서 리스트
	@Override
	public List<BookDTO> recommend_list(Criteria cri, DateDTO date) {
		return recommendMapper.recommend_list(cri, date);
	}

	// 추천 도서 수
	@Override
	public int get_total(DateDTO date) {
		return recommendMapper.get_total(date);
	}

	
	// 추천 도서 등록
	@Override
	public void regist_book(String user_id, BookDTO book) {
		recommendMapper.regist_book(user_id, book);
	}

	// 추천 도서 삭제
	@Override
	public void delete_book(String book_isbn, DateDTO date) {
		recommendMapper.delete_book(book_isbn, date);
	}
}
