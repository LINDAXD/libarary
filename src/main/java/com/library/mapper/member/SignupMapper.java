package com.library.mapper.member;

import com.library.model.member.MemberDTO;

public interface SignupMapper {

	// 회원가입
	public void signUp(MemberDTO member) throws Exception;

	// 중복 아이디 체크
	public int idCheck(String user_id) throws Exception;

	// 중복 이메일 체크
	public int mailCheck(String user_email) throws Exception;

	// 탈퇴 회원 중 아이디 체크
	public int secession_idCheck(String user_id) throws Exception;

	// 탈퇴 회원 중 메일 체크
	public int secession_mailCheck(String user_email) throws Exception;

	// 회원 권한 입력
	public void auth(String user_id);
}
