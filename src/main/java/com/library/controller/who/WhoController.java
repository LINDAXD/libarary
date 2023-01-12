package com.library.controller.who;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.who.WhoDTO;
import com.library.service.who.WhoService;

@Controller
@RequestMapping("/who")
public class WhoController {
	
	@Autowired
	private WhoService whoService;
	
	// 후기
	@PostMapping("/register")
	public String register(WhoDTO who) throws Exception {

		// 
		who.setWho(who.getWho());
	
		// 후기service 쿼리 실행
		whoService.regiester(who);


		System.out.println("후기 등록");

		return "redirect:/who/login";
	}


}

