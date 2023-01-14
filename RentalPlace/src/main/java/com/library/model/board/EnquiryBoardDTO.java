package com.library.model.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EnquiryBoardDTO {
	
	private Long enquiry_no;
	private String enquiry_title;
	private String enquiry_content;
	private String writer_id;
	private String writer_name;
	private int enquiry_hits;
	private Timestamp enquiry_reg_date;
	
	private AnswerBoardDTO answerList;

	private int count; // 관리자 계정 확인
}
