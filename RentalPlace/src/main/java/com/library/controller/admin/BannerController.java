package com.library.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.admin.BannerDTO;
import com.library.page.Criteria;
import com.library.service.admin.BannerService;

@Controller
@RequestMapping("/admin")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	// 배너 목록 출력
	@GetMapping("/banner-list")
	public String overdue_list(Model model, Criteria cri) {
		
		List<BannerDTO> banner_list = bannerService.banner_list();

		for (BannerDTO b : banner_list) {

			b.setReg_date(b.getReg_date().substring(0, 10));
		}

		model.addAttribute("banner_list", banner_list);


		return "/admin/sub5/banner_list";
	}
	
	// 배너 추가 페이지
	@GetMapping("/banner-add")
	public String banner_addGET() {
		return "/admin/sub5/banner-add";
	}
	
	// 배너 추가
	@PostMapping("/banner-add")
	public String banner_addPost(Principal principal, BannerDTO banner) {
		
		String user_id = principal.getName();
		
		banner.setUser_id(user_id);
		
		bannerService.banner_add(banner);		
		return "redirect:/admin/banner_add";
	}
	
	
	// 배너 삭제
	@PostMapping("/banner-del")
	public String banner_del(BannerDTO banner) {
		
		bannerService.banner_del(banner);
		
		return "redirect:/admin/banner-list";
	}

}
