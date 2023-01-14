package com.library.model.mylib;

import lombok.Data;

@Data
public class HopeDTO {
	
	// 희망 도서 번호
	private int hope_no;

	// 희망 도서 신청자 아이디
	private String user_id;

	// 희망 도서 명
	private String book_title;

	// 희망 도서 저자
	private String book_author;

	// 희망 도서 isbn
	private String book_isbn;

	// 희망 도서 출간일
	private String book_pubDate;

	// 희망 도서 출판사
	private String book_publisher;

	// 희망 도서 가격
	private String book_price;
	
	// 비고
	private String note;
	
	// 희망 도서 상태
	private String hope_status;
	
	// 취소 사유
	private String cancel_reason;
	
	// 희망 도서 등록 일시
	private String hope_reg_date;
	
}
