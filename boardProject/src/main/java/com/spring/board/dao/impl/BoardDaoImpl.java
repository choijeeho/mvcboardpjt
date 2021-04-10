package com.spring.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.domain.Board;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{

	@Autowired
    private SqlSession sqlSession;
 
    public void setSqlSession(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    
    @Override
    public int getContentCnt(Map<String, Object> paramMap) {
        return sqlSession.selectOne("selectContentCnt", paramMap);
    }
    
    @Override
    public List<Board> getContentList(Map<String, Object> paramMap) {
        return sqlSession.selectList("selectContent", paramMap);
    }
    
}
