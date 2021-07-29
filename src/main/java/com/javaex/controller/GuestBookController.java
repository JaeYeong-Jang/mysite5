package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService; //자동으로 연결해줘~
	
	@RequestMapping(value = "/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GuestBookController.list()]");
		
		List<GuestBookVo> guestBookList = guestBookService.getList();
		
		model.addAttribute("guestBookList",guestBookList);
		
		return "guestbook/addList";
	}
	
}
