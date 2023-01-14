package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.LoanHistoryMapper;
import com.library.model.search.BookDTO;
import com.library.page.Criteria;

@Service
public class LoanHistoryServiceImpl implements LoanHistoryService {

	@Autowired
	private LoanHistoryMapper mapper;

	// 대출 내역 조회
	@Override
	public List<BookDTO> loan_history(Criteria cri) {
		return mapper.loan_history(cri);
	}
	
	// 대출 수 출력
	@Override
	public int get_total(Criteria cri) {
		return mapper.get_total(cri);
	}


}
