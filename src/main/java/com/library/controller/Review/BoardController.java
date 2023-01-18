/*
 * package com.library.controller.review;
 * 
 * import java.net.http.HttpHeaders; import java.util.ArrayList; import
 * java.util.HashMap; import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.json.simple.JSONArray; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * @Controller public class BoardController {
 * 
 * @Autowired private BoardService boardServiceImpl;
 * 
 *//**
	 * 댓글 등록(Ajax)
	 * 
	 * @param boardVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
/*
 * @RequestMapping(value="/board/addComment.do")
 * 
 * @ResponseBody public String ajax_addComment(@ModelAttribute("boardVO")
 * BoardVO boardVO, HttpServletRequest request) throws Exception{
 * 
 * HttpSession session = request.getSession(); LoginVO loginVO =
 * (LoginVO)session.getAttribute("loginVO");
 * 
 * try{
 * 
 * boardVO.setWriter(loginVO.getUser_id());
 * boardServiceImpl.addComment(boardVO);
 * 
 * } catch (Exception e){ e.printStackTrace(); }
 * 
 * return "success"; }
 * 
 *//**
	 * 게시물 댓글 불러오기(Ajax)
	 * 
	 * @param boardVO
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
		 * @RequestMapping(value="/board/commentList.do",
		 * produces="application/json; charset=utf8")
		 * 
		 * @ResponseBody public ResponseEntity
		 * ajax_commentList(@ModelAttribute("boardVO") BoardVO boardVO,
		 * HttpServletRequest request) throws Exception{
		 * 
		 * HttpHeaders responseHeaders = new HttpHeaders(); ArrayList<HashMap> hmlist =
		 * new ArrayList<HashMap>();
		 * 
		 * // 해당 게시물 댓글 List<BoardVO> commentVO =
		 * boardServiceImpl.selectBoardCommentByCode(boardVO);
		 * 
		 * if(commentVO.size() > 0){ for(int i=0; i<commentVO.size(); i++){ HashMap hm =
		 * new HashMap(); hm.put("c_code", commentVO.get(i).getC_code());
		 * hm.put("comment", commentVO.get(i).getComment()); hm.put("writer",
		 * commentVO.get(i).getWriter());
		 * 
		 * hmlist.add(hm); }
		 * 
		 * }
		 * 
		 * JSONArray json = new JSONArray(hmlist); return new
		 * ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
		 * 
		 * } }
		 * 
		 * 
		 * }
		 */