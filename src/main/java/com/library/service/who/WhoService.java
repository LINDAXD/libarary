package com.library.service.who;

import com.library.model.who.WhoDTO;

public interface WhoService {

	// 회원가입
	public void regiester(WhoDTO who) throws Exception;
}
