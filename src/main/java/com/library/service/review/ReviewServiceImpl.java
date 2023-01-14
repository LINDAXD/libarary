package com.library.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.review.ReviewMapper;
import com.library.model.review.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	// 회원가입
	@Override
	public void regiester(ReviewDTO review) throws Exception {

		reviewMapper.regiester(review);

	}

	}

