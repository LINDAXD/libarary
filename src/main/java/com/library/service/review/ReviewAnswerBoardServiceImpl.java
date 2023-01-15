package com.library.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.review.ReviewAnswerBoardMapper;
import com.library.model.review.ReviewAnswerBoardMapperDTO;

@Service
public class ReviewAnswerBoardServiceImpl implements ReviewAnswerBoardService {

	@Autowired
	public ReviewAnswerBoardMapper aMapper;
	
	@Override
	public void reviewanswerBoardInsert(ReviewAnswerBoardMapperDTO dto) {
		aMapper.reviewanswerBoardInsert(dto);
	}

	@Override
	public ReviewAnswerBoardMapperDTO reviewanswerContent(Long reviewanswer_no) {
		return aMapper.answerContent(reviewanswer_no);
	}

	@Override
	public void updateView(Long reviewanswer_no) {
		aMapper.updateView(reviewanswer_no);
	}

	@Override
	public void reviewanswerBoardDelete(Long reviewanswer_no) {
		aMapper.answerBoardDelete(answer_no);
	}

	@Override
	public void reviewanswerBoardUpdate(ReviewAnswerBoardMapperDTO dto) {
		aMapper.reviewanswerBoardUpdate(dto);
		
	}

}
