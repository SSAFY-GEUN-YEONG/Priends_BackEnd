package com.ssafy.priends.domain.board.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.priends.domain.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	void writePost(BoardDto board) throws SQLException;

//	BoardDto getPost(int boardId) throws SQLException;
//
//	List<BoardDto> listPost(Map<String, Object> map) throws SQLException;
//
//	void updateHit(int boardId) throws SQLException;
//
//	void modifyPost(BoardDto board) throws SQLException;
//
//	void deletePost(int boardId) throws SQLException;
	
}
