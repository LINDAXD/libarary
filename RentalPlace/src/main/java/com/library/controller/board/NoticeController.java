package com.library.controller.board;

import java.io.File;
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

import com.library.model.board.NoticeAttachDTO;
import com.library.model.board.NoticeDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.board.NoticeService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/board/*")
public class NoticeController {
	
	public String UPLOAD_PATH = com.library.util.PathUtil.path() + File.separator + "notice" 
			+ File.separator; // 업로드 경로

	@Autowired
	private NoticeService noticeService;

	/* 공지사항 게시판 */
	@GetMapping("/noticeList")
	public String noticeList(Model model, Criteria cri) {

		List<NoticeDTO> noticeList = noticeService.getListPaging(cri);
		model.addAttribute("noticeList", noticeList);

		// 전체 게시물 수
		int total = noticeService.getTotal(cri);
		model.addAttribute("total", total);

		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/board/sub1/noticeList";
	}

	/* 게시물 조회 */
	@GetMapping("/noticeContent")
	public String noticeContent(Criteria cri, Model model, @RequestParam("notice_no") String n_notice_no) {

		Long notice_no = Long.parseLong(n_notice_no);

		// 조회수 증가
		noticeService.updateNoticeViews(notice_no);

		NoticeDTO noticeContent = noticeService.noticeContent(notice_no);
		List<NoticeDTO> posts = noticeService.getPrevAndNextPost(notice_no);

		model.addAttribute("noticeContent", noticeContent);
		model.addAttribute("cri", cri);
		model.addAttribute("posts", posts);

		return "/board/sub1/noticeContent";
	}

	/* 게시물 작성 */
	/* 게시물 작성 page로 이동 */
	@GetMapping("/insertNoticeForm")
	public String insertNoticeForm() {

		return "/board/sub1/insertNoticeForm";
	}

	/* 게시물 작성 process */
	@PostMapping("/insertNotice")
	public String insertNotice(NoticeDTO dto, RedirectAttributes rttr) {

		// 로그인 된 user_id 받아오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String id = userDetails.getUsername();

		// 첨부 파일이 있는 경우
		if (dto.getNoticeAttachList() != null) {
			dto.getNoticeAttachList().forEach(attach -> System.out.println(attach));
		}

		dto.setWriter_id(id);
		
		// 특수문자 치환
		dto.setNotice_title(XssUtil.XssReplace(dto.getNotice_title()));
		
		noticeService.insert(dto);

//		rttr.addFlashAttribute("result", dto.getNotice_no());

		return "redirect:/board/noticeList";
	}

	/* 첨부 파일 조회 */
	@GetMapping(value = "/getNoticeAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoticeAttachDTO>> getNoticeAttachList(Long notice_no) {

		List<NoticeAttachDTO> noticeAttachList = noticeService.getNoticeAttachList(notice_no);
		return new ResponseEntity<>(noticeAttachList, HttpStatus.OK);
	}

	/* 게시물 삭제 */
	@GetMapping("/deleteNotice")
	public String deleteNotice(Criteria cri, @RequestParam("notice_no") String n_notice_no, RedirectAttributes rttr,
			@RequestParam("uuid") String uuid, @RequestParam("file_type") String file_type) {

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");

			List<NoticeAttachDTO> attachList = noticeService.getNoticeAttachList(Long.parseLong(n_notice_no));
			deleteNoticeFiles(attachList);
			rttr.addFlashAttribute("result", "success");

			noticeFileDelete(uuid, file_type);

		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/noticeList";
		}

		Long notice_no = Long.parseLong(n_notice_no);
		noticeService.delete(notice_no);
		noticeService.reset();
		return "redirect:/board/noticeList?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword=" + keyword
				+ "&type=" + cri.getType();
	}

	/* 폴더 내 첨부 파일 삭제 */
	private void deleteNoticeFiles(List<NoticeAttachDTO> attachList) {

		if (attachList == null || attachList.size() == 0) {
			return;
		}

		attachList.forEach(attach -> {

			try {
				Path file = Paths
						.get(UPLOAD_PATH + attach.getUuid() + "_" + attach.getFile_name());
				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {
					Path thumbnail = Paths.get(UPLOAD_PATH + "\\s_" + attach.getUuid() + "_"
							+ attach.getFile_name());
					Files.delete(thumbnail);
				}
			} catch (Exception e) {
				System.out.println("파일 삭제 실패 ============= " + e.getMessage());
			}

		});

	}

	/* 폴더 내 파일 삭제 */
	public void noticeFileDelete(String uuid, String type) {

		System.out.println(uuid);
		System.out.println(type);

		String filePath = UPLOAD_PATH;
		File deleteFileName = new File(filePath + uuid);

		if (type.equals("image")) {
			String thumb = "s_" + uuid;
			File deleteThumbFileName = new File(filePath + thumb);
			deleteFileName.delete();
			deleteThumbFileName.delete();
			System.out.println("이미지 파일 삭제 완료");
		} else {
			deleteFileName.delete();
			System.out.println("파일 삭제 완료");
		}

	}

	/* 게시물 수정 page */
	@GetMapping("/updateNoticeForm")
	public String updateNoticeForm(Criteria cri, Model model, @RequestParam("notice_no") String n_notice_no) {

		Long notice_no = Long.parseLong(n_notice_no);

		NoticeDTO noticeContent = noticeService.noticeContent(notice_no);
		model.addAttribute("noticeContent", noticeContent);
		model.addAttribute("cri", cri);

		return "/board/sub1/updateNoticeForm";
	}

	/* 게시물 수정 process */
	@PostMapping("/updateNotice")
	public String updateNotice(Criteria cri, NoticeDTO dto, RedirectAttributes rttr) {

		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/board/noticeList";
		}

		// 특수문자 치환
		dto.setNotice_title(XssUtil.XssReplace(dto.getNotice_title()));
		
		noticeService.update(dto);

		return "redirect:/board/noticeContent?amount=" + cri.getAmount() + "&page=" + cri.getPage() + "&keyword="
				+ keyword + "&type=" + cri.getType() + "&notice_no=" + dto.getNotice_no();
	}

}
