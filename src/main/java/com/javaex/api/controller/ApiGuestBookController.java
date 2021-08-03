package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;

	//ajax 리스트 가져오기
	@ResponseBody //바디에 데이터만 넣어서 보내준다.
	@RequestMapping(value="api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestBookVo> List() {
		System.out.println("[ApiGuestBookController.List()]");
		
		List<GuestBookVo> guestbookList = guestBookService.getList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	//ajax 방명록 저장
	@ResponseBody // 제이슨!
	@RequestMapping(value="api/guestbook/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestBookVo write(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("[ApiGuestBookController.write()]");
		
		System.out.println(guestBookVo);
		
		GuestBookVo resultBookVo = guestBookService.writeResultVo(guestBookVo);
		
		return resultBookVo; // resultBookVo 를 json으로 넘겨준다. 알아서 변환해~
	}
	
}
