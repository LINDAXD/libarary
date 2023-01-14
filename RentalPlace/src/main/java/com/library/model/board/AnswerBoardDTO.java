package com.library.model.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AnswerBoardDTO {
	
	private Long answer_no;
	private Long enquiry_no;
	private String answer_title;
	private String answer_content;
	private String a_writer_id;
	private String a_writer_name;
	private int answer_hits;
	private Timestamp answer_reg_date;
	private String writer_id;
	
}
