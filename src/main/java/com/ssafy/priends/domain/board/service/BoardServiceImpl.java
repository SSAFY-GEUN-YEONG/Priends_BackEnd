package com.ssafy.priends.domain.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardListDto;
import com.ssafy.priends.domain.board.dto.BoardMemberDto;
import com.ssafy.priends.domain.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	public void writePost(BoardDto board) throws Exception {
		boardMapper.writePost(board);
	}

	@Override
	public BoardMemberDto getPost(long id) throws Exception {
		System.out.println("service impl id " + id);
		return boardMapper.getPost(id);
	}

	@Override
	public BoardListDto listPost(Map<String, String> map) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		// 카테고리 처리
		param.put("category", map.get("category") == null ? "FREE" : map.get("category"));
		// 페이지네이션 처리
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		// 검색어 처리
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
//		if ("nickname".equals(key))
//			param.put("key", key == null ? "" : "b.member_nick");
//		else 
		if ("id".equals(key))
			param.put("key", key == null ? "" : "b.id");
		
		//내가 쓴 글 가져오기
		param.put("myId", map.get("myId") == null ? "" : map.get("myId"));
		
		List<BoardMemberDto> list = boardMapper.listPost(param);

//		if ("member_id".equals(key))
//			param.put("key", key == null ? "" : "member_id");
//		else
		if ("id".equals(key))
			param.put("key", key == null ? "" : "id");
 
		int totalArticleCount = boardMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		System.out.println(param);

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}

	@Override
	public void updateHit(long id) throws Exception {
		boardMapper.updateHit(id);
	}

	@Override
	public void modifyPost(BoardMemberDto board) throws Exception {
		boardMapper.modifyPost(board);
	}

	@Override
	public void deletePost(long id) throws Exception {
		boardMapper.deletePost(id);
	}

}
