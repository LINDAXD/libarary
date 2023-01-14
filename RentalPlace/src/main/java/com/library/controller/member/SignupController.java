package com.library.controller.member;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.member.MemberDTO;
import com.library.service.member.SignupService;
import com.library.util.XssUtil;

@Controller
@RequestMapping("/member")
public class SignupController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private JavaMailSender mailSender; // 이메일 전송 bean

	@Autowired
	private PasswordEncoder pwencoder; // 암호화 Encoder

	// 약관동의 페이지 진입 (get)
	@GetMapping("/signup-check")
	public String signupCheck() {

		System.out.println("약관 동의 페이지 진입");

		return "/member/sub2/signup_check";

	}
	
	// 회원가입 페이지 진입
	@PostMapping("/signup")
	public String signup() {

		System.out.println("회원가입 페이지 진입");

		return "/member/sub2/signup";

	}

	// 회원가입
	@PostMapping("/signup-success")
	public String signupSuccess(MemberDTO member) throws Exception {

		String encode_pw = pwencoder.encode(member.getUser_pw());
		member.setUser_pw(encode_pw);

		// 특수문자 치환
		member.setUser_id(XssUtil.XssReplace(member.getUser_id()));
		member.setUser_name(XssUtil.XssReplace(member.getUser_name()));
		member.setUser_email(XssUtil.XssReplace(member.getUser_email()));
		member.setUser_tel(XssUtil.XssReplace(member.getUser_tel()));
		member.setUser_zip(XssUtil.XssReplace(member.getUser_zip()));
		member.setUser_address(XssUtil.XssReplace(member.getUser_address()));
		member.setUser_address_detail(XssUtil.XssReplace(member.getUser_address_detail()));
		
		// 회원가입 service 쿼리 실행
		signupService.signUp(member);

		// 회원 권한 추가
		signupService.auth(member.getUser_id());

		System.out.println("회원가입 성공");

		return "redirect:/member/login";
	}

	// 아이디 중복검사
	@ResponseBody
	@PostMapping("/memberIdChk")
	public String memberIdChkPOST(String memberId) throws Exception {

		System.out.println("memberIdChk() 진입");

		int result = signupService.idCheck(memberId);

		// 탈퇴 회원 중 아이디 체크
		int result2 = signupService.secession_idCheck(memberId);

		if (result != 0 || result2 != 0) {

			return "fail";

		} else {

			return "success";

		}
	}

	// 메일 중복검사
	@ResponseBody
	@PostMapping("/mailCheck")
	public String mailCheckPOST(String email) throws Exception {

		System.out.println("mailcheckPost 진입");

		int result = signupService.mailCheck(email);

		// 탈퇴 회원 중 메일 체크
		int result2 = signupService.secession_mailCheck(email);

		if (result != 0 || result2 != 0) {

			return "fail";

		} else {

			return sendCode(email);

		}
	}

	// 이메일 인증코드 발송
	public String sendCode(String email) throws Exception {

		// view로부터 넘어온 데이터 확인
		System.out.println("이메일 데이터 전송 확인");
		System.out.println("이메일 : " + email);

		// 인증번호 난수 생성
		Random random = new Random();
		int checkNum = random.nextInt(899999) + 100000; // 100000 ~ 999999 범위의 난수 생성
		System.out.println("인증번호 : " + checkNum);

		String from = "library.raon@gmail.com";
		String to = email;
		String title = "라온 도서관 : 회원가입 인증";
		String content = "라온 도서관을 이용해주셔서 감사합니다." + "<br><br>" + "인증번호는 <b>" + checkNum + "</b> 입니다." + "<br><br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

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

		String num = Integer.toString(checkNum); // view단으로 보내기 위한 난수 String 파싱

		return num;

	}

}
