package com.library.controller.board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.model.board.ArticleAttachDTO;
import com.library.model.board.ArticleDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.board.ArticleService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/board/*")
public class ArticleController {

	public String UPLOAD_PATH = com.library.util.PathUtil.path() + File.separator + "article" + File.separator; // 업로드
																													// 경로

	@Autowired
	private ArticleService articleService;

	@GetMapping("/articleList")
	public String articleList(Model model, Criteria cri) {

		List<ArticleDTO> articleList = articleService.getListPaging(cri);
		model.addAttribute("articleList", articleList);

		int total = articleService.getTotal(cri);
		model.addAttribute("total", total);
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/board/sub4/articleList";

		// 컨트롤러에서 서비스단으로 넘긴다.(모델이라는 객체 이용하면 뷰단으로 쉽게 빼낼 수 있음)
		// db에 있는 모든 정보(개별의dto)를 담아야하기때문에 list사용
	}

	@GetMapping("/articleInsertForm")
	public String goArticleInsert() {

		return "/board/sub4/articleInsertForm";
	}

	@PostMapping("/articleInsertForm")
	public String articleInsert(ArticleDTO dto /* RedirectAttributes rttr */) throws IOException, Exception {

		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		if (dto.getAttachList() != null) {

			dto.getAttachList().forEach(attach -> System.out.println(attach));

		}

		dto.setWriter_id(id);
		
		// 특수문자 치환
		dto.setArticle_title(XssUtil.XssReplace(dto.getArticle_title()));
		
		articleService.articleInsert(dto);

		/* rttr.addFlashAttribute("result", dto.getArticle_no()); */

		return "redirect:/board/articleList";
	}

	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ArticleAttachDTO>> getAttachList(Long article_no) {

		List<ArticleAttachDTO> a = articleService.getAttachList(article_no);

		return new ResponseEntity<>(a, HttpStatus.OK);

	}

	@GetMapping("/articleContent")
	public String articleContent(Criteria cri, @RequestParam("article_no") String a_article_no, Model model) {

		Long article_no = Long.parseLong(a_article_no);
		articleService.articleViewsCount(article_no);
		ArticleDTO dto = articleService.articleContent(article_no);

		// 게시물 상세보기 이전글, 다음글
		ArticleDTO dtoPre = articleService.articlePreContent(article_no);
		ArticleDTO dtoNext = articleService.articleNextContent(article_no);

		List<ArticleDTO> articleList = articleService.getListPaging(cri);
		model.addAttribute("articleList", articleList);

		model.addAttribute("dto", dto);
		model.addAttribute("dtoPre", dtoPre);
		model.addAttribute("dtoNext", dtoNext);
		model.addAttribute("cri", cri);

		return "/board/sub4/articleContent";

		// 디비에 있는 한줄의 row만 필요하기에 dto객체를 여러개 만들 필요 없음.
	}

	@GetMapping("/articleDelete")
	public String articleDelete(Criteria cri, @RequestParam("article_no") String a_article_no,
			@RequestParam("uuid") String uuid, @RequestParam("thumb") String thumb, RedirectAttributes rttr) {

		String keyword;
		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

			List<ArticleAttachDTO> attachList = articleService.getAttachList(Long.parseLong(a_article_no));
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");

		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/articleList";
		}

		Long article_no = Long.parseLong(a_article_no); // 들어오는 스트링값이 롱값으로 변환됌

		articleService.articleDelete(article_no);

		articleService.reset();

		return "redirect:/board/articleList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
				+ keyword + "&type" + cri.getType(); // 리다이렉트로 새로고침된 값을 뿌린다.
	}

	// 게시글 삭제시 폴더에 들어있는 사진 및 파일 삭제하는 함수
	private void deleteFiles(List<ArticleAttachDTO> attachList) {

		if (attachList == null || attachList.size() == 0) {
			return;
		}

		System.out.println("attachList=============" + attachList);

		attachList.forEach(attach -> {
			try {
				Path file = Paths.get(UPLOAD_PATH + attach.getUuid() + "_" + attach.getFile_name());

				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {

					Path thumbNail = Paths.get(UPLOAD_PATH + "\\s_" + attach.getUuid() + "_" + attach.getFile_name());

					Files.delete(thumbNail);
				}

			} catch (Exception e) {

				System.out.println("파일삭제 실패=========" + e.getMessage());

			} // end catch
		});// end foreachd
	}

	@GetMapping("/articleModifyForm")
	public String modifyForm(@RequestParam("article_no") String a_article_no, Model model, Criteria cri) {

		Long article_no = Long.parseLong(a_article_no);
		ArticleDTO dto = articleService.articleContent(article_no);
		model.addAttribute("dto", dto);
		model.addAttribute("cri", cri);

		return "/board/sub4/articleModifyForm";

	}

	@PostMapping("/articleModify")
	public String userModify(ArticleDTO dto, Criteria cri) {

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/articleList";
		}
		
		// 특수문자 치환
		dto.setArticle_title(XssUtil.XssReplace(dto.getArticle_title()));
		articleService.articleUpdate(dto);

		return "redirect:/board/articleContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
				+ keyword + "&type" + cri.getType() + "&article_no=" + dto.getArticle_no(); // 리다이렉트할때는 위에 매핑주소를 따라간다.
	}

}
