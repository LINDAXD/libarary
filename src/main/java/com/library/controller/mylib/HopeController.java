package com.library.controller.mylib;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.mylib.HopeService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/mylib")
public class HopeController {

	@Autowired
	private HopeService hopeService;
	
	
	// 희망 도서 신청 get
	@GetMapping("/hope")
	public String hopeGET(Model model) {

		System.out.println("도서신청 폼 진입");

		return "/mylib/sub2/hope";

	}
	
	// 희망도서 신청 post
	@PostMapping("/hope")
	public String hopePOST(HopeDTO hope, Principal principal) {
		
		System.out.println("도서신청 post");
		
		// 로그인 된 id 받아오기
		hope.setUser_id(principal.getName());
		
		// 특수문자 치환
		hope.setBook_title(XssUtil.XssReplace(hope.getBook_title()));
		hope.setBook_author(XssUtil.XssReplace(hope.getBook_author()));
		hope.setBook_publisher(XssUtil.XssReplace(hope.getBook_publisher()));
		hope.setBook_pubDate(XssUtil.XssReplace(hope.getBook_pubDate()));
		hope.setBook_isbn(XssUtil.XssReplace(hope.getBook_isbn()));
		hope.setNote(XssUtil.XssReplace(hope.getNote()));
		hope.setBook_price(XssUtil.XssReplace(hope.getBook_price()));
		
		// 희망 도서 신청
		hopeService.insert_hope(hope);
		
		return "redirect:/mylib/hope";
	}
	
	// 나의 희망 도서 신청 내역
	@GetMapping("/hope-history")
	public String hope_history(Model model, Criteria cri, Principal principal) {

		System.out.println("희망도서신청내역 진입");

		// 로그인 된 id 받아오기
		String id = principal.getName();
		
		// 나의 희망 도서 목록
		List<HopeDTO> hope_list = hopeService.hope_list(cri, id);

		for (HopeDTO hope : hope_list) {

			hope.setHope_reg_date(hope.getHope_reg_date().substring(0, 10));
		}

		model.addAttribute("hope_list", hope_list);

		// 나의 희망 도서 신청 수 
		int total = hopeService.get_total(cri, id);
		model.addAttribute("total", total);

		// 페이징
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/mylib/sub2/hope_history";

	}
	
	// 희망 도서 정보
	@GetMapping("/hope-info")
	public String hope_info(Model model, Criteria cri, HopeDTO hope, Principal principal) {

		// 로그인 된 아이디 받아오기
		String id = principal.getName();
		
		// DTO id set
		hope.setUser_id(id);
		
		// 희망 도서 정보 받아오기
		HopeDTO hope_info = hopeService.hope_info(hope);

		hope_info.setHope_reg_date(hope_info.getHope_reg_date().substring(0, 10));

		model.addAttribute("hope", hope_info);
		model.addAttribute("cri", cri);

		return "/mylib/sub2/hope_info";
	}
}
