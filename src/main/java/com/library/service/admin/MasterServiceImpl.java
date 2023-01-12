package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.MasterMapper;
import com.library.model.member.MemberDTO;
import com.library.page.Criteria;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterMapper adminListMapper;

	// 관리자 총 인원 수 출력
	@Override
	public int get_total(Criteria cri) {
		return adminListMapper.get_total(cri);
	}

	// 관리자 수 페이징
	@Override
	public List<MemberDTO> list_paging(Criteria cri) {
		return adminListMapper.list_paging(cri);
	}

	// 관리자 권한 부여
	@Override
	public void grant(String user_id) {
		adminListMapper.grant(user_id);
	}

	// 관리자 권한 회수
	@Override
	public void revoke(String user_id) {
		adminListMapper.revoke(user_id);
	}

	// 회원 검색
	@Override
	public MemberDTO search(String user_id) {
		return adminListMapper.search(user_id);
	}

	// 관리자 권한 검색
	@Override
	public int admin_check(String user_id) {
		return adminListMapper.admin_check(user_id);
	}

}
