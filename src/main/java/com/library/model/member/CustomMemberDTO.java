package com.library.model.member;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomMemberDTO extends User {

	private MemberDTO dto;

	public CustomMemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	public CustomMemberDTO(MemberDTO dto) {
		super(dto.getUser_id(), dto.getUser_pw(), 
				dto.getAuthList().stream().map(i -> new SimpleGrantedAuthority(i.getAuth()))
				.collect(Collectors.toList()));
		
		this.dto = dto;
	}
}
