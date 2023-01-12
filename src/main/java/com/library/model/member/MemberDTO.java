package com.library.model.member;

import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {
	// 회원 id
	private String user_id;

	// 회원 비밀번호
	private String user_pw;

	// 회원 이름
	private String user_name;
	
	// 회원 생년월일
	private String user_birth;
	
	// 회원 전화번호
	private String user_tel;

	// 회원 이메일
	private String user_email;

	// 회원 우편번호
	private String user_zip;

	// 회원 주소
	private String user_address;

	// 회원 상세주소
	private String user_address_detail;
	
	// 회원 대출가능 도서 수
	private int user_able_loan;
	
	// 회원 대출 중 도서 수
	private int user_book_count;

	// 회원 대출불가 일 수
	private String user_overdue_date;

	// 회원가입일
	private String user_reg_date;
	
	// 회원 권한 여부
	private boolean enabled;
	
	// 회원 권한 리스트
	private List<MemberAuthDTO> authList;
	
	// 회원 대출 총 도서수를 위한 count
	private int count;
	

}
