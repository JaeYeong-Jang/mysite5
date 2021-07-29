package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession; //자동으로 연결해줘~
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		
		return sqlSession.update("board.updateHit", no);
	}
	
	//게시판 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		
		return sqlSession.selectOne("board.selectBoard",no);
		
	}
	
	public List<BoardVo> getBoardList() {
		System.out.println("[BoardDao.getBoardList()]");
		
		return sqlSession.selectList("board.selectList");
	}
	
	public void deleteBoard(int no) {
		System.out.println("[BoardDao.deleteBoard()]");
		
		sqlSession.delete("board.deleteBoard",no);
	}
	
	public void writeBoard(Map<String, Object> boardMap) {
		System.out.println("[BoardDao.writeBoard()]");
		
		sqlSession.insert("board.insertBoard", boardMap);
	}
	
	public BoardVo getModifyInfo(int no) {
		System.out.println("[BoardDao.getModifyInfo()]");
		
		return sqlSession.selectOne("board.getModifyInfo",no);
	}
	
	public void updateBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.updateBoard()]");
		
		sqlSession.update("board.updateBoard",boardVo);
	}
	
}
