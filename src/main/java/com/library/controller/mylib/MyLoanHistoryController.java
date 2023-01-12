package com.library.controller.mylib;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.mylib.MyLoanHistoryService;

@Controller
@RequestMapping("/mylib")
public class MyLoanHistoryController {

	@Autowired
	private MyLoanHistoryService service;

	// 대출 내역 리스트 출력 (get)
	@GetMapping("/loan-history")
	public String my_loan_history(Model model, Criteria cri, String start_date, String end_date, Principal principal) {

		System.out.println("my_loan_history 진입");

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// start_date가 null 이면 현재 날짜에서 -1년한 날짜
		if (start_date == null) {
			start_date = date("start");
			System.out.println(start_date);
		}

		// end_date가 null 이면 현재 날짜
		if (end_date == null) {
			end_date = date("end");
			System.out.println(end_date);
		}

		// 회원의 대출 내역 받아오기
		List<BookDTO> loan_history = service.loan_history(id, cri, start_date, end_date);

		for (BookDTO book : loan_history) {

			book.setLoan_date(book.getLoan_date().substring(0, 10));
			if (book.getReturn_date() != null) {

				book.setReturn_date(book.getReturn_date().substring(0, 10));
			}

			book.setReturn_period(book.getReturn_period().substring(0, 10));
		}

		// 대출 내역
		model.addAttribute("loan_history", loan_history);

		// 대출 건수
		int total = service.get_total(id, start_date, end_date);
		model.addAttribute("total", total);

		// 페이징 정보
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);

		return "/mylib/sub1/loan_history";

	}

	public String date(String type) {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(now);

		int year = cal.get(Calendar.YEAR) - 1;
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String strMonth = Integer.toString(month);
		String strDay = Integer.toString(day);

		if (strMonth.length() == 1) {
			strMonth = "0" + strMonth;
		}

		if (strDay.length() == 1) {
			strDay = "0" + strDay;
		}

		// 현재 날짜 -1년
		String start_date = year + "-" + strMonth + "-" + strDay;

		// 현재 날짜
		String end_date = year + 1 + "-" + strMonth + "-" + strDay;

		if (type.equals("start")) {
			return start_date;
		} else {
			return end_date;
		}
	}

}
