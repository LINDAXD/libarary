package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.OverdueMapper;
import com.library.model.search.BookDTO;

@Service
public class OverdueServiceImpl implements OverdueService {

	@Autowired
	private OverdueMapper overdueMapper;

	// 연체 중 이력 조회
	@Override
	public List<BookDTO> overdue_list() {
		return overdueMapper.overdue_list();
	}

	// 연체 수 출력
	@Override
	public int get_total() {
		return overdueMapper.get_total();
	}

}
