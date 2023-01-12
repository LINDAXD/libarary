package com.library.mapper.mylib;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;

public interface HopeMapper {

	// 희망도서 신청
	public void insert_hope(HopeDTO book);

	// 희망도서 신청내역
	public List<HopeDTO> hope_list(@Param("cri") Criteria cri, @Param("user_id") String user_id);

	// 희망도서 신청수
	public int get_total(@Param("cri") Criteria cri, @Param("user_id") String user_id);

	// 희망 도서 정보 조회
	public HopeDTO hope_info(HopeDTO hope);
}
