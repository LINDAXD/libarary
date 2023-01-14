package com.library.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.model.member.MemberDTO;
import com.library.page.Criteria;

public interface AdminMapper {

	// 회원 모두 출력
	public List<MemberDTO> member_list();

	// 회원 총 인원 수 출력
	public int get_total(Criteria cri);

	// 회원 수 페이징
	public List<MemberDTO> list_paging(Criteria cri);

	// 회원 정보 출력
	public MemberDTO member_view(String user_id);

	// 회원 정보 수정
	public void member_modify(MemberDTO member);

	// 회원 탈퇴
	public void member_delete(String user_id);

	// 탈퇴 회원 테이블에 입력
	public void insert_secession(@Param("user_id") String user_id, @Param("user_email") String user_email);
}
