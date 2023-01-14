package com.library.model.board;

import lombok.Data;

@Data // Data = getter + setter + toString
public class NoticeAttachDTO {

	private String uuid;
	private String upload_path;
	private String file_name;
	private boolean file_type;
	private Long notice_no;
	
}
