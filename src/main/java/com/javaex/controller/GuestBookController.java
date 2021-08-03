package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	// 방명록 글 저장
		@RequestMapping(value = "/write")
		public String write(@ModelAttribute GuestBookVo guestBookVo) {
			System.out.println("[GuestBookController.write()]");

			guestBookService.writeGuest(guestBookVo);
			return "redirect:/guestbook/addList";
		}
	
	//ajax 방명록 메인 페이지
	@RequestMapping(value="/guestbook/ajaxMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxMain() {
		System.out.println("[GuestBookController.ajaxMain()]");
		
		return "guestbook/ajaxList";
	}
	
}
