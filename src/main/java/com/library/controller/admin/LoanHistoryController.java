package com.library.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.admin.LoanHistoryService;

@Controller
@RequestMapping("/admin")
public class LoanHistoryController {

	@Autowired
	private LoanHistoryService service;

	// 대출 중 리스트 출력 (get)
	@GetMapping("/loan-history")
	public String loan_list(Model model, Criteria cri) {

		System.out.println("loan_history 진입");

		List<BookDTO> loan_history = service.loan_history(cri);

		for (BookDTO book : loan_history) {

			book.setLoan_date(book.getLoan_date().substring(0, 10));
			if (book.getReturn_date() != null) {

				book.setReturn_date(book.getReturn_date().substring(0, 10));
			}

			book.setReturn_period(book.getReturn_period().substring(0, 10));
		}

		model.addAttribute("loan_history", loan_history);

		int total = service.get_total(cri);
		model.addAttribute("total", total);

		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/admin/sub2/loan_history";

	}
}
