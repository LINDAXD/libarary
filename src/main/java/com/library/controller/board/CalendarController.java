package com.library.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.board.CalendarDTO;
import com.library.service.board.CalendarService;

@Controller
@RequestMapping("/board")
public class CalendarController {
	
	@Autowired
	private CalendarService service;

	@GetMapping("/calendar")
	public String calendar() {
		
		return "/board/sub5/calendar";
	}
	
	@ResponseBody
	@GetMapping("/calendar_list")
	public List<CalendarDTO> calendarList() {
		
		List<CalendarDTO> list = service.list_all();
		
		return list;
	}

}
