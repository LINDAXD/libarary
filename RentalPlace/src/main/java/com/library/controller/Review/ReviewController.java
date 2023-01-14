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

		return "redirect:/review/login";
	}


}

