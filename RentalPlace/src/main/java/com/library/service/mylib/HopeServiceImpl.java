package com.library.service.mylib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.mylib.HopeMapper;
import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;

@Service
public class HopeServiceImpl implements HopeService {

	@Autowired
	private HopeMapper hopeMapper;

	// 희망 도서 신청
	@Override
	public void insert_hope(HopeDTO book) {
		hopeMapper.insert_hope(book);
	}

	// 희망 도서 신청내역
	@Override
	public List<HopeDTO> hope_list(Criteria cri, String user_id) {
		return hopeMapper.hope_list(cri, user_id);
	}

	// 희망 도서 신청 수
	@Override
	public int get_total(Criteria cri, String user_id) {
		return hopeMapper.get_total(cri, user_id);
	}

	// 희망 도서 정보 조회
	@Override
	public HopeDTO hope_info(HopeDTO hope) {
		return hopeMapper.hope_info(hope);
	}

}
