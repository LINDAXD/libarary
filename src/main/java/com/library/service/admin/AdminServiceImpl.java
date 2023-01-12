package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.AdminMapper;
import com.library.model.member.MemberDTO;
import com.library.page.Criteria;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	// member 모두 출력
	@Override
	public List<MemberDTO> member_list() {
		return adminMapper.member_list();
	}

	// 총 인원 수 출력
	@Override
	public int get_total(Criteria cri) {
		return adminMapper.get_total(cri);
	}

	//인원 수 페이징 처리
	@Override
	public List<MemberDTO> list_paging(Criteria cri) {
		return adminMapper.list_paging(cri);
	}

	// 회원 정보 출력
	@Override
	public MemberDTO member_view(String user_id) {
		return adminMapper.member_view(user_id);
	}

	// 회원 정보 수정
	@Override
	public void member_modify(MemberDTO member) {
		adminMapper.member_modify(member);
	}
	
	// 회원 탈퇴
	@Override
	public void member_delete(String user_id) {
		adminMapper.member_delete(user_id);
		
	}
	
	// 탈퇴 회원 테이블에 입력
	@Override
	public void insert_secession(String user_id, String user_email) {
		adminMapper.insert_secession(user_id, user_email);
	}
	
	
	
}
