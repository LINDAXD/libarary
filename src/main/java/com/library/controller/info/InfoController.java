package com.library.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/*")
public class InfoController {
	
	@GetMapping("/signup")
	public String signup() {

		return "/info/sub1/signup";
	}
	
	@GetMapping("/openingHours")
	public String openingHours() {

		return "/info/sub2/openingHours";
	}

	@GetMapping("/loanBook")
	public String loanBook() {

		return "/info/sub3/loanBook";

		// 컨트롤러에서 서비스단으로 넘긴다.(모델이라는 객체 이용하면 뷰단으로 쉽게 빼낼 수 있음)
	}

}
