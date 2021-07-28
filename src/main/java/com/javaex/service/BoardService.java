package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao; //자동으로 연결해줘~
	
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard()]");
		
		//조회수 올리기
		boardDao.updateHit(no);
		//게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);
		
		//복합작업은 서비스 측에서 수행하여 컨트롤러로 넘겨준다. 컨트롤러에서 수행하지 않음
		
		return boardVo;
	}
	
	public List<BoardVo> getList(){
		System.out.println("[BoardService.getList()]");
		
		return boardDao.getBoardList();
	}
	
	public void boardDelete(int no) {
		System.out.println("[BoardService.boardDelete()]");
		
		boardDao.deleteBoard(no);
	}
	
	public void boardWrite(int no, String title, String content) {
		System.out.println("[BoardService.boardWrite()]");
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("userNo", no);
		boardMap.put("title", title);
		boardMap.put("content", content);
		
		boardDao.writeBoard(boardMap);
	}
	
}
