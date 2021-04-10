package com.spring.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.domain.Board;
import com.spring.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardDao")
    private BoardDao boardDao;
 
	@Override
	public int getContentCnt(Map<String, Object> paramMap) {
	        return boardDao.getContentCnt(paramMap);
    }
 
    @Override
    public List<Board> getContentList(Map<String, Object> paramMap) {
        return boardDao.getContentList(paramMap);
    }
}
