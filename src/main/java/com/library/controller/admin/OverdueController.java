package com.library.controller.admin;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.search.BookDTO;
import com.library.service.admin.OverdueService;

@Controller
@RequestMapping("/admin")
public class OverdueController {

	@Autowired
	private OverdueService overdueService;

	@Autowired
	private JavaMailSender mailSender; // 이메일 전송 bean

	// 연체 중 리스트 출력 (get)
	@GetMapping("/overdue-list")
	public String overdue_list(Model model) {

		System.out.println("overdue_list");

		List<BookDTO> overdue_list = overdueService.overdue_list();

		for (BookDTO book : overdue_list) {

			book.setLoan_date(book.getLoan_date().substring(0, 10));
			if (book.getReturn_date() != null) {

				book.setReturn_date(book.getReturn_date().substring(0, 10));
			}

			book.setReturn_period(book.getReturn_period().substring(0, 10));
		}

		model.addAttribute("overdue_list", overdue_list);

		int total = overdueService.get_total();
		model.addAttribute("total", total);


		return "/admin/sub2/overdue_list";

	}

	// 연체 자 메일 전송
	@GetMapping("/send-mail")
	public String send_mail(HttpServletRequest request, Model model) throws Exception {

		String from = "library.raon@gmail.com";
		String to[] = request.getParameterValues("user_email");
		String title = "라온 도서관 : 연체 도서 안내";
		String content = "라온 도서관을 이용해주셔서 감사합니다." + "<br><br>" + "현재 연체된 도서가 있으니 반납 바랍니다." + "<br><br>";
		

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

		return "redirect:/admin/overdue-list";

	}

}
