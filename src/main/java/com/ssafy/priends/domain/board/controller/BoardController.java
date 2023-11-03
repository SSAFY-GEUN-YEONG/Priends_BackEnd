package com.ssafy.priends.domain.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 
	
	// 멤버에서 토큰 보내주면 받아와서 boardDto에 넘겨줘야함
	@PostMapping("/write")
	public ResponseEntity<?> writePost( @RequestBody BoardDto boardDto ) throws Exception {
//		MemberDto member = (MemberDto) session.getAttribute("userinfo");
//		board.setUser_id(member.getId());
		
		boardService.writePost(boardDto);
		
		return ResponseEntity.ok().body(0); //0:성공, 1:실패
	}
 
	// category에 따라서 글 가져오기 -> category : 'NOTICE','FREE','PATH'
	@GetMapping("/list")
	public ResponseEntity<?> listPost(@RequestParam String category) throws Exception {
		List<BoardMemberDto> list = boardService.listPost(category);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/view")
	public ResponseEntity<?> getPost(@RequestParam("id") long id ) throws Exception {
		System.out.println("id=" + id);
		boardService.updateHit(id);
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(board);
	}

	@GetMapping("/modify")
	public ResponseEntity<?> modifyPost(@RequestParam("id") int id ) throws Exception {
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(board);
	}

	@PostMapping("/modify")
	public ResponseEntity<?> modifyPost(@RequestBody BoardDto board) throws Exception {
		boardService.modifyPost(board);
		return ResponseEntity.ok().body(0);
	}

	@GetMapping("/delete")
	public ResponseEntity<?> deletePost(@RequestParam("id") long id) throws Exception {
		boardService.deletePost(id);
		return ResponseEntity.ok().body(0);
	}
}
