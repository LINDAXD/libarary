package com.library.controller.mylib;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.mylib.ReadingRoomDTO;
import com.library.service.mylib.ReadingRoomService;

@Controller
@RequestMapping("/mylib/*")
public class ReadingRoomController {

	@Autowired
	private ReadingRoomService readingRoomService;

	// 1열람실 출력
	@GetMapping("/readingRoom")
	public String getSeatsList(Model model, Principal principal) {

		List<ReadingRoomDTO> seatsList = readingRoomService.getReadingRoom1SeatsList();
		model.addAttribute("seatsList", seatsList);

		String user_id = principal.getName();
		model.addAttribute("login_id", user_id);

		ReadingRoomDTO mySeatInfo = readingRoomService.mySeatInfo(user_id);

		if (mySeatInfo == null) {
			return "/mylib/sub3/readingRoom";
		} else {
			Date now = new Date();
			mySeatInfo.setDiff_time(mySeatInfo.getCheckout_time().getTime() - now.getTime());
			model.addAttribute("mySeatInfo", mySeatInfo);
		}
		return "/mylib/sub3/readingRoom";

	}

	// 2열람실 출력
	@GetMapping("/readingRoom2")
	public String readingRoomRental(Model model, Principal principal) {

		List<ReadingRoomDTO> readingRoomlist = readingRoomService.getReadingRoom2SeatsList();
		model.addAttribute("readingRoomlist", readingRoomlist);

		// 로그인 된 아이디
		String user_id = principal.getName();

		model.addAttribute("login_id", user_id);

		ReadingRoomDTO room2_info = readingRoomService.mySeatInfo(user_id);

		// 제2열람실 좌석 정보
		if (room2_info == null) {

			return "/mylib/sub3/readingRoom2";

		} else {

			Date now = new Date();

			room2_info.setDiff_time(room2_info.getCheckout_time().getTime() - now.getTime());

			model.addAttribute("room2_info", room2_info);
		}
		return "/mylib/sub3/readingRoom2";
		// 컨트롤러에서 서비스단으로 넘긴다.(모델이라는 객체 이용하면 뷰단으로 쉽게 빼낼 수 있음)
	}
	
	
	/* 전체 좌석 출력(NotebookRoom) | 로그인 된 아이디와 비교 */
	@GetMapping("/notebookRoom")
	public String notebookRoomList(Model model, Principal principal) {

		List<ReadingRoomDTO> notebookRoomlist = readingRoomService.getNbRoomSeatsList();
		model.addAttribute("notebookRoomlist", notebookRoomlist);

		/* 로그인 된 ID */
		String user_id = principal.getName();
		model.addAttribute("login_id", user_id);

		ReadingRoomDTO nbRoom_info = readingRoomService.mySeatInfo(user_id);
		
		if(nbRoom_info == null) {
			return "/mylib/sub3/notebookRoom";
		}else {
			Date now = new Date();
			nbRoom_info.setDiff_time(nbRoom_info.getCheckout_time().getTime() - now.getTime());
			model.addAttribute("nbRoom_info", nbRoom_info);
		}
		
		return "/mylib/sub3/notebookRoom";

	}

	// 좌석 예약
	@PostMapping("/bookingSeat")
	public String bookingSeat(ReadingRoomDTO dto, Principal principal) {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowTime = fmt.format(now);

		// 현재 시간
		int hours = Integer.parseInt(nowTime.substring(11, 13));

		// 현재 시간이 9~17시 일경우 예약
		if (hours > 8 && hours < 18) {
			String user_id = principal.getName();
			dto.setUser_id(user_id);

			readingRoomService.bookingSeat(dto);
		}

		if (dto.getSeat_no() < 55) {
			return "redirect:/mylib/readingRoom";
		} else if (dto.getSeat_no() > 96) {
			return "redirect:/mylib/notebookRoom";
		} else {
			return "redirect:/mylib/readingRoom2";
		}

	}

	// 열람실 좌석 상태 체크
	@ResponseBody
	@PostMapping("/seat_check")
	public String seat_check(@RequestParam String seat_no) {

		int result = readingRoomService.seat_check(seat_no);

		if (result == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 열람실 퇴실
	@PostMapping("/returnSeat")
	public String returnSeat(Principal principal) {

		String user_id = principal.getName();
		readingRoomService.returnSeat(user_id);

		return "redirect:/mylib/reservationRoomPage";
	}

	// 좌석 이동
	@PostMapping("/moveSeat")
	public String moveSeat(ReadingRoomDTO dto, Principal principal) {

		String user_id = principal.getName();
		dto.setUser_id(user_id);
		readingRoomService.returnSeat(user_id);
		readingRoomService.bookingSeat(dto);

		if (dto.getSeat_no() < 55) {
			return "redirect:/mylib/readingRoom";
		} else if (dto.getSeat_no() > 96) {
			return "redirect:/mylib/notebookRoom";
		} else {
			return "redirect:/mylib/readingRoom2";
		}
	}

	// 좌석 연장
	@PostMapping("/extendSeat")
	public String extendSeat(Principal principal) {

		String user_id = principal.getName();
		readingRoomService.extendSeat(user_id);

		return "redirect:/mylib/readingRoom";
	}

}
