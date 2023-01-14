package com.library.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class FaqController {

	@GetMapping("/faq")
	public String articleList() {

		return "/board/sub2/faq";
	}

}
