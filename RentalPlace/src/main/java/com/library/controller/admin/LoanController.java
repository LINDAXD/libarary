package com.library.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.admin.LoanService;

@Controller
@RequestMapping("/admin")
public class LoanController {

	@Autowired
	private LoanService loanService;

	// 대출 중 리스트 출력 (get)
	@GetMapping("/loan-list")
	public String loan_list(Model model, Criteria cri) {

		System.out.println("loan_list 진입");

		List<BookDTO> loan_list = loanService.loan_list_paging(cri);

		for (BookDTO book : loan_list) {

			book.setLoan_date(book.getLoan_date().substring(0, 10));
			if (book.getReturn_date() != null) {

				book.setReturn_date(book.getReturn_date().substring(0, 10));
			}

			book.setReturn_period(book.getReturn_period().substring(0, 10));
		}

		model.addAttribute("loan_list", loan_list);

		int total = loanService.get_total(cri);
		model.addAttribute("total", total);

		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/admin/sub2/loan_list";

	}

	// 도서 반납 (get)
	@GetMapping("/return-book")
	public String return_book(BookDTO book, Model model, Criteria cri) {

		System.out.println("return_book 진입");

		// 도서 반납 처리
		int result = loanService.return_book(book);

		if (result == 1) {
			// 도서 반납 시 회원 대출 중 도서 수 감소
			loanService.decrease_count(book);

			// 연체 도서인지 확인
			int date = loanService.search_overdue(book.getLoan_no());

			// 연체도서 일 경우
			if (date < 0) {
				
				date *= -1;

				// 연체 일 업데이트
				loanService.update_user_overdue(book.getUser_id(), date);
			}
		}

		int amount = cri.getAmount();
		int page = cri.getPage();
		String type = cri.getType();
		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/admin/loan-list";
		}

		return "redirect:/admin/loan-list?amount=" + amount + "&page=" + page + "&type=" + type + "&keyword=" + keyword;

	}

}
