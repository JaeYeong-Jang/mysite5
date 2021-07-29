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
}
