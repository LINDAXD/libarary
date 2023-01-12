package com.library.model.member;

import lombok.Data;

@Data
public class MemberAuthDTO {
	
	// 회원 아이디
	private String user_id;
	
	// 회원 권한
	private String auth;
	
}
