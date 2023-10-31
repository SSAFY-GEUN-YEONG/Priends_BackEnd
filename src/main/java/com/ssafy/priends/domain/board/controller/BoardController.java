package com.ssafy.priends.domain.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.service.BoardService;
import com.ssafy.priends.domain.member.dto.MemberDto;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
	@GetMapping("/write")
	public String writePost() {
		return "board/write";
	}
	
	
	@PostMapping("/write")
	public String writePost(BoardDto board, HttpSession session) throws Exception {
		MemberDto member = (MemberDto) session.getAttribute("userinfo");
		board.setUser_id(member.getId());
		
		boardService.writePost(board);
		
		return "redirect:/board/list";
	}

	
	@GetMapping("/list")
	public ModelAndView listPost(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDto> list = boardService.listPost(map);
		mav.addObject("posts", list);
		mav.setViewName("board/list");
		return mav;
	}
//
//	@GetMapping("/view")
//	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		logger.debug("view articleNo : {}", articleNo);
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		boardService.updateHit(articleNo);
//		model.addAttribute("article", boardDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/view";
//	}
//
//	@GetMapping("/modify")
//	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		logger.debug("modify articleNo : {}", articleNo);
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		model.addAttribute("article", boardDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/modify";
//	}
//
//	@PostMapping("/modify")
//	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		logger.debug("modify boardDto : {}", boardDto);
//		boardService.modifyArticle(boardDto);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
//	}
//
//	@GetMapping("/delete")
//	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		logger.debug("delete articleNo : {}", articleNo);
////		boardService.deleteArticle(articleNo, servletContext.getRealPath(UPLOAD_PATH));
//		boardService.deleteArticle(articleNo, uploadPath);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
//	}
}
