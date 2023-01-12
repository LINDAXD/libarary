package com.library.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.model.admin.BannerDTO;
import com.library.model.board.CalendarDTO;
import com.library.model.board.NoticeDTO;
import com.library.model.search.BookDTO;
import com.library.model.search.DateDTO;
import com.library.page.Criteria;
import com.library.service.admin.BannerService;
import com.library.service.board.CalendarService;
import com.library.service.board.NoticeService;
import com.library.service.mylib.ReservationRoomService;
import com.library.service.search.BookService;
import com.library.service.search.RecommendService;
import com.library.util.DateUtil;

@Controller
public class HomeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private ReservationRoomService resService;

	@Autowired
	private CalendarService calService;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BannerService bannerService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Criteria cri) {

		DateDTO date = new DateDTO();

		date.setYear(DateUtil.date("year"));
		date.setMonth(DateUtil.date("month"));
		
		// 년
		model.addAttribute("year", date.getYear());
		// 월
		model.addAttribute("month", date.getMonth());
		
		// 공지사항
		List<NoticeDTO> noticeList = noticeService.getListPaging(cri);
		model.addAttribute("noticeList", noticeList);

		/* 열람실1 현황 */
		int rd1Room_usingSeat = resService.rd1Room_usingSeat();
		model.addAttribute("rd1Room_usingSeat", rd1Room_usingSeat);

		/* 열람실2 현황 */
		int rd2Room_usingSeat = resService.rd2Room_usingSeat();
		model.addAttribute("rd2Room_usingSeat", rd2Room_usingSeat);

		/* 노트북실 현황 */
		int nbRoom_usingSeat = resService.nbRoom_usingSeat();
		model.addAttribute("nbRoom_usingSeat", nbRoom_usingSeat);

		// 휴관일 검색
		List<CalendarDTO> cal_list = calService.search_closed(date);

		for (CalendarDTO c : cal_list) {

			c.setStart(c.getStart().substring(8, 10));
		}

		model.addAttribute("cal_list", cal_list);

		// 추천도서
		List<BookDTO> rec_list = recommendService.recommend_list(cri, date);
		model.addAttribute("rec_list", rec_list);

		// 대출 베스트
		List<BookDTO> best_list = bookService.book_rank(date);
		model.addAttribute("best_list", best_list);
		
		// 배너 목록
		List<BannerDTO> banner_list = bannerService.banner_list();
		model.addAttribute("banner_list", banner_list);
		
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();

		// 세션 초기화
		session.invalidate();

		return "redirect:/";

	}

	@GetMapping("/accessError")
	public String accessDenied() {

		return "/error/accessError";

	}

	@GetMapping("/accessError2")
	public String accessDenied2() {

		return "/error/accessError2";

	}

}
