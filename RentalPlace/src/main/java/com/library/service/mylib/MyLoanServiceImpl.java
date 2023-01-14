package com.library.service.mylib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.mylib.MyLoanMapper;
import com.library.model.search.BookDTO;

@Service
public class MyLoanServiceImpl implements MyLoanService {

	@Autowired
	private MyLoanMapper myLoanMapper;

	// 대출 중 이력 조회
	@Override
	public List<BookDTO> loan_list_paging(String user_id) {
		return myLoanMapper.loan_list_paging(user_id);
	}

	// 대출 수 출력
	@Override
	public int get_total(String user_id) {
		return myLoanMapper.get_total(user_id);
	}

	// 대출 정지 일
	@Override
	public int get_overdue_date(String user_id) {
		return myLoanMapper.get_overdue_date(user_id);
	}

	// 연체 도서 수
	@Override
	public int get_overdue(String user_id) {
		return myLoanMapper.get_overdue(user_id);
	}

}
