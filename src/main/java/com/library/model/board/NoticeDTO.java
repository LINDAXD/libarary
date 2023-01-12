package com.library.model.board;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data // Data = getter + setter + toString
public class NoticeDTO {

	private Long notice_no;
	private String notice_title;
	private String notice_content;
	private String writer_id;
	private String writer_name;
	private Timestamp notice_reg_date;
	private int notice_views;
	private String notice_img;
	
	private List<NoticeAttachDTO> noticeAttachList;

	
}
