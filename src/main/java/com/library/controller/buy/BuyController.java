package com.library.controller.buy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.search.BookDTO;
import com.library.page.Criteria;
import com.library.page.ViewPage;
import com.library.service.buy.BuyService;
import com.library.service.buy.MyBuyHistoryService;
import com.library.service.search.AladinApi;
import com.library.service.search.BookService;

@Controller
@RequestMapping("/buy")
public class BuyController {

	@Autowired
	private AladinApi api;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBuyHistoryService service;
	
	@Autowired
	private BuyService buyservice;

	boolean i = false;
	// 도서 구매 출력
	@GetMapping("/book_buy")
	public String book_buy(Model model, @RequestParam String book_isbn) {

		System.out.println("book_buy 실행");
		BookDTO book = new BookDTO();
		
		
		List<BookDTO> list = new ArrayList<BookDTO>();

		// isbn이 null이 아닐 때
		if (book_isbn != null && book_isbn != "") {
			try {

				book = api.search_detail(book_isbn);

				if (book.getBook_title() != null) {

					System.out.println("선택 책 제목 : " + book.getBook_title());
					model.addAttribute("book", book);

					// 구매한 도서의 수를 가져옴
					int count = bookService.count(book_isbn);
					count = 2 - count;
					model.addAttribute("count", count);

				} else {

					System.out.println("잘못된 값 입력");
					return "redirect:/search/book";

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("빈 검색어 입력");
			return "redirect:/search/book";

		}
		model.addAttribute("book", book);

		return "/buy/book_buy";

	}
	
	
	@RequestMapping("/book_buy_api")
	@ResponseBody
	public String kakaopay() {
		
		try {

			
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "KakaoAK 7ab27b00537b3367f4963eaca8eed02f");
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setDoOutput(true);
			
			

				
			
			
			String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=test&quantity=1&total_amount=50000&tax_free_amount=0&approval_url=http://localhost:8092/success&fail_url=http://localhost:8092/fail&cancel_url=http://localhost:8092/cancel";
			OutputStream output = con.getOutputStream();
			DataOutputStream toput = new DataOutputStream(output);
			toput.writeBytes(parameter);
			toput.close();
			int result = con.getResponseCode();
			InputStream input;
			if(result == 200) {
				input = con.getInputStream();
				System.out.println(input);
			} else {
				input = con.getErrorStream();
			}
			
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(reader);
			

				return buffer.readLine();

		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return "{\"result\":\"NO\"}";
		
	}
	
	
	
	
/*	
	public String date(String type) {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR) - 1;
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String strMonth = Integer.toString(month);
		String strDay = Integer.toString(day);
		if (strMonth.length() == 1) {
			strMonth = "0" + strMonth;
		}
		if (strDay.length() == 1) {
			strDay = "0" + strDay;
		}
		// 현재 날짜 -1년
		String start_date = year + "-" + strMonth + "-" + strDay;
		// 현재 날짜
		String end_date = year + 1 + "-" + strMonth + "-" + strDay;
		if (type.equals("start")) {
			return start_date;
		} else {
			return end_date;
		}
	}

*/	
	
	// 도서 구매 내역 출력
		@GetMapping("/book_buylist")
		public String book_buylist(Model model, Criteria cri, Principal principal) {

		
				
				System.out.println("buy_history 진입");
				
				// 로그인 된 user_id 받아오기
				String id = principal.getName();

				// 회원의 구매 내역 받아오기
				List<BookDTO> buy_history = service.buy_history(id, cri);
				// 구매 내역
				model.addAttribute("buy_history", buy_history);
				// 대출 건수
				int total = service.get_total_buy(id);
				model.addAttribute("total", total);
				// 페이징 정보
				ViewPage vp = new ViewPage(cri, total);
				model.addAttribute("pageMaker", vp);

				
			return "/buy/book_buylist";

		}
	
		@GetMapping("/buybook")
		public String buy(BookDTO book, /* @RequestParam String detail, */ Principal principal) {

			
			// 로그인 된 user_id 받아오기
			String id = principal.getName();
			System.out.println("책이름: " + book.getBook_title());
			// id 세팅
			book.setUser_id(id);

			System.out.println("\n======================== 구매 ========================");
			System.out.println("아이디 : " + book.getUser_id());
			System.out.println("구매 책 제목 : " + book.getBook_title());
			System.out.println("구매한 책 ISBN : " + book.getBook_isbn());
			System.out.println("책 가격 : " + book.getPriceStandard());
			System.out.println("구매한 책 가격 : " + book.getResultpriceStandard());
			System.out.println("구매한 책 가격 : " + book.getBook_price());
			System.out.println("========================================================\n");

			

			// 구매
			buyservice.buy(book); //insert into buy_history

			/*
			 * if (detail.equals("true")) {
			 * 
			 * return "redirect:/search/best-book-detail?book_isbn=" + book.getBook_isbn();
			 * 
			 * } else {
			 * 
			 * return "redirect:/search/book-detail?amount=" + cri.getAmount() + "&page=" +
			 * cri.getPage() + "&type=" + cri.getType() + "&keyword=" + keyword +
			 * "&book_isbn=" + book.getBook_isbn();
			 * 
			 * }
			 */

			return "redirect:/buy/book_buy?book_isbn=" + book.getBook_isbn();
		}
		
		@ResponseBody
		@PostMapping("/buyChk")
		public String buyChk(String book_isbn, Principal principal) throws Exception {

			// 로그인 된 user_id 받아오기
			String id = principal.getName();

			


			// 대출하려는 회원이 대출 중인 도서인지 체크
			/* int buy_check = buyservice.buy_check(id, book_isbn); */
			int buy_check = 2;
			if (buy_check == 1) {

				return "alreadyBuy";

			} else {

				// 아직 좋아요 안한 책이라면 success 리턴
				return "success";

				} 
		}
	/*
		@PostMapping("/buybook")
		public String buy() {
			System.out.println("책 구매하기");
			
			return "/search/book";
		}
	
	*/
	
		/*
		 * @RequestMapping("/book_buy_result_api")
		 * 
		 * @ResponseBody public String kakaopayResult() { try { URL url = new
		 * URL("https://kapi.kakao.com/v1/payment/approve"); HttpsURLConnection con =
		 * (HttpsURLConnection) url.openConnection(); con.setRequestMethod("POST");
		 * con.setRequestProperty("Authorization",
		 * "KakaoAK 7ab27b00537b3367f4963eaca8eed02f");
		 * con.setRequestProperty("Content-type",
		 * "application/x-www-form-urlencoded;charset=utf-8"); con.setDoOutput(true);
		 * String parameter =
		 * "cid=TC0ONETIME&tid=T1234567890123456789&partner_order_id=partner_order_id&partner_user_id=partner_user_id&pg_token=xxxxxxxxxxxxxxxxxxxx";
		 * OutputStream output = con.getOutputStream(); DataOutputStream toput = new
		 * DataOutputStream(output); toput.writeBytes(parameter); toput.close();
		 * 
		 * int result = con.getResponseCode();
		 * 
		 * InputStream input; if(result == 200) { input = con.getInputStream(); } else {
		 * input = con.getErrorStream(); }
		 * 
		 * InputStreamReader reader = new InputStreamReader(input); BufferedReader
		 * buffer = new BufferedReader(reader); return buffer.readLine(); }
		 * catch(MalformedURLException e) { e.printStackTrace(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * 
		 * return "{\"result\":\"NO\"}";
		 * 
		 * }
		 */
		@ResponseBody
		@PostMapping("/test")
		public String likeChk(String book_isbn, Principal principal) throws Exception {

			// 로그인 된 user_id 받아오기
			String id = principal.getName();

			System.out.println(id);
			System.out.println("likeChk() 진입");


			// 대출하려는 회원이 대출 중인 도서인지 체크
			int buy_check = 2;

			if (buy_check == 1) {

				return "alreadyLike";

			} else {

				// 아직 좋아요 안한 책이라면 success 리턴
				return "success";

				} 
		}
}
