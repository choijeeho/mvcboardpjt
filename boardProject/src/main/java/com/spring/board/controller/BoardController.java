package com.spring.board.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.board.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	 @Autowired
	 BoardService boardService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(@RequestParam Map<String, Object> paramMap, Model model) {


        //조회 하려는 페이지
        int startPage = (paramMap.get("startPage")!=null?Integer.parseInt(paramMap.get("startPage").toString()):1);
        //한페이지에 보여줄 리스트 수
        int visiblePages = (paramMap.get("visiblePages")!=null?Integer.parseInt(paramMap.get("visiblePages").toString()):10);
        //일단 전체 건수를 가져온다.
        int totalCnt = boardService.getContentCnt(paramMap);
 
 
        //아래 1,2는 실제 개발에서는 class로 빼준다. (여기서는 이해를 위해 직접 적음)
        //1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
        BigDecimal decimal1 = new BigDecimal(totalCnt);
        BigDecimal decimal2 = new BigDecimal(visiblePages);
        BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);
 
        int startLimitPage = 0;
        //2.mysql limit 범위를 구하기 위해 계산
        if(startPage==1){
            startLimitPage = 0;
        }else{
            startLimitPage = (startPage-1)*visiblePages;
        }
 
        paramMap.put("start", startLimitPage);
 
        //MYSQL
        //paramMap.put("end", visiblePages);
 
        //ORACLE
        paramMap.put("end", startLimitPage+visiblePages);
 
        //jsp 에서 보여줄 정보 추출
        model.addAttribute("startPage", startPage+"");//현재 페이지      
        model.addAttribute("totalCnt", totalCnt);//전체 게시물수
        model.addAttribute("totalPage", totalPage);//페이지 네비게이션에 보여줄 리스트 수
        model.addAttribute("boardList", boardService.getContentList(paramMap));//검색
 
        return "board";
	}
	
}
