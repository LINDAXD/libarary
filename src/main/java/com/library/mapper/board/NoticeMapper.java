package com.library.mapper.board;

import java.util.List;

import com.library.model.board.NoticeDTO;
import com.library.page.Criteria;

public interface NoticeMapper {
	
	public List<NoticeDTO> getListPaging(Criteria cri);
	public int getTotal(Criteria cri);
	public NoticeDTO noticeContent(Long notice_no);
	public void updateViews(Long notice_no);
	public void insert(NoticeDTO dto);
	public void delete(Long notice_no);
	public void reset();
	public boolean update(NoticeDTO dto);
	public List<NoticeDTO> getPrevAndNextPost(Long notice_no);
	
	
	
}
