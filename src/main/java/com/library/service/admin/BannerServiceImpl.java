package com.library.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.admin.BannerMapper;
import com.library.model.admin.BannerDTO;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public List<BannerDTO> banner_list() {
		// TODO Auto-generated method stub
		return bannerMapper.banner_list();
	}

	@Override
	public void banner_del(BannerDTO banner) {
		bannerMapper.banner_del(banner);
	}

	@Override
	public void banner_add(BannerDTO banner) {
		bannerMapper.banner_add(banner);
	}
	
}
