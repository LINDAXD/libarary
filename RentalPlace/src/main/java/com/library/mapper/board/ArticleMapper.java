package com.library.mapper.board;

import java.util.List;

import com.library.model.board.ArticleDTO;
import com.library.page.Criteria;

public interface ArticleMapper {
		
	public List<ArticleDTO> list_all();
	
	public ArticleDTO articleContent(Long article_no);
	
	// 게시물 상세보기(이전글, 다음글)
	public ArticleDTO articlePreContent(Long article_no);
	public ArticleDTO articleNextContent(Long article_no);

	public void articleInsert(ArticleDTO dto);

	public void articleDelete(Long article_no);
	
	public boolean articleUpdate(ArticleDTO dto);
	
	public void articleViewsCount(Long article_no);

	public List<ArticleDTO> getListPaging(Criteria cri);

	public int getTotal(Criteria cri);

	public void reset();

	
}
