package com.library.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.model.mylib.HopeDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.admin.HopeHistoryService;

@Controller
@RequestMapping("/admin")
public class HopeHistoryController {

	@Autowired
	private HopeHistoryService hopeHistoryService;

	// 희망 도서 신청 내역 출력
	@GetMapping("/hope-history")
	public String hope_history(Model model, Criteria cri) {

		System.out.println("희망도서신청내역 진입");

		// 희망 도서 내역
		List<HopeDTO> hope_list = hopeHistoryService.hope_list(cri);

		for (HopeDTO hope : hope_list) {
			
			hope.setHope_reg_date(hope.getHope_reg_date().substring(0, 10));
		}

		model.addAttribute("hope_list", hope_list);

		// 희망 도서 신청 수
		int total = hopeHistoryService.get_total(cri);
		model.addAttribute("total", total);

		// 페이징
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/admin/sub2/hope_history";

	}

	// 희망 도서 정보
	@GetMapping("/hope-info")
	public String hope_info(Model model, Criteria cri, @RequestParam String hope_no) {

		// 희망 도서 정보 조회
		HopeDTO hope = hopeHistoryService.hope_info(hope_no);

		hope.setHope_reg_date(hope.getHope_reg_date().substring(0, 10));

		model.addAttribute("hope", hope);
		model.addAttribute("cri", cri);

		return "/admin/sub2/hope_info";
	}

	// 희망 도서 취소 처리
	@PostMapping("/hope-cancel")
	public String hope_cancel(Criteria cri, HopeDTO hope) {

		hopeHistoryService.hope_cancel(hope);

		return "redirect:/admin/hope-info?amount=" + cri.getAmount() + "&page=" + cri.getPage() + 
				"&type=" + cri.getType() + "&hope_no=" + hope.getHope_no();
	}
	
	// 희망 도서 소유 처리
	@PostMapping("/hope-own")
	public String hope_own(Criteria cri, HopeDTO hope) {

		hopeHistoryService.hope_own(hope.getHope_no());

		return "redirect:/admin/hope-info?amount=" + cri.getAmount() + "&page=" + cri.getPage() + 
				"&type=" + cri.getType() + "&hope_no=" + hope.getHope_no();
	}
}
