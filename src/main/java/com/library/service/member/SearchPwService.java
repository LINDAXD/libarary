package com.library.service.member;

import com.library.model.member.MemberDTO;

public interface SearchPwService {

	// 비밀번호 찾기 정보 확인
	public int search_pw_check(MemberDTO member) throws Exception;

	// 비밀번호 찾기 성공후 초기화
	public void reset_pw(MemberDTO member, String newPw) throws Exception;
}
