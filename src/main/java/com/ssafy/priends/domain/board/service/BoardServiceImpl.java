package com.ssafy.priends.domain.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ssafy.priends.domain.board.dto.BoardDto;
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
	public List<BoardMemberDto> listPost(String category) throws Exception {
		return boardMapper.listPost(category);
	}

	@Override
	public void updateHit(long id) throws Exception {
		boardMapper.updateHit(id);
	}

	@Override
	public void modifyPost(BoardDto board) throws Exception {
		boardMapper.modifyPost(board);
	}

	@Override
	public void deletePost(long id) throws Exception {
		boardMapper.deletePost(id);
	}


}
