package com.library.service.who;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.who.WhoMapper;
import com.library.model.who.WhoDTO;

@Service
public class WhoServiceImpl implements WhoService {

	@Autowired
	private WhoMapper whoMapper;

	// 회원가입
	@Override
	public void regiester(WhoDTO who) throws Exception {

		whoMapper.regiester(who);

	}

	}

