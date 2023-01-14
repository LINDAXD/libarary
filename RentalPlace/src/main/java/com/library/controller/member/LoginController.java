package com.library.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class LoginController {

	// 로그인 페이지 진입
	@GetMapping("/login")
	public String loginGET() {

		System.out.println("로그인 페이지 진입");

		return "/member/sub1/login";

	}

}
