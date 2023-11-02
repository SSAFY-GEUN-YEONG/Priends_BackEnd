package com.ssafy.priends.domain.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardMemberDto;
import com.ssafy.priends.domain.board.service.BoardService;
import com.ssafy.priends.domain.member.dto.MemberDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	
	private BoardService boardService;
 
	@PostMapping("/write")
	public ResponseEntity<?> writePost( @RequestParam BoardDto boardDto ) throws Exception {
//		MemberDto member = (MemberDto) session.getAttribute("userinfo");
//		board.setUser_id(member.getId());
		
		boardService.writePost(boardDto);
		
		return ResponseEntity.ok().body(0); //0:성공, 1:실패
	}
 
	@GetMapping("/list")
	public ResponseEntity<?> listPost(@RequestParam String category) throws Exception {
		List<BoardMemberDto> list = boardService.listPost(category);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/view")
	public ResponseEntity<?> getPost(@RequestParam("id") long id ) throws Exception {
		System.out.println("id=" + id);
		BoardMemberDto board = boardService.getPost(id);
//		boardService.updateHit(id);
		return ResponseEntity.ok().body(board);
	}

//	@GetMapping("/modify")
//	public String modify(@RequestParam("id") int id, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		BoardDto board = boardService.getPost(id);
//		model.addAttribute("post", board);
//		return "board/modify";
//	}

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
