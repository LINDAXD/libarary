package com.library.controller.search;

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

import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.search.AladinApi;
import com.library.service.search.BookService;
import com.library.service.search.RecommendService;
import com.library.util.DateUtil;

@Controller
@RequestMapping("/search")
public class RecommendController {

	@Autowired
	private AladinApi api;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private BookService bookService;

	// 추천 도서 출력
	@GetMapping("/recommend-book")
	public String recommend_book(Model model, Criteria cri, DateDTO date) {

		System.out.println("recommend_book 진입");

		// year이 null 이면 현재 날짜 기준 year
		if (date.getYear() == null) {
			date.setYear(DateUtil.date("year"));
		}

		// month가 null 이면 현재 날짜 기준 month
		if (date.getMonth() == null) {
			date.setMonth(DateUtil.date("month"));
		}

		// BookDTO 리스트 출력
		List<BookDTO> list = recommendService.recommend_list(cri, date);
		model.addAttribute("list", list);

		// 추천 도서 권수
		int total = recommendService.get_total(date);

		// 페이징
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		// 년
		model.addAttribute("year", date.getYear());
		// 월
		model.addAttribute("month", date.getMonth());

		return "/search/sub3/recommend_book";
	}

	// 도서 상세페이지
	@GetMapping("/recommend-book-detail")
	public String recommned_book_detail(Model model, Criteria cri, @RequestParam String book_isbn, String year,
			String month) {

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
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "/search/sub3/recommend_book_detail";
	}

	// 추천 도서 팝업
	@GetMapping("/regist-book")
	public String regist_book(Model model, Criteria cri) {

		System.out.println("/regist-book 진입");

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

		return "/search/sub3/regist_book";
	}

	// 추천도서 등록
	@PostMapping("regist")
	public String regist(BookDTO book, Principal principal) {
		System.out.println("regist 진입");
		
		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// 추천도서 등록
		recommendService.regist_book(id, book);
		return "redirect:/search/regist-book";
	}
	
	// 추촌도서 삭제
	@PostMapping("delete-rec")
	public String delete_rec(@RequestParam String book_isbn, DateDTO date) {
		recommendService.delete_book(book_isbn, date);
		return "redirect:/search/recommend-book";
	}

}
