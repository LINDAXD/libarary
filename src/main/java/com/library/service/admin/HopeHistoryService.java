package com.library.service.admin;

import java.util.List;

import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;

public interface HopeHistoryService {

	// 희망 도서 신청내역
	public List<HopeDTO> hope_list(Criteria cri);
	
	// 희망 도서 신청수
	public int get_total(Criteria cri);
	
	// 희망 도서 정보 조회
	public HopeDTO hope_info(String hope_no);
	
	// 희망 도서 취소
	public void hope_cancel(HopeDTO hope);
	
	// 희망 도서 소장
	public void hope_own(int hope_no);
}
