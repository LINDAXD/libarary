package com.library.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.model.mylib.ReadingRoomDTO;
import com.library.page.Criteria;
import com.library.service.mylib.ReadingRoomService;

@Controller
@RequestMapping("/admin")
public class SeatController {

	@Autowired
	private ReadingRoomService readingRoomService;

	// 열람실 이용 중 출력
	@GetMapping("/seat-list")
	public String seat_list(Model model, Criteria cri) {
		
		List<ReadingRoomDTO> seat_list = readingRoomService.getReadingRoomAll();
		model.addAttribute("seat_list", seat_list);

		return "/admin/sub6/seat_list";
	}
	
	// 퇴실
	@PostMapping("/seat-return")
	public String seat_return(@RequestParam String user_id) {
		
		readingRoomService.returnSeat(user_id);
		
		return "redirect:/admin/seat-list";
	}

}
