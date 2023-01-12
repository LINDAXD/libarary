package com.library.mapper.board;

import java.util.List;

import com.library.model.board.EnquiryBoardDTO;
import com.library.page.Criteria;

public interface EnquiryBoardMapper {
	

	/* 게시물 본문 / 조회수 */
	public EnquiryBoardDTO enquiryContent(Long enquiry_no);
	public void updateView(Long enquiry_no);
	
	
	/* 등록 / 수정 / 삭제 */
	public void enquiryBoardInsert(EnquiryBoardDTO dto);
	public void enquiryBoardUpdate(EnquiryBoardDTO dto);
	public void enquiryBoardDelete(Long enquiry_no);
	public String id_duplicate(String enquiry_no);
	

	/* 게시판(페이징) / 총 게시물 / 게시판 정렬 */
	public List<EnquiryBoardDTO> getListPage(Criteria cri);
	public int getTotal(Criteria cri);
	public void reset();
	
	/* 관리자 계정 확인 */
	public int check_admin(String user_id);

}

