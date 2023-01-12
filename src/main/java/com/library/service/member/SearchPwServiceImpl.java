package com.library.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.member.SearchPwMapper;
import com.library.model.member.MemberDTO;

@Service
public class SearchPwServiceImpl implements SearchPwService {

	@Autowired
	private SearchPwMapper searchPwMapper;
	
	// 비밀번호 찾기 정보 확인
	@Override
	public int search_pw_check(MemberDTO member) throws Exception {
		
		return searchPwMapper.search_pw_check(member);
	}

	// 비밀번호 찾기 성공후 초기화
	@Override
	public void reset_pw(MemberDTO member, String newPw) throws Exception {
		searchPwMapper.reset_pw(member, newPw);
		
	}


}
