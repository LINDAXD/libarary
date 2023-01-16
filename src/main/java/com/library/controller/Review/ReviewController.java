package com.library.controller.review;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.model.review.ReviewAnswerBoardMapperDTO;
import com.library.model.review.ReviewBoardDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.review.ReviewAnswerBoardService;
import com.library.service.review.ReviewBoardService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/review/*")
public class ReviewController {

	@Autowired
	private ReviewBoardService eBoardService;

	@Autowired
	private ReviewAnswerBoardService aBoardService;

	/* 묻고답하기 게시판 */
	@GetMapping("/reviewBoardList")
	public String reviewBoardList(Model model, Criteria cri) {

		List<ReviewBoardDTO> reviewBoardList = eBoardService.getListPage(cri);
		model.addAttribute("reviewBoardList", reviewBoardList);
		int total = eBoardService.getTotal(cri);
		model.addAttribute("total", total);
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/review/reviewBoardList";
	}

	/* 게시물 본문 */
	@GetMapping("/reviewBoardContent")
	public String reviewBoardContent(@RequestParam("review_no") String ureview_no, Model model, Criteria cri,
			Principal principal) {

		Long review_no = Long.parseLong(ureview_no);
		ReviewBoardDTO dto = eBoardService.reviewContent(review_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 확인 가능 */
		if (writer_id.equals(login_id)) {
			eBoardService.updateView(review_no);
			dto = eBoardService.reviewContent(review_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/review/reviewBoardContent";

		} else if (check == 1) {
			eBoardService.updateView(review_no);
			dto = eBoardService.reviewContent(review_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/review/reviewBoardContent";
		}

		else {
			return "redirect:/accessError2";
		}

	}

	/* 등록 / 수정 / 삭제 */
	/* 게시물 작성 page */
	@GetMapping("/reviewBoardWrite")
	public String reviewBoardWrite() {
		return "/review/reviewBoardWrite";
	}

	/* 게시물 작성 */
	@PostMapping("/reviewBoardInsert")
	public String reviewBoardInsert(ReviewBoardDTO dto) {

		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		dto.setWriter_id(id);
		
		eBoardService.reviewBoardInsert(dto);

		return "redirect:/review/reviewBoardList";
	}

	/* 게시물 수정 page */
	@PostMapping("/reviewBoardEdit")
	public String reviewBoardEdit(@RequestParam("review_no") String ureview_no, Model model, Criteria cri,
			Principal principal) {

		Long review_no = Long.parseLong(ureview_no);
		ReviewBoardDTO dto = eBoardService.reviewContent(review_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		/* 작성자와 로그인한 user가 같을 때 수정 가능 */
		if (writer_id.equals(login_id)) {
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/review/reviewBoardEdit";
		} else {
			return "redirect:/accessError2";
		}

	}

	/* 게시물 수정 */
	@PostMapping("/reviewBoardUpdate")
	public String reviewBoardUpdate(ReviewBoardDTO dto, Criteria cri, Principal principal) {

		String keyword;

		Long review_no = dto.getReview_no();

		ReviewBoardDTO dto2 = eBoardService.reviewContent(review_no);

		String writer_id = dto2.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		if (writer_id.equals(login_id)) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return "redirect:/review/reviewBoardList";
			}

			
			eBoardService.reviewBoardUpdate(dto);

			return "redirect:/review/reviewBoardContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
					+ keyword + "&type=" + cri.getType() + "&review_no=" + dto.getReview_no();
			
		} else {
			return "redirect:/accessError2";
		}

	}

	/* 게시물 삭제 */
	@PostMapping("/reviewBoardDelete")
	public String reviewBoardDelete(Criteria cri, @RequestParam("review_no") String ureview_no, Principal principal) {

		String keyword;
		Long review_no = Long.parseLong(ureview_no);
		ReviewBoardDTO dto = eBoardService.reviewContent(review_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID
		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 삭제 가능 */
		if (writer_id.equals(login_id)) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

				eBoardService.reviewBoardDelete(review_no);
				eBoardService.reset();
				return "redirect:/review/reviewBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
						+ keyword + "&type=" + cri.getType();
			} catch (UnsupportedEncodingException e) {
				return "redirect:/review/reviewBoardList";
			}

		} else if (check == 1) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

				eBoardService.reviewBoardDelete(review_no);
				eBoardService.reset();
				return "redirect:/review/reviewBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
						+ keyword + "&type=" + cri.getType();
			} catch (UnsupportedEncodingException e) {
				return "redirect:/review/reviewBoardList";
			}
		}

		else {
			return "redirect:/accessError2";
		}

	}

	/* 답글 */
	/* 답글 게시물 본문 / 조회수 */
	@GetMapping("/reviewanswerBoardContent")
	public String reviewanswerBoardContent(@RequestParam("reviewanswer_no") String ureviewanswer_no, Model model, Criteria cri,
			Principal principal) {

		Long reviewanswer_no = Long.parseLong(ureviewanswer_no);
		ReviewAnswerBoardMapperDTO dto = aBoardService.reviewanswerContent(reviewanswer_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName(); // 로그인한 ID
		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 확인 가능 */
		if (writer_id.equals(login_id)) {
			aBoardService.updateView(reviewanswer_no);
			dto = aBoardService.reviewanswerContent(reviewanswer_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/review/reviewanswerBoardContent";

		} else if (check == 1) {
			aBoardService.updateView(reviewanswer_no);
			dto = aBoardService.reviewanswerContent(reviewanswer_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/review/reviewanswerBoardContent";

		} else {
			return "redirect:/accessError2";
		}

	}

	/* 답글 등록 page */
	@GetMapping("/reviewanswerBoardWrite")
	public String goreviewanswerBoardWrite(@RequestParam("review_no") String ureview_no, Model model, Criteria cri) {

		Long review_no = Long.parseLong(ureview_no);
		ReviewBoardDTO dto = eBoardService.reviewContent(review_no);
		model.addAttribute("review", dto);
		model.addAttribute("cri", cri);

		return "/review/reviewanswerBoardWrite";
	}

	/* 답글 등록 */
	@PostMapping("/reviewanswerBoardWrite")
	public String reviewanswerBoardWrite(ReviewAnswerBoardMapperDTO dto, Criteria cri, Principal principal) {
		dto.setA_writer_id(principal.getName());
		
		
		aBoardService.reviewanswerBoardInsert(dto);

		return "redirect:/review/reviewBoardList";
	}

	/* 답글 수정 page */
	@GetMapping("/reviewanswerBoardEdit")
	public String reviewanswerBoardEdit(@RequestParam("reviewanswer_no") String ureviewanswer_no, Model model, Criteria cri) {

		Long reviewanswer_no = Long.parseLong(ureviewanswer_no);
		ReviewAnswerBoardMapperDTO dto = aBoardService.reviewanswerContent(reviewanswer_no);
		model.addAttribute("reviewanswer", dto);
		model.addAttribute("cri", cri);

		return "/review/reviewanswerBoardEdit";
	}

	/* 게시물 수정 */
	@PostMapping("/reviewanswerBoardUpdate")
	public String reviewanswerBoardUpdate(ReviewAnswerBoardMapperDTO dto, Criteria cri) {
		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		dto.setA_writer_id(id);

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/review/reviewanswerBoardEdit";
		}


		aBoardService.reviewanswerBoardUpdate(dto);

		return "redirect:/review/reviewanswerBoardContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
				+ keyword + "&type=" + cri.getType() + "&reviewanswer_no=" + dto.getReviewanswer_no();
	}

	/* 삭제 */
	@GetMapping("/reviewanswerBoardDelete")
	public String reviewanswerBoardDelete(Criteria cri, @RequestParam("reviewanswer_no") String ureviewanswer_no) {
		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
			Long reviewanswer_no = Long.parseLong(ureviewanswer_no);
			aBoardService.reviewanswerBoardDelete(reviewanswer_no);
//			eBoardService.reset();
			return "redirect:/review/reviewBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
					+ keyword + "&type=" + cri.getType();
		} catch (UnsupportedEncodingException e) {
			return "redirect:/review/reviewBoardList";
		}

	}

}
