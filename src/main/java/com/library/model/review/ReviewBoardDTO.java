package com.library.model.review;

import java.sql.Timestamp;

import com.library.model.board.AnswerBoardDTO;

import lombok.Data;

@Data
public class ReviewBoardDTO {

	// 후기
	private String review;
	
	private Long review_no;
	private String review_title;
	private String review_content;
	private String writer_id;
	private String writer_name;
	private int review_hits;
	private Timestamp review_reg_date;
	
	private AnswerBoardDTO answerList;

	private int count; // 관리자 계정 확인

}
