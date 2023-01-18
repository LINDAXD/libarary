package com.library.controller.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.board.ArticleDTO;
import com.library.model.review.ReviewBoardDTO;
import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.review.ReviewBoardService;
import com.library.service.search.AladinApi;
import com.library.service.search.BookService;
import com.library.util.DateUtil;

@Controller
@RequestMapping("/search")
public class BookController {

	@Autowired
	private AladinApi api;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReviewBoardService reviewService;

	// 검색 도서 출력
	@GetMapping("/book")
	public String book(Model model, Criteria cri) {

		System.out.println("/search/book 진입");

		// BookDTO 리스트 선언
		List<BookDTO> list = new ArrayList<BookDTO>();

		// 검색어와 검색타입이 Null이 아닐 때 실행
		if (cri.getKeyword() != null && cri.getType() != null && cri.getKeyword() != "") {
			System.out.println("옵션 : " + cri.getType() + ", 검색 키 : " + cri.getKeyword() + ", 페이지 : " + cri.getPage());
			try {

				// api를 통해 검색어를 입력해 책 정보를 받아옴
				list = api.search(cri.getKeyword(), cri.getType(), cri.getPage(), cri.getAmount());

				// 검색 된 내용이 null이 아닐 때 수행
				if (!list.isEmpty()) {

					// 검색된 자료의 total을 가져옴
					model.addAttribute("total", list.get(0).getTotal());

					// 페이징 처리위한 함수
					ViewPage vp = new ViewPage(cri, list.get(0).getTotal());
					model.addAttribute("pageMaker", vp);

					// 검색 된 도서의 대출 중인 책의 권수를 가져옴
					for (BookDTO book : list) {
						book.setCount(bookService.count(book.getBook_isbn()));
					}

				}
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("빈 검색어 입력");
		}
		model.addAttribute("cri", cri);
		model.addAttribute("list", list);

		return "/search/sub1/book";
	}

	// 도서 상세페이지
	@GetMapping("/book-detail")
	public String book_detail(Model model, Criteria cri, @RequestParam String book_isbn) {

		// isbn이 null이 아닐 때
		if (book_isbn != null && book_isbn != "") {
			try {

				BookDTO book = api.search_detail(book_isbn);

				if (book.getBook_title() != null) {

					System.out.println("선택 책 제목 : " + book.getBook_title());
					model.addAttribute("book", book);

					// 대출 중인 도서의 수를 가져옴
					int count = bookService.count(book_isbn);
					count = 2 - count;
					model.addAttribute("count", count);
					
					// 후기
					List<ReviewBoardDTO> reviewList = reviewService.getListPage(cri,Long.parseLong(book_isbn));
					model.addAttribute("reviewList", reviewList);

//					int total = reviewList.getTotal(cri);
//					model.addAttribute("total", total);
//					ViewPage vp = new ViewPage(cri, total);
//					model.addAttribute("pageMaker", vp);

				} else {

					System.out.println("잘못된 값 입력");
					return "redirect:/search/book";

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("빈 검색어 입력");
			return "redirect:/search/book";

		}
		model.addAttribute("cri", cri);
		return "/search/sub1/book_detail";
	}

	// 책 대출
	@PostMapping("/loan")
	public String loan(Model model, Criteria cri, BookDTO book, @RequestParam String detail, Principal principal) {

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// id 세팅
		book.setUser_id(id);

		System.out.println("\n======================== 대출 신청 ========================");
		System.out.println("대출자 아이디 : " + book.getUser_id());
		System.out.println("대출 책 제목 : " + book.getBook_title());
		System.out.println("대출 책 ISBN : " + book.getBook_isbn());
		System.out.println("keyword : " + cri.getKeyword());
		System.out.println("========================================================\n");

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/search/book";
		}

		// 도서 대출 실행(대출하려는 도서의 대출 수가 2가 아닐 때)
		if (bookService.count(book.getBook_isbn()) != 2) {

			// 대출
			bookService.loan(book);

			// 대출자 대출 중 도서수 증가
			bookService.increase_count(book.getUser_id());

		} else {
			System.out.println("대출불가");
		}

		if (detail.equals("true")) {

			return "redirect:/search/best-book-detail?book_isbn=" + book.getBook_isbn();

		} else {
			return "redirect:/search/book-detail?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&type="
					+ cri.getType() + "&keyword=" + keyword + "&book_isbn=" + book.getBook_isbn();
		}

	}

	// 대출자 상태 체크
	@ResponseBody
	@PostMapping("/statusChk")
	public String statusChk(String book_isbn, Principal principal) throws Exception {

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		System.out.println(id);
		System.out.println("statusChk() 진입");

		// 대출하려는 회원의 대출 상태를 체크
		int result = bookService.statusCheck(id);

		if (result == 1) {

			// 대출하려는 회원이 대출 중인 도서인지 체크
			int loan_check = bookService.loan_check(id, book_isbn);

			if (loan_check == 1) {

				return "loan";

			} else {

				// 대출 중인 도서 상태 체크
				int count = bookService.count(book_isbn);

				if (count < 2) {

					// 대출 중인 해당 도서수가 2권 미만일 경우 success
					return "success";

				} else {
					return "fail";
				}

			}

		} else {

			return "fail";

		}
	}

	// 대출베스트 출력
	@GetMapping("/best-book")
	public String best_book(Model model, Criteria cri, DateDTO date) {

		System.out.println("best_book 진입");

		// year이 null 이면 현재 날짜 기준 year
		if (date.getYear() == null) {
			date.setYear(DateUtil.date("year"));
		}

		// month가 null 이면 현재 날짜 기준 month
		if (date.getMonth() == null) {
			date.setMonth(DateUtil.date("month"));
		}

		List<BookDTO> list = bookService.book_rank(date);

		for (BookDTO book : list) {
			book.setCount(bookService.count(book.getBook_isbn()));
		}

		model.addAttribute("list", list);

		// 년
		model.addAttribute("year", date.getYear());
		// 월
		model.addAttribute("month", date.getMonth());

		return "/search/sub2/best_book";

	}

	// 대출베스트 책 상세내용
	@GetMapping("/best-book-detail")
	public String best_book_detail(Model model, Criteria cri, DateDTO date, @RequestParam String book_isbn) {

		if (book_isbn != null && book_isbn != "") {

			try {

				BookDTO book = api.search_detail(book_isbn);

				if (book.getBook_title() != null) {

					System.out.println("선택 책 제목 : " + book.getBook_title());
					model.addAttribute("book", book);

					int count = bookService.count(book_isbn);
					count = 2 - count;
					model.addAttribute("count", count);

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

				} else {

					System.out.println("잘못된 값 입력");
					return "redirect:/search/book";

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("빈 검색어 입력");
			return "redirect:/search/book";

		}
		model.addAttribute("cri", cri);
		return "/search/sub2/best_book_detail";
	}
	

	
	
	

}
