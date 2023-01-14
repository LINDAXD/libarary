package com.library.mapper.member;

import com.library.model.member.MemberDTO;

public interface SearchIdMapper {

	// 아이디 찾기 정보 확인
	public int search_id_check(MemberDTO member);

	// 아이디 찾기
	public String search_id(MemberDTO member);

}
