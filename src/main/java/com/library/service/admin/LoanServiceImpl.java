package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.LoanMapper;
import com.library.model.search.BookDTO;
import com.library.page.Criteria;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanMapper loanMapper;

	// 대출 중 이력 조회
	@Override
	public List<BookDTO> loan_list_paging(Criteria cri) {
		return loanMapper.loan_list_paging(cri);
	}

	// 대출 수 출력
	@Override
	public int get_total(Criteria cri) {
		return loanMapper.get_total(cri);
	}

	// 도서 반납
	@Override
	public int return_book(BookDTO book) {
		return loanMapper.return_book(book);
	}

	// 회원 대출 중 도서 수 감소
	@Override
	public void decrease_count(BookDTO book) {
		loanMapper.decrease_count(book);

	}

	// 연체 도서 일 수 검색
	@Override
	public int search_overdue(int loan_no) {
		return loanMapper.search_overdue(loan_no);
	}

	// 연체 일 만큼 대출 불가 일 수 추가
	@Override
	public void update_user_overdue(String user_id, int date) {
		loanMapper.update_user_overdue(user_id, date);
	}

}
