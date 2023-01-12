package com.library.model.admin;

import lombok.Data;

@Data // Data = getter + setter + toString
public class BannerDTO {

	private String user_id;
	private String path;
	private String reg_date;
}
