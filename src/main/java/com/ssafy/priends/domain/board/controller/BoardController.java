package com.ssafy.priends.domain.board.controller;

import java.util.List;

import com.ssafy.priends.global.common.dto.Message;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardMemberDto;
import com.ssafy.priends.domain.board.service.BoardService;


@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	// 멤버에서 토큰 보내주면 받아와서 boardDto에 넘겨줘야함
	@PostMapping("/write")
	public ResponseEntity<Message<Void>> writePost( @RequestBody BoardDto boardDto ) throws Exception {
//		MemberDto member = (MemberDto) session.getAttribute("userinfo");
//		board.setUser_id(member.getId());
		
		boardService.writePost(boardDto);
		
		return ResponseEntity.ok().body(Message.success()); //0:성공, 1:실패
	}
 
	// category에 따라서 글 가져오기 -> category : 'NOTICE','FREE','PATH'
	@GetMapping("/list")
	public ResponseEntity<Message<List<BoardMemberDto>>> listPost(@RequestParam String category) throws Exception {
		List<BoardMemberDto> list = boardService.listPost(category);
		return ResponseEntity.ok().body(Message.success(list));
	}

	@GetMapping("/view")
	public ResponseEntity<Message<BoardMemberDto>> getPost(@RequestParam("id") long id ) throws Exception {
		System.out.println("id=" + id);
		boardService.updateHit(id);
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(Message.success(board));
	}

	@GetMapping("/modify")
	public ResponseEntity<Message<BoardMemberDto>> modifyPost(@RequestParam("id") int id ) throws Exception {
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(Message.success(board));
	}

	@PostMapping("/modify")
	public ResponseEntity<Message<Void>> modifyPost(@RequestBody BoardDto board) throws Exception {
		boardService.modifyPost(board);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/delete")
	public ResponseEntity<Message<Void>> deletePost(@RequestParam("id") Long id) throws Exception {
		boardService.deletePost(id);
		return ResponseEntity.ok().body(Message.success());
	}
}
