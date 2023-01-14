package com.library.controller.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.review.ReviewDTO;
import com.library.service.review.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	// 후기
	@PostMapping("/register")
	public String register(ReviewDTO review) throws Exception {

		// 
		review.setReview(review.getReview());
	
		// 후기service 쿼리 실행
		reviewService.regiester(review);


		System.out.println("후기 등록");

		return "redirect:/board/book_detail";
		
	}
	
	
	

	
	
	
/*	
	@GetMapping("/qnaBoardWrite")
	public String qnaBoardWrite() {
		return "/board/sub3/qnaBoardWrite";
	}

	 게시물 작성 
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
		return "redirect:/board/book_detail";*/
		
	}

