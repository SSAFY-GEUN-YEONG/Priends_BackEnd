package com.ssafy.priends.domain.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public void writePost(BoardDto board) throws Exception {
		boardMapper.writePost(board);
	}

	@Override
	public BoardDto getPost(int boardId) throws Exception {
		return boardMapper.getPost(boardId);
	}

	@Override
	public List<BoardDto> listPost(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<>();
		return boardMapper.listPost(param);
	}

	@Override
	public void updateHit(int boardId) throws Exception {
		boardMapper.updateHit(boardId);
	}

	@Override
	public void modifyPost(BoardDto board) throws Exception {
		boardMapper.modifyPost(board);
	}

	@Override
	public void deletePost(int boardId) throws Exception {
		boardMapper.deletePost(boardId);
	}

}
