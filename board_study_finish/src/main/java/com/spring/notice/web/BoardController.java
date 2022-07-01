package com.spring.notice.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.notice.service.BoardService;
import com.spring.notice.vo.BoardVO;

@Controller

public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//파일 다운
	@RequestMapping (value="fileDown", method=RequestMethod.GET)
	public void fileDown(BoardVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boardService.fileDown(vo, request, response);
	}
	
	//글쓰기 화면으로 이동
	@RequestMapping("insertView.do")
	public String writeBoard() throws Exception{
		return "/board/board_write";
	}
	
	//글 등록
	@RequestMapping("insertBoard.do") 
	public void insertBoard(HttpServletResponse response, BoardVO vo) throws Exception{
		System.out.println("글 등록 처리 " + vo);
		boardService.insertBoard(response, vo);
		//return "redirect:boardList.do";
	}
	
	//글 목록
	@RequestMapping("boardList.do")
	public String getBoardList(BoardVO vo, Model model) throws Exception{
		System.out.println("글 목록 처리");
		Map<String, Object> result = new HashMap<String, Object>();
		result = boardService.getBoardList(vo);
		model.addAttribute("result",result);
		return "/board/board_list";
	}
	
	//글 상세
	@RequestMapping("getBoard.do")
	public String getBoard(BoardVO vo, Model model) throws Exception{
		Map<String, Object> result = boardService.getBoard(vo);
		model.addAttribute("result", result);
		return "/board/board_view";
	}
	
	
	//글수정 화면으로 이동
	@RequestMapping("modifyView.do")
	public String updateBoard(BoardVO vo, Model model) throws Exception{
		Map<String, Object> result = boardService.readBoard(vo);
		model.addAttribute("result", result);
		return "board/board_modify";
	} 
	
	//글 수정
	@RequestMapping("modifyBoard.do")
	public void modifyBoard(HttpServletResponse response, BoardVO vo) throws Exception{
		System.out.println("글 수정 처리"+ vo);
		boardService.modifyBoard(response, vo);
		//return "redirect:getBoard.do?num="+vo.getNum();
	}
	
	
	//글 삭제 화면으로 이동
	@RequestMapping("deleteView.do")
	public String deleteView(BoardVO vo, Model model) throws Exception{
		Map<String, Object> result = boardService.readBoard(vo);
		model.addAttribute("result", result);
		return "/board/board_delete";
	}
	
	//글 삭제
	@RequestMapping("deleteBoard.do")
	public void deleteBoard(HttpServletResponse response, BoardVO vo) throws Exception {
		System.out.println("글 삭제 처리>>");
		boardService.deleteBoard(response, vo);
		//return "redirect:boardList.do";
	}
	
}
