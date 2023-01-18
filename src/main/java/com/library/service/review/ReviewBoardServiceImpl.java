package com.library.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.review.ReviewBoardMapper;
import com.library.model.review.ReviewBoardDTO;
import com.library.page.Criteria;

@Service
public class ReviewBoardServiceImpl implements ReviewBoardService {

	@Autowired
	public ReviewBoardMapper eMapper;
	

	/* 게시물 본문 / 조회수 */
	@Override
	public ReviewBoardDTO reviewContent(Long review_no) {
		return eMapper.reviewContent(review_no);
	}

	@Override
	public void updateView(Long review_no) {
		eMapper.updateView(review_no);
		
	}
	
	
	
	/* 등록 / 수정 / 삭제 */
	@Override
	public void reviewBoardInsert(ReviewBoardDTO dto) {
		eMapper.reviewBoardInsert(dto);
	}

	@Override
	public void reviewBoardUpdate(ReviewBoardDTO dto) {
		eMapper.reviewBoardUpdate(dto);
	}
	
	@Override
	public void reviewBoardDelete(Long review_no) {
		eMapper.reviewBoardDelete(review_no);
	}

	@Override
	public String id_duplicate(String review_no) {
		return eMapper.id_duplicate(review_no);
	}


	/* 게시판(페이징) / 총 게시물 / 게시판 정렬 */
	@Override
	public List<ReviewBoardDTO> getListPage(Criteria cri,Long book_isbn) {
		System.out.println("BOOK_ISBN = ");
		System.out.println(book_isbn);
		return eMapper.getListPage(cri, book_isbn);
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
