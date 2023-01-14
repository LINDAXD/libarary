package com.library.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.board.CalendarDTO;
import com.library.model.search.DateDTO;
import com.library.service.board.CalendarService;
import com.library.util.DateUtil;

@Controller
@RequestMapping("/admin")
public class AdminCalendarController {

	@Autowired
	private CalendarService calService;

	// 일정 리스트 출력 (get)
	@GetMapping("/calendar-list")
	public String calendar_list(Model model, DateDTO date) {

		// year이 null 이면 현재 날짜 기준 year
		if (date.getYear() == null) {
			
			date.setYear(DateUtil.date("year"));
		}

		// month가 null 이면 현재 날짜 기준 month
		if (date.getMonth() == null) {
			
			date.setMonth(DateUtil.date("month"));
		}

		// year, month 조건 검색
		List<CalendarDTO> list = calService.list_ym(date);

		for (CalendarDTO c : list) {

			c.setReg_date(c.getReg_date().substring(0, 10));
		}

		// 일정 리스트
		model.addAttribute("list", list);

		// 년
		model.addAttribute("year", date.getYear());
		// 월
		model.addAttribute("month", date.getMonth());
		return "/admin/sub4/calendar_list";
	}

	// 일정 추가 팝업 (get)
	@GetMapping("/cal-add")
	public String calendarAddGET() {
		return "/admin/sub4/cal_add";
	}

	// 일정 추가
	@PostMapping("/cal-add")
	public String calendarAddPOST(CalendarDTO cal, Principal principal) {

		String user_id = principal.getName();

		cal.setUser_id(user_id);
		
		// 종료일을 시작일과 같게
		cal.setEnd(cal.getStart());
		
		// 일정 추가
		calService.insert_cal(cal);

		return "redirect:/admin/cal-add";
	}

	// 일정 삭제
	@PostMapping("/cal-del")
	public String calendarDel(CalendarDTO cal, DateDTO date) {

		// 일정삭제
		calService.delete_cal(cal.getCal_no());
		
		return "redirect:/admin/calendar-list?year=" + date.getYear() + "&month=" + date.getMonth();
	}

}
