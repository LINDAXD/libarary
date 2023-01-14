package com.library.service.mylib;

import java.util.List;

import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;

public interface HopeService {

	// 희망 도서 신청
	public void insert_hope(HopeDTO book);
	
	// 희망 도서 신청내역
	public List<HopeDTO> hope_list(Criteria cri, String user_id);
	
	// 희망 도서 신청수
	public int get_total(Criteria cri, String user_id);
	
	// 희망 도서 정보 조회
	public HopeDTO hope_info(HopeDTO hope);
}
