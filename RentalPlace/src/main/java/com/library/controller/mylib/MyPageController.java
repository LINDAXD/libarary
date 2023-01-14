package com.library.controller.mylib;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.member.MemberDTO;
import com.library.service.mylib.MyPageService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/mylib")
public class MyPageController {

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private PasswordEncoder pwencoder; // 암호화 Encoder

	// 마이 페이지 진입 전 비밀번호 확인
	@GetMapping("/mypage")
	public String check() {
		return "/mylib/sub5/check";
	}

	@ResponseBody
	@PostMapping("/check-pw")
	public String checkPw(String user_pw, Principal principal) {
		String id = principal.getName();
		MemberDTO my = myPageService.my_view(id);

		// 입력한 비밀번호과 db에 있는 비밀번호가 같으면 탈퇴 수행
		if (pwencoder.matches(user_pw, my.getUser_pw())) {

			return "success";

		} else {

			return "fail";

		}
	}

	// 마이 페이지 진입
	@PostMapping("/mypage")
	public String mypage(Model model, Principal principal) {

		System.out.println("마이 페이지 진입");

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		MemberDTO my = myPageService.my_view(id);

		my.setUser_reg_date(my.getUser_reg_date().substring(0, 10));

		model.addAttribute("my", my);

		return "/mylib/sub5/mypage";

	}

	// 수정 페이지 진입
	@GetMapping("/modify")
	public String modifyGET(Model model, Principal principal) {
		System.out.println("수정 페이지 진입");

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		MemberDTO my = myPageService.my_view(id);

		my.setUser_reg_date(my.getUser_reg_date().substring(0, 10));

		model.addAttribute("my", my);

		return "/mylib/sub5/modify";

	}

	// 정보 수정
	@PostMapping("/modify")
	public String modifyPOST(MemberDTO member, Principal principal) {

		System.out.println("수정post 진입");

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		member.setUser_id(id);
		
		// 특수문자 치환
		member.setUser_name(XssUtil.XssReplace(member.getUser_name()));
		member.setUser_tel(XssUtil.XssReplace(member.getUser_tel()));
		member.setUser_email(XssUtil.XssReplace(member.getUser_email()));
		member.setUser_zip(XssUtil.XssReplace(member.getUser_zip()));
		member.setUser_address(XssUtil.XssReplace(member.getUser_address()));
		member.setUser_address_detail(XssUtil.XssReplace(member.getUser_address_detail()));
		
		myPageService.my_modify(member);

		return "redirect:/mylib/mypage";
	}

	// 탈퇴 페이지 진입
	@GetMapping("/secession")
	public String secessionGET() {

		System.out.println("탈퇴 페이지 진입");

		return "/mylib/sub5/secession";

	}

	// 탈퇴 가능 여부 체크
	@ResponseBody
	@PostMapping("/secession")
	public String secessionPOST(String user_pw, Principal principal, HttpServletRequest request) {

		HttpSession session = request.getSession();

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// 회원 정보 받아오기
		MemberDTO my = myPageService.my_view(id);

		// 대출 중 도서 수
		int count = my.getUser_book_count();

		// db에 있는 회원 비밀번호
		String db_pw = my.getUser_pw();

		// 미반납 도서가 있을 시
		if (count > 0) {

			return "book";

		} else {

			// 입력한 비밀번호과 db에 있는 비밀번호가 같으면 탈퇴 수행
			if (pwencoder.matches(user_pw, db_pw)) {

				// 회원 탈퇴
				myPageService.my_secession(id);

				// 탈퇴 회원 테이블에 입력
				myPageService.insert_secession(my);

				// 세션 초기화
				session.invalidate();

				return "success";

			} else {

				return "fail";

			}

		}

	}

	@GetMapping("/modify-pw")
	public String modifyPwGET() {

		return "/mylib/sub5/modify_pw";
	}

	@ResponseBody
	@PostMapping("/modify-pw")
	public String modifyPwPOST(String present_pw, String user_pw, Principal principal, HttpServletRequest request) {

		HttpSession session = request.getSession();

		// 로그인 된 user_id 받아오기
		String id = principal.getName();

		// 회원 정보 받아오기
		MemberDTO my = myPageService.my_view(id);

		// db에 있는 회원 비밀번호
		String db_pw = my.getUser_pw();

		// 입력한 현재 비밀번호과 db에 있는 비밀번호가 같으면 탈퇴 수행
		if (pwencoder.matches(present_pw, db_pw)) {

			// 비밀번호 암호화
			String encode_pw = pwencoder.encode(user_pw);

			// 비밀번호 변경
			myPageService.modify_pw(id, encode_pw);

			// 세션 초기화
			session.invalidate();

			return "success";

		} else {

			return "fail";

		}

	}

}
