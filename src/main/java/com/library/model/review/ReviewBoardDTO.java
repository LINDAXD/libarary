package com.library.model.review;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewBoardDTO {

	// 후기
	private Long review_no; //게시글번호
	private String review_content;
	private String writer_id;
	private Long book_isbn;  //책번호
	private Timestamp review_reg_date;
	private int count; // 관리자 계정 확인

}
