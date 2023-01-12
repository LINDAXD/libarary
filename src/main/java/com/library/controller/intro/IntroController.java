package com.library.controller.intro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class IntroController {

	@GetMapping("/greeting")
	public String intro() {

		return "/intro/sub1/greeting";
	}
	
	@GetMapping("/history")
	public String history() {

		return "/intro/sub2/history";
	}
	
	@GetMapping("/road")
	public String road() {
		return "/intro/sub3/road";
	}


}
