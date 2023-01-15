package com.library.model.review;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewAnswerBoardMapperDTO {
	
	private Long reviewanswer_no;
	private Long review_no;
	private String reviewanswer_title;
	private String reviewanswer_content;
	private String a_writer_id;
	private String a_writer_name;
	private int reviewanswer_hits;
	private Timestamp reviewanswer_reg_date;
	private String writer_id;
	
}
