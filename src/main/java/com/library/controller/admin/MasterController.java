package com.library.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.member.MemberDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.admin.MasterService;

@Controller
@RequestMapping("/master")
public class MasterController {

	@Autowired
	private MasterService adminListService;

	// 관리자 목록 출력
	@GetMapping("/admin-list")
	public String overdue_list(Model model, Criteria cri) {
		List<MemberDTO> admin_list = adminListService.list_paging(cri);

		for (MemberDTO m : admin_list) {

			m.setUser_reg_date(m.getUser_reg_date().substring(0, 10));
		}

		model.addAttribute("admin_list", admin_list);

		int total = adminListService.get_total(cri);
		model.addAttribute("total", total);

		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/admin/sub3/admin_list";

	}

	// 관리자 추가메뉴
	@GetMapping("/add-popup")
	public String add_popup(Model model) {

		return "/admin/sub3/add_popup";

	}

	// 관리자 추가할 회원 검색
	@GetMapping("/admin-search")
	public String Admin_search(Model model, @RequestParam String user_id) {
		MemberDTO result = adminListService.search(user_id);

		if (result != null) {
			result.setUser_reg_date(result.getUser_reg_date().substring(0, 10));
		}

		model.addAttribute("result", result);

		return "/admin/sub3/add_popup";

	}

	// 관리자 권한 부여
	@PostMapping("/grant")
	public String grant(@RequestParam String user_id) {

		adminListService.grant(user_id);

		return "redirect:/master/add-popup";
	}

	// 관리자 권한가지고 있는지 검색
	@ResponseBody
	@PostMapping("/adminChk")
	public String adminChk(@RequestParam String user_id) {

		int result = adminListService.admin_check(user_id);

		if (result == 1) {
			return "fail";
		} else {
			return "success";
		}

	}

	@PostMapping("/revoke")
	public String revoke(Criteria cri, @RequestParam String user_id) {

		adminListService.revoke(user_id);

		int amount = cri.getAmount();
		int page = cri.getPage();
		String type = cri.getType();
		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/master/admin-list";
		}

		return "redirect:/master/admin-list?amount=" + amount + "&page=" + page + "&type=" + type + "&keyword="
				+ keyword;
	}

}
