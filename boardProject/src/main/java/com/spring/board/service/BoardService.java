package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.domain.Board;

public interface BoardService {

	int getContentCnt(Map<String, Object> paramMap);
    
    List<Board> getContentList(Map<String, Object> paramMap);
}
