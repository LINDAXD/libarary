package com.library.service.review;

import com.library.model.review.ReviewAnswerBoardMapperDTO;

public interface ReviewAnswerBoardService {
	
	/* 등록 / 수정 / 삭제 */
	public void reviewanswerBoardInsert(ReviewAnswerBoardMapperDTO dto);
	public void reviewanswerBoardUpdate(ReviewAnswerBoardMapperDTO dto);
	public void reviewanswerBoardDelete(Long reviewanswer_no);
	
	
	/* 본문 조회 */
	public ReviewAnswerBoardMapperDTO reviewanswerContent(Long reviewanswer_no);
	/* 조회수 */
	public void updateView(Long reviewanswer_no);

	
}