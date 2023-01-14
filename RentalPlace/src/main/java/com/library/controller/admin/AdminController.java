package com.library.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.model.member.MemberDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private JavaMailSender mailSender; // 이메일 전송 bean

	// 멤버 리스트 출력 (get)
	@GetMapping("/member-list")
	public String member_list(Model model, Criteria cri) {

		System.out.println("member_list 진입");

//		List<MemberDTO> member_list = adminService.member_list();

		List<MemberDTO> member_list = adminService.list_paging(cri);

		for (MemberDTO m : member_list) {

			m.setUser_reg_date(m.getUser_reg_date().substring(0, 10));
		}

		model.addAttribute("member_list", member_list);

		int total = adminService.get_total(cri);
		model.addAttribute("total", total);

		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);

		return "/admin/sub1/member_list";

	}

	// 멤버 조회
	@GetMapping("/member-view")
	public String member_view(Model model, Criteria cri, @RequestParam String user_id) {

		MemberDTO member = adminService.member_view(user_id);

		member.setUser_reg_date(member.getUser_reg_date().substring(0, 10));

		model.addAttribute("member", member);
		model.addAttribute("cri", cri);

		return "/admin/sub1/member_view";
	}

	// 회원 수정 폼 진입
	@GetMapping("/member-modify")
	public String member_modifyGet(Model model, Criteria cri, @RequestParam String user_id) {

		MemberDTO member = adminService.member_view(user_id);

		member.setUser_reg_date(member.getUser_reg_date().substring(0, 10));

		model.addAttribute("member", member);
		model.addAttribute("cri", cri);

		return "/admin/sub1/member_modify";
	}

	// 회원 수정
	@PostMapping("/member-modify")
	public String member_modifyPost(Model model, Criteria cri, MemberDTO member) {

		adminService.member_modify(member);
		String keyword;
		int amount = cri.getAmount();
		int page = cri.getPage();
		String type = cri.getType();
		model.addAttribute("user_id", member.getUser_id());

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/admin/member-list";
		}

		return "redirect:/admin/member-view?amount=" + amount + "&page=" + page + "&type=" + type + "&keyword="
				+ keyword;
	}

	// 회원 탈퇴
	@GetMapping("/member-delete")
	public String member_delete(Criteria cri, @RequestParam String user_id, @RequestParam String user_email) {

		// 회원탈퇴
		adminService.member_delete(user_id);

		// 탈퇴 회원 테이블에 입력
		adminService.insert_secession(user_id, user_email);

		int amount = cri.getAmount();
		int page = cri.getPage();
		String type = cri.getType();
		String keyword;

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/admin/member-list";
		}

		return "redirect:/admin/member-list?amount=" + amount + "&page=" + page + "&type=" + type + "&keyword="
				+ keyword;
	}

	// 메일 발송
	@GetMapping("/mail")
	public String mailGET(Model model, Criteria cri, @RequestParam String user_id) {

		MemberDTO member = adminService.member_view(user_id);
		model.addAttribute("member", member);
		model.addAttribute("cri", cri);
		return "/admin/sub1/mail";
	}

	// 메일 발송
	@PostMapping("/mail")
	public String mailPOST(Model model, Criteria cri, MemberDTO member, @RequestParam String mail_title,
			@RequestParam String mail_content) {

		// 메일 발송
		sendMail(member.getUser_email(), mail_title, mail_content);
		
		String keyword;
		int amount = cri.getAmount();
		int page = cri.getPage();
		String type = cri.getType();
		model.addAttribute("user_id", member.getUser_id());

		try {
			keyword = URLEncoder.encode(cri.getKeyword(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "redirect:/admin/member-list";
		}

		return "redirect:/admin/member-view?amount=" + amount + "&page=" + page + "&type=" + type + "&keyword="
				+ keyword;
	}

	// 메일 전송 함수
	public void sendMail(String email, String mail_title, String mail_content) {

		System.out.println(email);
		System.out.println(mail_title);
		System.out.println(mail_content);
		String from = "library.raon@gmail.com";
		String to = email;
		String title = mail_title;
		String content = mail_content;

		// 메일 발송
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				mailHelper.setFrom(new InternetAddress(from, "라온도서관", "UTF-8"));
				mailHelper.setTo(to);
				mailHelper.setSubject(title);
				mailHelper.setText(content, true);

			}
		};

		try {
			mailSender.send(preparator);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
