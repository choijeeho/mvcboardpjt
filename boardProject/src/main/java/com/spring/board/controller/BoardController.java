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


        //��ȸ �Ϸ��� ������
        int startPage = (paramMap.get("startPage")!=null?Integer.parseInt(paramMap.get("startPage").toString()):1);
        //���������� ������ ����Ʈ ��
        int visiblePages = (paramMap.get("visiblePages")!=null?Integer.parseInt(paramMap.get("visiblePages").toString()):10);
        //�ϴ� ��ü �Ǽ��� �����´�.
        int totalCnt = boardService.getContentCnt(paramMap);
 
 
        //�Ʒ� 1,2�� ���� ���߿����� class�� ���ش�. (���⼭�� ���ظ� ���� ���� ����)
        //1.�ϴ� ������ �׺���̼ǿ��� ������ ����Ʈ ���� ���Ѵ�.
        BigDecimal decimal1 = new BigDecimal(totalCnt);
        BigDecimal decimal2 = new BigDecimal(visiblePages);
        BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);
 
        int startLimitPage = 0;
        //2.mysql limit ������ ���ϱ� ���� ���
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
 
        //jsp ���� ������ ���� ����
        model.addAttribute("startPage", startPage+"");//���� ������      
        model.addAttribute("totalCnt", totalCnt);//��ü �Խù���
        model.addAttribute("totalPage", totalPage);//������ �׺���̼ǿ� ������ ����Ʈ ��
        model.addAttribute("boardList", boardService.getContentList(paramMap));//�˻�
 
        return "board";
	}
	
}
