package com.library.mapper.board;

import java.util.List;

import com.library.model.board.NoticeAttachDTO;

public interface NoticeAttachMapper {
	
	public void insert(NoticeAttachDTO dto);
	public void delete(String uuid);
	public List<NoticeAttachDTO> findByNotice_no(Long notice_no);
	public void deleteAllByNotice_no(Long notice_no);
	
}
