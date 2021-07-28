package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	//fields
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		System.out.println(userVo);
		
		return sqlSession.selectOne("user.selectUser",userVo);
		
	}
	
	public void insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		System.out.println(userVo);
		
		sqlSession.insert("user.insertUser",userVo);
	}
	
}
