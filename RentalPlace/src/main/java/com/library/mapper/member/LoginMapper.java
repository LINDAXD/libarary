package com.library.mapper.member;

import com.library.model.member.MemberDTO;

public interface LoginMapper {
	
	public MemberDTO login(String user_id);
}
