package com.ssafy.priends.domain.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.priends.domain.board.dto.BoardDto;
import com.ssafy.priends.domain.board.dto.BoardListDto;
import com.ssafy.priends.domain.board.dto.BoardMemberDto;

public interface BoardService {
	
	void writePost(BoardDto board) throws Exception;
	BoardMemberDto getPost(long id) throws Exception;

	BoardListDto listPost(Map<String, String> map) throws Exception;

	void updateHit(long id) throws Exception;

	void modifyPost(BoardMemberDto board) throws Exception;
	void deletePost(long id) throws Exception;
	

	
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