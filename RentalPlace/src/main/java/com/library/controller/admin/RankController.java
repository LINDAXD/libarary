package com.library.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.member.MemberDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;
import com.library.service.admin.RankService;
import com.library.util.DateUtil;

@Controller
@RequestMapping("/admin")
public class RankController {

	@Autowired
	private RankService rankService;

	// 멤버 리스트 출력 (get)
	@GetMapping("/rank-member") 
	public String member_list(Model model, Criteria cri, DateDTO date) {

		System.out.println("recommend_book 진입");

		// year이 null 이면 현재 날짜 기준 year
		if (date.getYear() == null) {
			date.setYear(DateUtil.date("year"));
		}

		// month가 null 이면 현재 날짜 기준 month
		if (date.getMonth() == null) {
			date.setMonth(DateUtil.date("month"));
		}

		// 년
		model.addAttribute("year", date.getYear());
		// 월
		model.addAttribute("month", date.getMonth());

		List<MemberDTO> rank_list = rankService.rank_list(date);
		
		for (MemberDTO m : rank_list) {
			m.setUser_reg_date(m.getUser_reg_date().substring(0, 10));
		}

		model.addAttribute("rank_list", rank_list);
		
		return "/admin/sub2/rank_member";

	}

}
