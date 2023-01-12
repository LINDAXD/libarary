package com.library.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.member.SearchIdMapper;
import com.library.model.member.MemberDTO;

@Service
public class SearchIdServiceImpl implements SearchIdService {

	@Autowired
	private SearchIdMapper searchIdMapper;

	
	// 아이디 찾기 정보확인
	@Override
	public int search_id_check(MemberDTO member) throws Exception {
		
		return searchIdMapper.search_id_check(member);
	}

	// 아이디 찾기
	@Override
	public String search_id(MemberDTO member) throws Exception {
		return searchIdMapper.search_id(member);
	}
	

}
