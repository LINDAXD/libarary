package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.RankMapper;
import com.library.model.member.MemberDTO;
import com.library.model.search.DateDTO;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	private RankMapper rankMapper;
	
	//회원 대출 순위
	@Override
	public List<MemberDTO> rank_list(DateDTO date) {
		return rankMapper.rank_list(date);
	}

}
