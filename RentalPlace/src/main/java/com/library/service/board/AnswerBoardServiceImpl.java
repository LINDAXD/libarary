package com.library.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.board.AnswerBoardMapper;
import com.library.model.board.AnswerBoardDTO;

@Service
public class AnswerBoardServiceImpl implements AnswerBoardService {

	@Autowired
	public AnswerBoardMapper aMapper;
	
	@Override
	public void answerBoardInsert(AnswerBoardDTO dto) {
		aMapper.answerBoardInsert(dto);
	}

	@Override
	public AnswerBoardDTO answerContent(Long answer_no) {
		return aMapper.answerContent(answer_no);
	}

	@Override
	public void updateView(Long answer_no) {
		aMapper.updateView(answer_no);
	}

	@Override
	public void answerBoardDelete(Long answer_no) {
		aMapper.answerBoardDelete(answer_no);
	}

	@Override
	public void answerBoardUpdate(AnswerBoardDTO dto) {
		aMapper.answerBoardUpdate(dto);
		
	}

}
