package com.library.controller.board;

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

import com.library.model.board.AnswerBoardDTO;
import com.library.model.board.EnquiryBoardDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.board.AnswerBoardService;
import com.library.service.board.EnquiryBoardService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/board/*")
public class EnquiryBoardController {

	@Autowired
	private EnquiryBoardService eBoardService;

	@Autowired
	private AnswerBoardService aBoardService;

	/* 묻고답하기 게시판 */
	@GetMapping("/qnaBoardList")
	public String qnaBoardList(Model model, Criteria cri) {

		List<EnquiryBoardDTO> qnaBoardList = eBoardService.getListPage(cri);
		model.addAttribute("qnaBoardList", qnaBoardList);
		int total = eBoardService.getTotal(cri);
		model.addAttribute("total", total);
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/board/sub3/qnaBoardList";
	}

	/* 게시물 본문 */
	@GetMapping("/qnaBoardContent")
	public String qnaBoardContent(@RequestParam("enquiry_no") String uenquiry_no, Model model, Criteria cri,
			Principal principal) {

		Long enquiry_no = Long.parseLong(uenquiry_no);
		EnquiryBoardDTO dto = eBoardService.enquiryContent(enquiry_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 확인 가능 */
		if (writer_id.equals(login_id)) {
			eBoardService.updateView(enquiry_no);
			dto = eBoardService.enquiryContent(enquiry_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/board/sub3/qnaBoardContent";

		} else if (check == 1) {
			eBoardService.updateView(enquiry_no);
			dto = eBoardService.enquiryContent(enquiry_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/board/sub3/qnaBoardContent";
		}

		else {
			return "redirect:/accessError2";
		}

	}

	/* 등록 / 수정 / 삭제 */
	/* 게시물 작성 page */
	@GetMapping("/qnaBoardWrite")
	public String qnaBoardWrite() {
		return "/board/sub3/qnaBoardWrite";
	}

	/* 게시물 작성 */
	@PostMapping("/qnaBoardInsert")
	public String qnaBoardInsert(EnquiryBoardDTO dto) {

		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		dto.setWriter_id(id);
		
		// 특수문자 치환
		dto.setEnquiry_title(XssUtil.XssReplace(dto.getEnquiry_title()));
		
		eBoardService.enquiryBoardInsert(dto);

		return "redirect:/board/qnaBoardList";
	}

	/* 게시물 수정 page */
	@PostMapping("/qnaBoardEdit")
	public String qnaBoardEdit(@RequestParam("enquiry_no") String uenquiry_no, Model model, Criteria cri,
			Principal principal) {

		Long enquiry_no = Long.parseLong(uenquiry_no);
		EnquiryBoardDTO dto = eBoardService.enquiryContent(enquiry_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		/* 작성자와 로그인한 user가 같을 때 수정 가능 */
		if (writer_id.equals(login_id)) {
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/board/sub3/qnaBoardEdit";
		} else {
			return "redirect:/accessError2";
		}

	}

	/* 게시물 수정 */
	@PostMapping("/qnaBoardUpdate")
	public String qnaBoardUpdate(EnquiryBoardDTO dto, Criteria cri, Principal principal) {

		String keyword;

		Long enquiry_no = dto.getEnquiry_no();

		EnquiryBoardDTO dto2 = eBoardService.enquiryContent(enquiry_no);

		String writer_id = dto2.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID

		if (writer_id.equals(login_id)) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return "redirect:/board/qnaBoardList";
			}

			// 특수문자 치환
			dto.setEnquiry_title(XssUtil.XssReplace(dto.getEnquiry_title()));
			
			eBoardService.enquiryBoardUpdate(dto);

			return "redirect:/board/qnaBoardContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
					+ keyword + "&type=" + cri.getType() + "&enquiry_no=" + dto.getEnquiry_no();
			
		} else {
			return "redirect:/accessError2";
		}

	}

	/* 게시물 삭제 */
	@PostMapping("/qnaBoardDelete")
	public String qnaBoardDelete(Criteria cri, @RequestParam("enquiry_no") String uenquiry_no, Principal principal) {

		String keyword;
		Long enquiry_no = Long.parseLong(uenquiry_no);
		EnquiryBoardDTO dto = eBoardService.enquiryContent(enquiry_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName();// 로그인한 ID
		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 삭제 가능 */
		if (writer_id.equals(login_id)) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

				eBoardService.enquiryBoardDelete(enquiry_no);
				eBoardService.reset();
				return "redirect:/board/qnaBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
						+ keyword + "&type=" + cri.getType();
			} catch (UnsupportedEncodingException e) {
				return "redirect:/board/qnaBoardList";
			}

		} else if (check == 1) {

			try {
				keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

				eBoardService.enquiryBoardDelete(enquiry_no);
				eBoardService.reset();
				return "redirect:/board/qnaBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
						+ keyword + "&type=" + cri.getType();
			} catch (UnsupportedEncodingException e) {
				return "redirect:/board/qnaBoardList";
			}
		}

		else {
			return "redirect:/accessError2";
		}

	}

	/* 답글 */
	/* 답글 게시물 본문 / 조회수 */
	@GetMapping("/answerBoardContent")
	public String answerBoardContent(@RequestParam("answer_no") String uanswer_no, Model model, Criteria cri,
			Principal principal) {

		Long answer_no = Long.parseLong(uanswer_no);
		AnswerBoardDTO dto = aBoardService.answerContent(answer_no);

		String writer_id = dto.getWriter_id(); // 작성자 ID
		String login_id = principal.getName(); // 로그인한 ID
		int check = eBoardService.check_admin(login_id); // 관리자 계정 확인

		/* 작성자와 로그인한 user가 같거나, 관리자일 경우엔 게시물 확인 가능 */
		if (writer_id.equals(login_id)) {
			aBoardService.updateView(answer_no);
			dto = aBoardService.answerContent(answer_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/board/sub3/answerBoardContent";

		} else if (check == 1) {
			aBoardService.updateView(answer_no);
			dto = aBoardService.answerContent(answer_no);
			model.addAttribute("dto", dto);
			model.addAttribute("cri", cri);

			return "/board/sub3/answerBoardContent";

		} else {
			return "redirect:/accessError2";
		}

	}

	/* 답글 등록 page */
	@GetMapping("/answerBoardWrite")
	public String goAnswerBoardWrite(@RequestParam("enquiry_no") String uenquiry_no, Model model, Criteria cri) {

		Long enquiry_no = Long.parseLong(uenquiry_no);
		EnquiryBoardDTO dto = eBoardService.enquiryContent(enquiry_no);
		model.addAttribute("enquiry", dto);
		model.addAttribute("cri", cri);

		return "/board/sub3/answerBoardWrite";
	}

	/* 답글 등록 */
	@PostMapping("/answerBoardWrite")
	public String answerBoardWrite(AnswerBoardDTO dto, Criteria cri, Principal principal) {
		dto.setA_writer_id(principal.getName());
		
		// 특수문자 치환
		dto.setAnswer_title(XssUtil.XssReplace(dto.getAnswer_title()));
		
		aBoardService.answerBoardInsert(dto);

		return "redirect:/board/qnaBoardList";
	}

	/* 답글 수정 page */
	@GetMapping("/answerBoardEdit")
	public String answerBoardEdit(@RequestParam("answer_no") String uanswer_no, Model model, Criteria cri) {

		Long answer_no = Long.parseLong(uanswer_no);
		AnswerBoardDTO dto = aBoardService.answerContent(answer_no);
		model.addAttribute("answer", dto);
		model.addAttribute("cri", cri);

		return "/board/sub3/answerBoardEdit";
	}

	/* 게시물 수정 */
	@PostMapping("/answerBoardUpdate")
	public String answerBoardUpdate(AnswerBoardDTO dto, Criteria cri) {
		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		dto.setA_writer_id(id);

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/answerBoardEdit";
		}

		// 특수문자 치환
		dto.setAnswer_title(XssUtil.XssReplace(dto.getAnswer_title()));
		aBoardService.answerBoardUpdate(dto);

		return "redirect:/board/answerBoardContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
				+ keyword + "&type=" + cri.getType() + "&answer_no=" + dto.getAnswer_no();
	}

	/* 삭제 */
	@GetMapping("/answerBoardDelete")
	public String answerBoardDelete(Criteria cri, @RequestParam("answer_no") String uanswer_no) {
		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
			Long answer_no = Long.parseLong(uanswer_no);
			aBoardService.answerBoardDelete(answer_no);
//			eBoardService.reset();
			return "redirect:/board/qnaBoardList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
					+ keyword + "&type=" + cri.getType();
		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/qnaBoardList";
		}

	}

}
