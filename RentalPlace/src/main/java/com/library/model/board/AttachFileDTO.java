package com.library.model.board;

import lombok.Data;

// 이미지인지 아닌지 판단용, uploadAjaxAction 에서 사용
@Data
public class AttachFileDTO {

	private String file_name;
	private String upload_path;
	private String uuid;
	private boolean image;
	
}
