package com.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.library.mapper.member.LoginMapper;
import com.library.model.member.CustomMemberDTO;
import com.library.model.member.MemberDTO;

public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	LoginMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("CustomUserDetailService 진입, 아이디 확인 : " + username);
		
		MemberDTO dto = mapper.login(username);
		
		return dto == null ? null : new CustomMemberDTO(dto);
	}

}
