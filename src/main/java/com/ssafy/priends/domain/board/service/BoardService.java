package com.ssafy.priends.domain.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.priends.domain.board.dto.BoardDto;

public interface BoardService {
	
	void writePost(BoardDto board) throws Exception;
	
//	BoardDto getPost(int boardId) throws Exception;
//
//	List<BoardDto> listPost(Map<String, String> map) throws Exception;
//
//	void updateHit(int boardId) throws Exception;
//	
//	void modifyPost(BoardDto board) throws Exception;
//	void deletePost(int boardId) throws Exception;
	
	
	
	
}


/*
private int id;
private String title;
private String content;
private String createdAt;
private String updatedAt;
private boolean isdeleted;
private String category;
*/