package com.library.service.mylib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.mylib.MyPageMapper;
import com.library.model.member.MemberDTO;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageMapper myPageMapper;

	// 회원 정보 출력
	@Override
	public MemberDTO my_view(String user_id) {
		return myPageMapper.my_view(user_id);
	}

	// 회원 정보 수정
	@Override
	public void my_modify(MemberDTO member) {
		myPageMapper.my_modify(member);
	}

	// 회원 탈퇴
	@Override
	public void my_secession(String user_id) {
		myPageMapper.my_secession(user_id);
	}
	
	// 탈퇴 회원 테이블 삭제
	@Override
	public void delete_buyhistory(String user_id) {
		myPageMapper.delete_buyhistory(user_id);
	}
	@Override
	public void delete_cart(String user_id) {
		myPageMapper.delete_cart(user_id);
	}
	@Override
	public void delete_likehistory(String user_id) {
		myPageMapper.delete_likehistory(user_id);
	}
	@Override
	public void delete_loanhistory(String user_id) {
		myPageMapper.delete_loanhistory(user_id);
	}
	@Override
	public void delete_review(String user_id) {
		myPageMapper.delete_review(user_id);
	}
	@Override
	public void delete_hope(String user_id) {
		myPageMapper.delete_hope(user_id);
	}
	@Override
	public void delete_recommend_book(String user_id) {
		myPageMapper.delete_recommend_book(user_id);
	}
	@Override
	public void delete_notice(String user_id) {
		myPageMapper.delete_notice(user_id);
	}
	@Override
	public void delete_answer(String user_id) {
		myPageMapper.delete_answer(user_id);
	}
	@Override
	public void delete_enquiry(String user_id) {
		myPageMapper.delete_enquiry(user_id);
	}

	// 탈퇴 회원 테이블에 입력
	@Override
	public void insert_secession(MemberDTO member) {
		myPageMapper.insert_secession(member);
	}

	// 비밀번호 변경
	@Override
	public int modify_pw(String user_id, String user_pw) {
		return myPageMapper.modify_pw(user_id, user_pw);
	}

}
