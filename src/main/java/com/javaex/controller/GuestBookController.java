package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaex.service.GuestBookService;

@Controller
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService; //자동으로 연결해줘~
	
	
	
}
