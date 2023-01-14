package com.library.service.board;

import com.library.model.board.AnswerBoardDTO;

public interface AnswerBoardService {
	
	/* 등록 / 수정 / 삭제 */
	public void answerBoardInsert(AnswerBoardDTO dto);
	public void answerBoardUpdate(AnswerBoardDTO dto);
	public void answerBoardDelete(Long answer_no);
	
	
	/* 본문 조회 */
	public AnswerBoardDTO answerContent(Long answer_no);
	/* 조회수 */
	public void updateView(Long answer_no);

	
}
