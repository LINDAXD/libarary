package com.library.service.admin;

import java.util.List;

import com.library.model.member.MemberDTO;
import com.library.model.search.DateDTO;

public interface RankService {

	// 회원 대출 순위
	public List<MemberDTO> rank_list(DateDTO date);

}
