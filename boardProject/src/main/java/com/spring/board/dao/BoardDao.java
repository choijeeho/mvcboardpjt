package com.spring.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.board.domain.Board;

public interface BoardDao {

	  int getContentCnt(Map<String, Object> paramMap);
	    
	  List<Board> getContentList(Map<String, Object> paramMap);
	    
}
