package com.library.service.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.search.BookMapper;
import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

	// 도서 대출
	@Override
	public void loan(BookDTO dto) {
		mapper.loan(dto);
	}

	// 대출 중인 해당 도서 수 카운트
	@Override
	public int count(String isbn) {
		return mapper.count(isbn);
	}

	// 대출 베스트 출력
	@Override
	public List<BookDTO> book_rank(DateDTO date) {
		return mapper.book_rank(date);
	}
	
	// 대출자 상태 체크
	@Override
	public int statusCheck(String user_id) {
		return mapper.statusCheck(user_id);
	}

	// 대출자 대출 수 증가
	@Override
	public void increase_count(String user_id) {
		mapper.increase_count(user_id);
	}

	// 회원이 대출 중인 도서인지 체크
	public int loan_check(String user_id, String book_isbn) {
		return mapper.loan_check(user_id, book_isbn);
	}
}
