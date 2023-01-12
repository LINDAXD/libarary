package com.library.controller.mylib;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.model.mylib.ReadingRoomDTO;
import com.library.service.mylib.ReadingRoomService;
import com.library.service.mylib.ReservationRoomService;

@Controller
@RequestMapping("/mylib/*")
public class ReservationRoomController {
	
	@Autowired
	private ReservationRoomService resService;
	
	@Autowired 
	private ReadingRoomService roomService;
	
	@GetMapping("/reservationRoomPage")
	public String reservationRoomPage(Model model, Principal principal) {
		
		/* 로그인 된 ID */
		String user_id = principal.getName();
		model.addAttribute("login_id", user_id);
		
		/* 열람실1 현황 */
		int rd1Room_usingSeat = resService.rd1Room_usingSeat();
		model.addAttribute("rd1Room_usingSeat", rd1Room_usingSeat);
		int rd1Room_usedSeat = resService.rd1Room_usedSeat();
		model.addAttribute("rd1Room_usedSeat", rd1Room_usedSeat);
		
		
		/* 열람실2 현황 */
		int rd2Room_usingSeat = resService.rd2Room_usingSeat();
		model.addAttribute("rd2Room_usingSeat", rd2Room_usingSeat);
		int rd2Room_usedSeat = resService.rd2Room_usedSeat();
		model.addAttribute("rd2Room_usedSeat", rd2Room_usedSeat);
		
		
		/* 노트북실 현황 */
		int nbRoom_usingSeat = resService.nbRoom_usingSeat();
		model.addAttribute("nbRoom_usingSeat", nbRoom_usingSeat);
		int nbRoom_usedSeat = resService.nbRoom_usedSeat();
		model.addAttribute("nbRoom_usedSeat", nbRoom_usedSeat);
		
		return "/mylib/sub3/reservationRoomPage";
	}
	
	@GetMapping("/myReservationInfo")
	public String myReservationInfo(Model model, Principal principal) {
		/* 로그인 된 ID */
		String user_id = principal.getName();
		model.addAttribute("login_id", user_id);

		ReadingRoomDTO room_info = roomService.mySeatInfo(user_id);
		
		if(room_info == null) {
			return "/mylib/sub3/myReservationInfo";
		}else {
			Date now = new Date();
			room_info.setDiff_time(room_info.getCheckout_time().getTime() - now.getTime());
			model.addAttribute("nbRoom_info", room_info);
		}
		
		return "/mylib/sub3/myReservationInfo";
	}

}
