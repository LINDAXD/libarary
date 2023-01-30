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

   // 도서 구매 출력
   @GetMapping("/book_buy")
   public String book_buy(Model model, @RequestParam String book_isbn) {

      System.out.println("book_buy 실행");
      BookDTO book = new BookDTO();
      
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
               // 잘못된 검색어 입력시 검색창으로 다시 이동
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
   
   @GetMapping("/book_buy_not")
   public String book_buy_not(Model model, @RequestParam String book_isbn, Principal principal) {
	   
	   System.out.println("book_buy_not 실행");
	   BookDTO book = new BookDTO();
	   String id = principal.getName();
	   List<BookDTO> buy_history = service.r_buy_history(id);
 	  
	   model.addAttribute("buy_history", buy_history);
	   System.out.println("이다" + buy_history + "이다");
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
				   // 잘못된 검색어 입력시 검색창으로 다시 이동
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
	   
	   return "/buy/book_buy_not";
	   
   }
   
   
   @RequestMapping("/book_buy_api")
   @ResponseBody
   public String kakaopay() {
      try {
         URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
         HttpsURLConnection con = (HttpsURLConnection) url.openConnection();  // URL 클래스로부터 주소를 얻어서 연결하는 역할
         con.setRequestMethod("POST"); // 통신방식 : POST
         con.setRequestProperty("Authorization", "KakaoAK 7ab27b00537b3367f4963eaca8eed02f"); // 카카오 디벨로퍼에서 받은 ADMIN키로 인증하는 역할
         con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 한글적용
         con.setDoOutput(true); // 서버에 전달할게 있기때문에 DoOutput(true)를 사용

         String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=test&quantity=1&total_amount=50000&tax_free_amount=0&approval_url=http://localhost:8092/success&fail_url=http://localhost:8092/fail&cancel_url=http://localhost:8092/cancel";
         // 카카오페이 결제에서 사용될 내용들
         
         OutputStream output = con.getOutputStream(); // 서버에 데이터를 줄 수 있게 연결하는 역할
         DataOutputStream toput = new DataOutputStream(output); // 데이터를 받아와서 전달해주는 역할
         
         // outputstram은 byte형식으로 전달해야 하기 때문에 형변환이 필요
         toput.writeBytes(parameter); // 파라미터 내용들을 자동으로 형 변환
         toput.close();
         
         int result = con.getResponseCode(); // 서버 결과 번호를 받아오는 역할
         InputStream input; // 서버의 데이터를 받아올 수 있게 하는 역할
         if(result == 200) {
            input = con.getInputStream();
            System.out.println(input);
         } else {
            input = con.getErrorStream();
         }
         InputStreamReader reader = new InputStreamReader(input); // 받아온 데이터를 읽을 수 있게 하는 역할
         BufferedReader buffer = new BufferedReader(reader); // 읽어온 데이터를 형변환 시키는 역할
            return buffer.readLine(); // 형변환된 데이터를 사이트에 출력

      } catch(MalformedURLException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
       return "{\"result\":\"NO\"}";
   }
      
   // 도서 구매 내역 출력
      @GetMapping("/book_buylist")
      public String book_buylist(Model model, Criteria cri, Principal principal) {

      
            
            System.out.println("buy_history 진입");
            
            // 로그인하지 않았을때 로그인창을 리턴받음
            if(principal == null) {
               return "/member/sub1/login";
            } else {
               // 로그인 된 user_id 받아오기
               String id = principal.getName();
               
               // 회원의 구매 내역 받아오기
               List<BookDTO> buy_history = service.buy_history(id, cri);
               // 구매 내역
               model.addAttribute("buy_history", buy_history);
               // 구매 건수
               int total = service.get_total_buy(id);
               model.addAttribute("total", total);
               // 페이징 정보
               ViewPage vp = new ViewPage(cri, total);
               model.addAttribute("pageMaker", vp);
   
               
            return "/buy/book_buylist";
            }
      }
   
      @PostMapping("/buybook")
      public String buy(BookDTO book, Principal principal) {
         
         // 로그인 된 user_id 받아오기
         String id = principal.getName();
         System.out.println("책이름: " + book.getBook_title());
         // id 세팅
         book.setUser_id(id);

         // 구매
         buyservice.buy(book); //insert into buy_history
         
         System.out.println("\n======================== 구매 ========================");
         System.out.println("아이디 : " + book.getUser_id());
         System.out.println("구매 책 제목 : " + book.getBook_title());
         System.out.println("구매한 책 ISBN : " + book.getBook_isbn());
         System.out.println("책 가격 : " + book.getPriceStandard());
         System.out.println("구매한 책 가격 : " + book.getResultpriceStandard());
         System.out.println("========================================================\n");

         
         return "redirect:/search/book-detail?book_isbn=" + book.getBook_isbn();
      }
      
      @PostMapping("/buybook_not")
      public String buy_not( BookDTO book, Principal principal) {
    	  
    	  // 로그인 된 user_id 받아오기
    	  String id = principal.getName();
    	  System.out.println("책이름: " + book.getBook_title());
    	  
    	  // id 세팅
    	  book.setUser_id(id);
    	  
    	  
    	  return "redirect:/search/book-detail?book_isbn=" + book.getBook_isbn();
      }
      
      @PostMapping("/pre_buybook")
      public String pre_buy(BookDTO book,  Principal principal) {

         
         // 로그인 된 user_id 받아오기
         String id = principal.getName();
         System.out.println("책이름: " + book.getBook_title());
         // id 세팅
         book.setUser_id(id);

         // 구매(포장 추가)
         buyservice.pre_buy(book); //insert into buy_history
         
         System.out.println("\n======================== 구매 ========================");
         System.out.println("아이디 : " + book.getUser_id());
         System.out.println("구매 책 제목 : " + book.getBook_title());
         System.out.println("구매한 책 ISBN : " + book.getBook_isbn());
         System.out.println("책 가격 : " + book.getPriceStandard());
         System.out.println("구매한 책 가격 : " + book.getResultpriceStandard());
         System.out.println("========================================================\n");

         return "redirect:/search/book-detail?book_isbn=" + book.getBook_isbn();
      }
      
      @PostMapping("/cart_buybook")
      public String cart_buy(BookDTO book,  Principal principal) {
         
         System.out.println("cart_buybook실행");
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
         System.out.println("========================================================\n");
         
         
         
         // 구매
         buyservice.cart_buy(book); //insert into buy_history
         //장바구니 구매
         buyservice.count_buy(book);
         buyservice.count_buy_update(book);

         
         return "redirect:/buy/book_buy_not?book_isbn=" + book.getBook_isbn();
      }

}