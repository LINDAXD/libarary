package com.library.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.mapper.board.ArticleMapper;
import com.library.mapper.board.ArticleAttachMapper;
import com.library.model.board.ArticleAttachDTO;
import com.library.model.board.ArticleDTO;
import com.library.page.Criteria;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper mapper;
	
	@Autowired
	private ArticleAttachMapper attachMapper;
	

	@Override
	public List<ArticleDTO> list_all(){
		return mapper.list_all();
		
		//impl에서 xml로 넘긴다.(mapper 자바도 가서 만들어줘야함)
	}


	@Override
	public ArticleDTO articleContent(Long article_no) {
		return mapper.articleContent(article_no);
	}
	
	// 파일첨부
	@Transactional
	@Override
	public void articleInsert(ArticleDTO dto) {
		mapper.articleInsert(dto);
		
		if (dto.getAttachList() == null || dto.getAttachList().size() <= 0) {
			return;
		}
		
		dto.getAttachList().forEach(attach -> {

			attach.setArticle_no(dto.getArticle_no());
			attachMapper.insert(attach);
		});
		
	}
	
	@Transactional
	@Override
	public void articleDelete(Long article_no) {
		
		attachMapper.deleteAll(article_no);
		
		mapper.articleDelete(article_no);
		
	}
	
	

	@Override
	public boolean articleUpdate(ArticleDTO dto) {
			
		attachMapper.deleteAll(dto.getArticle_no());
		
		boolean modifyResult = mapper.articleUpdate(dto);
		
		if(modifyResult && dto.getAttachList() != null && dto.getAttachList().size() > 0) {
			dto.getAttachList().forEach(attach -> {
				
				attach.setArticle_no(dto.getArticle_no());
				attachMapper.insert(attach);
				
			});
		}
		
		return modifyResult;
		
	}
	
	@Override
	public void articleViewsCount(Long article_no) {
		mapper.articleViewsCount(article_no);
		
	}

	@Override
	public List<ArticleDTO> getListPaging(Criteria cri) {
		
		return mapper.getListPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return mapper.getTotal(cri);
	}

	@Override
	public void reset() {
		mapper.reset();
		
	}

	@Override
	public List<ArticleAttachDTO> getAttachList(Long article_no) {
				
		return attachMapper.findByArticle_no(article_no);
	}

	
	// 게시물 상세보기(이전글, 다음글)
	@Override
	public ArticleDTO articlePreContent(Long article_no) {
		
		return mapper.articlePreContent(article_no);
	}

	@Override
	public ArticleDTO articleNextContent(Long article_no) {

		return mapper.articleNextContent(article_no);
	}
	

}
