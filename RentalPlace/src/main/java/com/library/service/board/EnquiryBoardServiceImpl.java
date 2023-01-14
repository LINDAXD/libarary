package com.library.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.board.EnquiryBoardMapper;
import com.library.model.board.EnquiryBoardDTO;
import com.library.page.Criteria;

@Service
public class EnquiryBoardServiceImpl implements EnquiryBoardService {

	@Autowired
	public EnquiryBoardMapper eMapper;
	

	/* 게시물 본문 / 조회수 */
	@Override
	public EnquiryBoardDTO enquiryContent(Long enquiry_no) {
		return eMapper.enquiryContent(enquiry_no);
	}

	@Override
	public void updateView(Long enquiry_no) {
		eMapper.updateView(enquiry_no);
		
	}
	
	
	
	/* 등록 / 수정 / 삭제 */
	@Override
	public void enquiryBoardInsert(EnquiryBoardDTO dto) {
		eMapper.enquiryBoardInsert(dto);
	}

	@Override
	public void enquiryBoardUpdate(EnquiryBoardDTO dto) {
		eMapper.enquiryBoardUpdate(dto);
	}
	
	@Override
	public void enquiryBoardDelete(Long enquiry_no) {
		eMapper.enquiryBoardDelete(enquiry_no);
	}

	@Override
	public String id_duplicate(String enquiry_no) {
		return eMapper.id_duplicate(enquiry_no);
	}


	/* 게시판(페이징) / 총 게시물 / 게시판 정렬 */
	@Override
	public List<EnquiryBoardDTO> getListPage(Criteria cri) {
		return eMapper.getListPage(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return eMapper.getTotal(cri);
	}

	@Override
	public void reset() {
		eMapper.reset();
		
	}

	/* 관리자 계정 확인 */
	@Override
	public int check_admin(String user_id) {
		return eMapper.check_admin(user_id);
	}

	

}
