package com.library.service.board;

import java.util.List;

import com.library.model.board.NoticeAttachDTO;
import com.library.model.board.NoticeDTO;
import com.library.page.Criteria;

public interface NoticeService {

	/* 게시판 조회(페이징) */
	public List<NoticeDTO> getListPaging(Criteria cri);
	
	/* 총 게시물 수 */
	public int getTotal(Criteria cri);
	
	/* 조회수 업데이트 */
	public void updateNoticeViews(Long notice_no);
	
	/* 게시물 조회 */
	public NoticeDTO noticeContent(Long notice_no);
	
	/* 첨부 파일 조회 */
	public List<NoticeAttachDTO> getNoticeAttachList(Long notice_no);
	
	/* 이전글, 다음글 조회 */
	public List<NoticeDTO> getPrevAndNextPost(Long notice_no);
	
	/* 게시물 등록 */
	public void insert(NoticeDTO dto);
	
	/* 게시물 삭제 */
	public void delete(Long notice_no);
	
	/* 글 삭제 시 글 번호 정렬 */
	public void reset();
	
	/* 게시물 수정 */
	public boolean update(NoticeDTO dto);
	
	
}
