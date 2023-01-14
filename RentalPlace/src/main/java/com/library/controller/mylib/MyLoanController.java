package com.library.controller.mylib;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.search.BookDTO;
import com.library.service.mylib.MyLoanService;

@Controller
@RequestMapping("/mylib")
public class MyLoanController {

	@Autowired
	private MyLoanService myLoanService;

	// 대출 중 리스트 출력 (get)
	@GetMapping("/loan-list")
	public String my_loan_list(Model model, Principal principal) {

		System.out.println("my_loan_list 진입");

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// 회원 대출 중 리스트
		List<BookDTO> loan_list = myLoanService.loan_list_paging(id);

		for (BookDTO book : loan_list) {

			book.setLoan_date(book.getLoan_date().substring(0, 10));
			if (book.getReturn_date() != null) {

				book.setReturn_date(book.getReturn_date().substring(0, 10));
			}

			book.setReturn_period(book.getReturn_period().substring(0, 10));
		}

		// 대출 중 내역
		model.addAttribute("loan_list", loan_list);

		// 대출 내역 수
		int total = myLoanService.get_total(id);
		model.addAttribute("total", total);

		// 대출 연체 권수
		int overdue_count = myLoanService.get_overdue(id);
		model.addAttribute("overdue_count", overdue_count);

		// 회원 대출 정지일
		int overdue_date = myLoanService.get_overdue_date(id);

		if (overdue_date != 0) {
			model.addAttribute("overdue_date", date(overdue_date));
		} else {
			model.addAttribute("overdue_date", "해당없음");
		}

		return "/mylib/sub1/loan_list";

	}

	public String date(int overdue_date) {

		// 현재시간
		Calendar now = Calendar.getInstance();

		now.setTime(new Date());

		// 현재날짜에 연체일 더하기
		now.add(Calendar.DATE, overdue_date);

		// date형식을 yyyy-MM-dd로 설정
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		// 대출 정지 만기일
		String date = df.format(now.getTime());

		return date;
	}

}
