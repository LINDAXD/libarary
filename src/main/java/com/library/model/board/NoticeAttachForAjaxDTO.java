package com.library.model.board;

import lombok.Data;

@Data // Data = getter + setter + toString
public class NoticeAttachForAjaxDTO {

	private String uuid;
	private String upload_path;
	private String file_name;
	private boolean image;
	
}
