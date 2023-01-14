package com.library.service.admin;

import java.util.List;

import com.library.model.member.MemberDTO;
import com.library.page.Criteria;

public interface MasterService {

	// 관리자 총 인원 수 출력
	public int get_total(Criteria cri);

	// 관리자 수 페이징
	public List<MemberDTO> list_paging(Criteria cri);

	// 관리자 권한 부여
	public void grant(String user_id);

	// 관리자 권한 회수
	public void revoke(String user_id);

	// 회원 검색
	public MemberDTO search(String user_id);

	// 관리자 권한 검색
	public int admin_check(String user_id);
}
