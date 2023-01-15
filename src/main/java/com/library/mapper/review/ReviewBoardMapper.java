package com.library.mapper.review;

import com.library.model.review.ReviewBoardDTO;

public interface ReviewBoardMapper {

	// 회원가입
	public void regiester(ReviewBoardDTO who) throws Exception;
	
	
	public void reviewBoardInsert(ReviewBoardDTO dto);
	public void reviewBoardUpdate(ReviewBoardDTO dto);
	public void reviewBoardDelete(Long review_no);

	/* 본문 / 조회수 */
	public ReviewBoardDTO reviewContent(Long review_no);
	public void updateView(Long review_no);
}
