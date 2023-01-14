package com.library.model.board;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

// data 어노테이션은 get,set, toString 메서드 모두 가져올 수 있음
@Data 
public class ArticleDTO {
	
	private Long article_no;
	private String article_title;
	private String article_content;
	private String writer_id;
	private String writer_name;
	private Timestamp article_reg_date;
	private int article_views;
	
	// 파일업로드
	private List<ArticleAttachDTO> attachList;
		
}
