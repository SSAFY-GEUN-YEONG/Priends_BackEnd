package com.ssafy.priends.domain.board.controller;

import java.util.List;
import java.util.Map;

import com.ssafy.priends.global.common.dto.Message;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardListDto;
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
		System.out.println(boardDto);
		boardService.writePost(boardDto);
		
		return ResponseEntity.ok().body(Message.success()); //0:성공, 1:실패
	}
 
	// category에 따라서 글 가져오기 -> category : 'NOTICE','FREE','QNA'
	@GetMapping("/list")
	public ResponseEntity<Message<BoardListDto>> listPost(@RequestParam  Map<String, String> map) throws Exception {
	
		System.out.println("listArticle map - {}" + map);
		BoardListDto list = boardService.listPost(map);
		return ResponseEntity.ok().body(Message.success(list));
	}
	/* 
	public ResponseEntity<?> listArticle(
			@RequestParam  ( required = true) Map<String, String> map) {
		log.info("listArticle map - {}", map);
		try {
			BoardListDto boardListDto = boardService.listArticle(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardListDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	 */
	
	

	@GetMapping("/view/{id}")
	public ResponseEntity<Message<BoardMemberDto>> getPost( @PathVariable("id") long id) throws Exception {
		System.out.println("id=" + id);
		boardService.updateHit(id);
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(Message.success(board));
	}

	@GetMapping("/modify/{id}")
	public ResponseEntity<Message<BoardMemberDto>> modifyPost( @PathVariable("id") long id) throws Exception {
		BoardMemberDto board = boardService.getPost(id);
		return ResponseEntity.ok().body(Message.success(board));
	}

	@PutMapping("/modify") 
	public ResponseEntity<Message<Void>> modifyPost(@RequestBody BoardMemberDto board) throws Exception {
		System.out.println("modify");
		boardService.modifyPost(board);
		return ResponseEntity.ok().body(Message.success());
	}

	@PutMapping("/delete/{id}")
	public ResponseEntity<Message<Void>> deletePost(@PathVariable("id") long id) throws Exception {
		boardService.deletePost(id);
		return ResponseEntity.ok().body(Message.success());
	}
}
