package com.library.model.search;

import lombok.Data;

@Data
public class BookDTO {
	// 검색된 도서 수
	private int total;

	// 대출 중 도서 수
	private int count;

	// 대출 베스트 도서 총 대출 수
	private int total_count;

	// 대출 번호
	private int loan_no;

	// 대출자 아이디
	private String user_id;

	// 대출자 이메일
	private String user_email;

	// 대출 도서 명
	private String book_title;

	// 대출 도서 저자
	private String book_author;

	// 대출 도서 isbn
	private String book_isbn;

	// 대출 도서 표지 링크
	private String book_cover;

	// 대출 도서 출간일
	private String book_pubDate;

	// 대출 도서 출판사
	private String book_publisher;

	// 대출 일
	private String loan_date;

	// 반납 일
	private String return_date;

	// 반납 예정 일
	private String return_period;

	// 도서 가격(AladinAPI에서 받아올 때)
	private int priceStandard;
	
	// 도서
	private String description;
	
	// 연체일
	private int overdue_date;
}
