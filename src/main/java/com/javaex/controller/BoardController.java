package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService; //자동으로 연결해줘~
	
	//읽기
	@RequestMapping(value = "/board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no, HttpSession session) {
		System.out.println("[BoardController.read()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser);
		
		session.setAttribute("authUser", authUser);
		
		BoardVo boardVo = boardService.getBoard(no);
		System.out.println(boardVo);
		
		model.addAttribute("boardVo",boardVo);
		
		return "board/read";
	}
	
	//리스트
	@RequestMapping(value = "/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[BoardController.list()]");
		
		List<BoardVo> boardList = boardService.getList();
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	//삭제
	@RequestMapping(value = "/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no")int no) {
		System.out.println("[BoardController.delete()]");
		
		boardService.boardDelete(no);
		
		return "redirect:/board/list";
	}
	
	//글쓰기폼
	@RequestMapping(value =  "/board/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[BoardController.writeForm()]");
		
		
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value = "board/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(HttpSession session, @RequestParam("title")String title, @RequestParam("content")String content) {
		System.out.println("[BoardController.write()]");
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		System.out.println(userVo.getNo());
		
		boardService.boardWrite(userVo.getNo(),title,content);
		
		return "redirect:/board/list";
	}
	
	//수정폼
	@RequestMapping(value = "board/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model,@RequestParam("no")int no) {
		System.out.println("[BoardController.modifyForm()]");
		System.out.println(no);
		
		BoardVo boardVo = boardService.selectModifyInfo(no);
		model.addAttribute("boardVo",boardVo);
		
		return "board/modifyForm";
	}
	
	//수정
	@RequestMapping(value = "board/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.modify()]");
		
		boardService.boardModify(boardVo);
		
		return "redirect:/board/list";
	}
	
	
}
