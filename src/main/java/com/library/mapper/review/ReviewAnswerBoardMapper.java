package com.library.mapper.review;

import com.library.model.review.ReviewAnswerBoardMapperDTO;
import com.library.model.review.ReviewBoardDTO;

public interface ReviewAnswerBoardMapper {

	// 회원가입
	public void regiester(ReviewBoardDTO who) throws Exception;
	
	
	public void reviewanswerBoardInsert(ReviewAnswerBoardMapperDTO dto);
	public void reviewanswerBoardUpdate(ReviewAnswerBoardMapperDTO dto);
	public void reviewanswerBoardDelete(Long review_no);

	/* 본문 / 조회수 */
	public ReviewAnswerBoardMapperDTO reviewanswerContent(Long review_no);
	public void updateView(Long review_no);
}
