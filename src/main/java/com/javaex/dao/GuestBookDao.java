package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getGuestBookList(){
		System.out.println("[GuestBookDao.getGuestBookList()]");
		
		return sqlSession.selectList("guestbook.selectGuestBookList");
		
	}
	
	public void insertGuestBook(GuestBookVo guestBookVo) {
		System.out.println("[GuestBookDao.insertGuestBook()]");
		
		sqlSession.insert("guestbook.insertGuestBook",guestBookVo);
	}
	
	//방명록 글 저장 --ajax
	public int insertGuestBookKey(GuestBookVo guestBookVo) {
		System.out.println("[guestBookDao.insertGuestBookKey()]");
		
		return sqlSession.insert("guestbook.insertGuestBookKey",guestBookVo);
	}
	
	//방명록 글 가져오기 --ajax
	public GuestBookVo selectGuestBook(int no) {
		System.out.println("guestBookDao.selectGuestBook()");
		
		return sqlSession.selectOne("guestbook.selectGuestBook",no);
	}
	
	
}
