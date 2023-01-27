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
      
   // 도서 구매 내역 출력
      @GetMapping("/book_buylist")
      public String book_buylist(Model model, Criteria cri, Principal principal) {

      
            
            System.out.println("buy_history 진입");
            if(principal == null) {
               return "/member/sub1/login";
            } else {
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
      }
   
      @GetMapping("/buybook")
      public String buy(BookDTO book, Principal principal) {
         
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
         System.out.println("========================================================\n");

         

         // 구매
         buyservice.buy(book); //insert into buy_history


         return "redirect:/buy/book_buy?book_isbn=" + book.getBook_isbn();
      }
      
      @GetMapping("/pre_buybook")
      public String pre_buy(BookDTO book,  Principal principal) {

         
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
         System.out.println("========================================================\n");

         

         // 구매(포장 추가)
         buyservice.pre_buy(book); //insert into buy_history

         return "redirect:/buy/book_buy?book_isbn=" + book.getBook_isbn();
      }
      
      @GetMapping("/cart_buybook")
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
}