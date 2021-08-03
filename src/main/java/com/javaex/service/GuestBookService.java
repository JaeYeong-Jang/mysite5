package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestBookDao;
	
	//방명록 리스트 가져오기
	public List<GuestBookVo> getList(){
		System.out.println("[GuestBookService.getList()]");
		
		return guestBookDao.getGuestBookList();
	}
	
	//방명록 글 저장
	public void writeGuest(GuestBookVo guestBookVo) {
		System.out.println("[GuestBookService.writeGuest()]");
		
		guestBookDao.insertGuestBook(guestBookVo);
	}
	
	//방명록 글 저장, 게시글 가져오기
	public GuestBookVo writeResultVo(GuestBookVo guestBookVo) {
		System.out.println("[GuestBookService.writeResultVo()]");
		//글 저장
		System.out.println(guestBookVo); // no없음
		int count = guestBookDao.insertGuestBookKey(guestBookVo);
		System.out.println(guestBookVo); // no있음
		
		int no = guestBookVo.getNo(); // 방금 저장한 글 번호
		
		//글 가져오기
		GuestBookVo resultVo = guestBookDao.selectGuestBook(no);
		
		return resultVo;
	}
	
}
