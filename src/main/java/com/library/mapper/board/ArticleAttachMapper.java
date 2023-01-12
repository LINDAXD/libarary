package com.library.mapper.board;

import java.util.List;

import com.library.model.board.ArticleAttachDTO;

public interface ArticleAttachMapper {
	
	public void insert(ArticleAttachDTO dto);
	
	public void delete(String uuid);
	
	public List<ArticleAttachDTO> findByArticle_no(Long article_no);
	
	public void deleteAll(Long article_no);


}
