package com.library.service.member;

import com.library.model.member.MemberDTO;

public interface SearchIdService {

	// 아이디 찾기 정보확인
	public int search_id_check(MemberDTO member) throws Exception;

	// 아이디 찾기
	public String search_id(MemberDTO member) throws Exception;

}
