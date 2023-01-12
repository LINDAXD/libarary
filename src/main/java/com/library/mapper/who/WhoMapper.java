package com.library.mapper.who;

import com.library.model.who.WhoDTO;

public interface WhoMapper {

	// 회원가입
	public void regiester(WhoDTO who) throws Exception;

}
