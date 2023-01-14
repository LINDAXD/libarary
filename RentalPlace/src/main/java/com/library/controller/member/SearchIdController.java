package com.library.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.member.MemberDTO;
import com.library.service.member.SearchIdService;

@Controller
@RequestMapping("/member")
public class SearchIdController {

	@Autowired
	private SearchIdService searchIdService;

	// 아이디 찾기 페이지 진입
	@GetMapping("/search-id")
	public String searchIdGET() {

		System.out.println("아이디 찾기 페이지 진입");

		return "/member/sub3/search_id";

	}

	// 아이디 찾기 정보확인
	@ResponseBody
	@PostMapping("/search-id-check")
	public String searchIdCheck(MemberDTO member) throws Exception {

		System.out.println("searchIdCheck() 진입");

		int result = searchIdService.search_id_check(member);

		if (result == 0) {

			return "fail";

		} else {

			return "success";

		}
	}

	// 아이디 찾기 성공
	@PostMapping("/search-id")
	public String searchIdPOST(MemberDTO member, Model model) throws Exception {

		System.out.println("searchId() 진입");

		String result = searchIdService.search_id(member);

		model.addAttribute("search_id", result);

		System.out.println(result);
		return "redirect:/member/search-id-result";

	}

	// 아이디 찾기 결과
	@GetMapping("/search-id-result")
	public String searchIdResult(@RequestParam String search_id, Model model) {

		model.addAttribute("search_id", search_id);
		return "/member/sub3/search_id_result";

	}
}
